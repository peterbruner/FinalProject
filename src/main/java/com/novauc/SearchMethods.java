package com.novauc;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.novauc.entities.*;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.*;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class SearchMethods {

    //Supermarket Items
    public static ArrayList<SearchByProductName> supermarketItems(String input){

        ArrayList<SearchByProductName> results = new ArrayList<>();

        RestTemplate restTemplate = new RestTemplate();
        String xmlData = restTemplate.getForObject(
                "http://www.SupermarketAPI.com/api.asmx/SearchByProductName?APIKEY=59837307ef&ItemName=" + input,
                String.class);

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xmlData));

            Document doc = db.parse(is);
            NodeList nodes = doc.getElementsByTagName("Product");
            for (int i = 0; i < nodes.getLength(); i++) {
                Element element = (Element) nodes.item(i);

                NodeList name = element.getElementsByTagName("Itemname");
                Element line = (Element) name.item(0);
                String itemName = getCharacterDataFromElement(line);

                NodeList desc = element.getElementsByTagName("ItemDescription");
                line = (Element) desc.item(0);
                String itemDesc = getCharacterDataFromElement(line);

                NodeList cat = element.getElementsByTagName("ItemCategory");
                line = (Element) cat.item(0);
                String itemCat = getCharacterDataFromElement(line);

                NodeList id = element.getElementsByTagName("ItemID");
                line = (Element) id.item(0);
                String itemID = getCharacterDataFromElement(line);

                NodeList image = element.getElementsByTagName("ItemImage");
                line = (Element) image.item(0);
                String itemImage = getCharacterDataFromElement(line);

                NodeList aisle = element.getElementsByTagName("AisleNumber");
                line = (Element) aisle.item(0);
                String aisleNumber = getCharacterDataFromElement(line);

                Random rand = new Random();

                int smCost = rand.nextInt(50) + 1;

                results.add(new SearchByProductName(itemName, itemDesc, itemCat, itemID, itemImage, aisleNumber, smCost));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    //Used by SupermarketItems & Supermarket Stores
    public static String getCharacterDataFromElement(Element e) {
        Node child = e.getFirstChild();
        if (child instanceof CharacterData) {
            CharacterData cd = (CharacterData) child;
            return cd.getData();
        }
        return "?";
    }


    //Supermarket Stores
    public static ArrayList<StoresByZip> supermarketStores(String zipcode){

        ArrayList<StoresByZip> results = new ArrayList<>();

        RestTemplate restTemplate = new RestTemplate();
        String XMLdata = restTemplate.getForObject(
                "http://www.SupermarketAPI.com/api.asmx/StoresByZip?APIKEY=59837307&ZipCode=" + zipcode,
                String.class);

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(XMLdata));

            Document doc = db.parse(is);
            NodeList nodes = doc.getElementsByTagName("Store");
            for (int i = 0; i < nodes.getLength(); i++) {
                Element element = (Element) nodes.item(i);

                NodeList name = element.getElementsByTagName("Storename");
                Element line = (Element) name.item(0);
                String smName = getCharacterDataFromElement(line);

                NodeList address = element.getElementsByTagName("Address");
                line = (Element) address.item(0);
                String smAddress = getCharacterDataFromElement(line);

                NodeList city = element.getElementsByTagName("City");
                line = (Element) city.item(0);
                String smCity = getCharacterDataFromElement(line);

                NodeList state = element.getElementsByTagName("State");
                line = (Element) state.item(0);
                String smState = getCharacterDataFromElement(line);

                NodeList zip = element.getElementsByTagName("Zip");
                line = (Element) zip.item(0);
                String smZip = getCharacterDataFromElement(line);

                NodeList phone = element.getElementsByTagName("Phone");
                line = (Element) phone.item(0);
                String smPhone = getCharacterDataFromElement(line);

                NodeList id = element.getElementsByTagName("StoreId");
                line = (Element) id.item(0);
                String smId = getCharacterDataFromElement(line);

                results.add(new StoresByZip(smName, smAddress, smCity, smState, smZip, smPhone, smId));
                results.size();
                results.get(results.size()-1).getSmName();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }


    //Walmart Items
    public static Items walmartItems(String input) throws IOException{
        RestTemplate restTemplate = new RestTemplate();
        String jsonData = restTemplate.getForObject(
                "http://api.walmartlabs.com/v1/search?apiKey=c3exxssx4eme5j56s5zk7xg7&query=" + input, String.class
        );
        ObjectMapper objectMapper = new ObjectMapper();
        Items items = objectMapper.readValue(jsonData, Items.class);
        return items;
    }

    //Walmart Stores
    public static WalmartStores[] wmStores(String zipcode) throws IOException{
        RestTemplate restTemplate = new RestTemplate();
        WalmartStores[] jsonData = restTemplate.getForObject(
                "http://api.walmartlabs.com/v1/stores?apiKey=c3exxssx4eme5j56s5zk7xg7&zip=" + zipcode + "&format=json", WalmartStores[].class
        );
        return jsonData;
    }

    //Craigslist
    public static ArrayList<ItemCL> craigslistItems(String city, String input) throws IOException {

        HashMap<String, String> zipcodeMap = new HashMap<>();

        String searchQuery = input;
        String location = city.replaceAll("\\s","");
        zipcodeMap.put(location, "https://" +location.trim()+ ".craigslist.org/search/sss?sort=rel&query=");
        WebClient client = new WebClient();
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);
        String searchUrl = zipcodeMap.get(location) + URLEncoder.encode(searchQuery, "UTF-8");
        HtmlPage page = client.getPage(searchUrl);

        List<HtmlElement> items = (List<HtmlElement>) page.getByXPath("//p[@class='result-info']");
        ArrayList<ItemCL> craigslistItems = new ArrayList<>();

        for (HtmlElement htmlItem : items) {
            HtmlAnchor itemAnchor = htmlItem.getFirstByXPath(".//a");
            HtmlElement spanPrice = htmlItem.getFirstByXPath(".//span[@class='result-price']");
            String itemPrice = spanPrice == null ? "0.0" : spanPrice.asText();

            ItemCL item = new ItemCL();
            item.setTitle(itemAnchor.asText());
            item.setUrl("https://" + location + ".craigslist.org" + itemAnchor.getHrefAttribute());
            item.setPrice(new BigDecimal(itemPrice.replace("$", "")));
            craigslistItems.add(item);
        }
        return craigslistItems;
    }
}