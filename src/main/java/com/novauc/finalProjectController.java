package com.novauc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.rmi.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by dangelojoyce on 4/10/17.
 */
@Controller
public class finalProjectController {
    HashMap<String, String> zipcodeMap = new HashMap<>();
    HashMap<String, String> newUrl = new HashMap<>();


    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session) {

        model.addAttribute("craigslistItems", session.getAttribute("craigslistItems"));


        return "index";
    }

    @RequestMapping(path = "/search", method = RequestMethod.POST)
    public String scrape(String zipcode, String input, HttpSession session) throws IOException, MalformedURLException, UnknownHostException {
        String searchQuery = input;
        String location = zipcode.replaceAll("\\s","");
        zipcodeMap.put(location, "https://" +location.trim()+ ".craigslist.org/search/sss?sort=rel&query=");
        WebClient client = new WebClient();
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);
        String searchUrl = zipcodeMap.get(location) + URLEncoder.encode(searchQuery, "UTF-8");
        HtmlPage page = client.getPage(searchUrl);

        List<HtmlElement> items = (List<HtmlElement>) page.getByXPath("//p[@class='result-info']");
        ArrayList<Item> craigslistItems = new ArrayList<>();

        if (items.isEmpty()) {
            System.out.println("No items found !");
        } else {
            for (HtmlElement htmlItem : items) {
                HtmlAnchor itemAnchor = htmlItem.getFirstByXPath(".//a");
                HtmlElement spanPrice = htmlItem.getFirstByXPath(".//span[@class='result-price']");
                String itemPrice = spanPrice == null ? "0.0" : spanPrice.asText();

                Item item = new Item();
                item.setTitle(itemAnchor.asText());
                item.setUrl("https://" +location + ".craigslist.org" + itemAnchor.getHrefAttribute());
                item.setPrice(new BigDecimal(itemPrice.replace("$", "")));
                craigslistItems.add(item);
                ObjectMapper mapper = new ObjectMapper();
                String jsonString = mapper.writeValueAsString(item);
                System.out.println(jsonString);
            }

        }
        session.setAttribute("craigslistItems", craigslistItems);
        return "redirect:/";
    }
}







