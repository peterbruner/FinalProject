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
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by dangelojoyce on 4/10/17.
 */
@Controller
public class finalProjectController  {
    HashMap<String, String> zipcodeMap = new HashMap<>();
    HashMap<String, HashMap>zipcodeHash = new HashMap<>();


    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session){

        model.addAttribute("craigslistItems", session.getAttribute("craigslistItems"));


        return "index";
    }

//    @RequestMapping(path = "/walmart")
//    public String walmartTest() {
//        try {
//            return eStore();
//        } catch (Exception e){
//            System.out.println("Houston had a problem @: ");
//            e.printStackTrace();
//        }
//        return "";
//    }


//    @RequestMapping(path = "/search")
//    public String test() throws IOException {
//            return scrape();
//    }
    @RequestMapping (path = "/search", method = RequestMethod.POST)
    public String scrape(String zipcode, String input,  HttpSession session) throws IOException {
        String searchQuery = input;
        String location = zipcode;
        zipcodeMap.put("DC", "https://dc.craigslist.org/search/sss?sort=rel&query=");
        zipcodeMap.put("Dallas", "https://dallas.craigslist.org/search/sss?sort=rel&query=");
        WebClient client = new WebClient();
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);
        String searchUrl = zipcodeMap.get(location) + URLEncoder.encode(searchQuery, "UTF-8");
//        String searchUrl = "https://dc.craigslist.org/search/sss?sort=rel&query=" + URLEncoder.encode(searchQuery, "UTF-8");
        HtmlPage page = client.getPage(searchUrl);
//        zipcode.put(searchQuery, searchUrl);
//        if(searchQuery == "75001"){
//            zipcode.get(searchQuery);
//            return searchQuery;
//        }


        List<HtmlElement> items = (List<HtmlElement>) page.getByXPath("//p[@class='result-info']");
        ArrayList<Item> craigslistItems = new ArrayList<>();

        if (items.isEmpty()) {
            System.out.println("No items found !");
        } else {
            for (HtmlElement htmlItem : items) {
                HtmlAnchor itemAnchor = htmlItem.getFirstByXPath(".//a");

                HtmlElement spanPrice = htmlItem.getFirstByXPath(".//span[@class='result-price']");

                String itemPrice = spanPrice == null ? "0.0" : spanPrice.asText() ;

                Item item = new Item();
                item.setTitle(itemAnchor.asText());
                item.setUrl( searchUrl +
                        itemAnchor.getHrefAttribute());
                item.setPrice(new BigDecimal(itemPrice.replace("$", "")));
                craigslistItems.add(item);
                ObjectMapper mapper = new ObjectMapper();
                String jsonString = mapper.writeValueAsString(item) ;
                System.out.println(jsonString);
            }

                }
            session.setAttribute("craigslistItems", craigslistItems);
        return "redirect:/";
    }
//    public String HashMap(){
//        HashMap<String, String> zipcode = new HashMap<>();
//        zipcode.put("75001", "https://dallas.craigslist.org/search/sss?sort=rel&query=");
//
//
//    }

//    @RequestMapping(path = "/")
//    public String jauntson(){
//
//        try{
//            UserAgent userAgent = new UserAgent();         //create new userAgent (headless browser).
//            userAgent.sendGET("https://newyork.craigslist.org/search/sss?sort=rel&query=");   //send request
//            System.out.println(userAgent.json);            //print the retrieved JSON object
//            System.out.println("Other response data: " + userAgent.response); //response metadata, including headers.
//            Item item = new Item();
//
//
//
//        }
//        catch(JauntException e){         //if an HTTP/connection error occurs, handle JauntException.
//            System.err.println(e);
//        }
//
//    return "";
//    }

//    public class Servlet extends HttpServlet {
//
//        @Override
//        protected void doPost(HttpServletRequest request, HttpServletResponse response)
//                throws ServletException, IOException {
//            handleRequest(request, response);
//        }
//
//        protected void doGet(HttpServletRequest request, HttpServletResponse response)
//                throws ServletException, IOException {
//            handleRequest(request, response);
//        }
//
//        protected void handleRequest(HttpServletRequest request, HttpServletResponse response)
//                throws ServletException, IOException {
//
//            String p = request.getParameter("url");
//            System.out.println("test");
//            System.out.println(p);
//        }
//    }

}





