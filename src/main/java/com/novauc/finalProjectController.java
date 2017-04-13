package com.novauc;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dangelojoyce on 4/10/17.
 */
@Controller
public class finalProjectController implements FinalProjectInterface {

    public static ArrayList<Item> jsitem = new ArrayList<>();

    public String eStore() throws IOException, JAXBException {
        ArrayList<JsonParser> jsonp = new ArrayList<JsonParser>();

        Store store = new Store();
        String uri =
                "http://api.walmartlabs.com/v1/stores?format=json&zip=20744&apiKey=c3exxssx4eme5j56s5zk7xg7";
        URL url = new URL(uri);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");

        InputStream json = connection.getInputStream();
        byte[] bytes = IOUtils.toByteArray(json);


        connection.disconnect();


        return bytes.toString();
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(){
        return "index.html";
    }

    @RequestMapping(path = "/walmart")
    public String walmartTest() {
        try {
            return eStore();
        } catch (Exception e){
            System.out.println("Houston had a problem @: ");
            e.printStackTrace();
        }
        return "";
    }


//    @RequestMapping(path = "/search")
//    public String test() throws IOException {
//            return scrape();
//    }
    @RequestMapping (path = "/search", method = RequestMethod.POST)
    public String scrape(String input) throws IOException {
        String searchQuery = input;

        WebClient client = new WebClient();
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);
        String searchUrl = "https://newyork.craigslist.org/search/sss?sort=rel&query=" + URLEncoder.encode(searchQuery, "UTF-8");
        HtmlPage page = client.getPage(searchUrl);

  //      System.out.println(page.asXml());
        //page = page.asXml();

        List<HtmlElement> items = (List<HtmlElement>) page.getByXPath("//p[@class='result-info']");
        if (items.isEmpty()) {
            System.out.println("No items found !");
        } else {
            for (HtmlElement htmlItem : items) {

                HtmlAnchor itemAnchor = htmlItem.getFirstByXPath(".//a");

                HtmlElement spanPrice = htmlItem.getFirstByXPath(".//span[@class='result-price']");

                String itemPrice = spanPrice == null ? "0.0" : spanPrice.asText() ;

                Item item = new Item();
                item.setTitle(itemAnchor.asText());
                item.setUrl( "https://newyork.craigslist.org" +
                        itemAnchor.getHrefAttribute());
                item.setPrice(new BigDecimal(itemPrice.replace("$", "")));

                ObjectMapper mapper = new ObjectMapper();
                String jsonString = mapper.writeValueAsString(item) ;
                System.out.println(jsonString);
                    }
                }


        return "redirect:/";
    }

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






