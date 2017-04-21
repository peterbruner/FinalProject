package com.novauc;


import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.novauc.entities.Items;
import com.novauc.entities.SearchByProductName;
import com.novauc.entities.Source;
import com.novauc.entities.WalmartStore;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.*;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class SearchMethods {

    //SupermarketAPI
    public static ArrayList<SearchByProductName> getSupermarket(String userRequest){

        ArrayList<SearchByProductName> results = new ArrayList<>();

        RestTemplate restTemplate = new RestTemplate();
        String ourProdXML = restTemplate.getForObject(
                "http://www.SupermarketAPI.com/api.asmx/SearchByProductName?APIKEY=59837307ef&ItemName=" + userRequest,
                String.class);

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(ourProdXML));

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

                results.add(new SearchByProductName(itemName, itemDesc, itemCat, itemID, itemImage, aisleNumber));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
//        int x = 0;
        return results;
    }

    public static String getCharacterDataFromElement(Element e) {
        Node child = e.getFirstChild();
        if (child instanceof CharacterData) {
            CharacterData cd = (CharacterData) child;
            return cd.getData();
        }
        return "?";
    }

    //Walmart
    public static Items walmartItems(String userRequest) throws IOException{

        RestTemplate restTemplate = new RestTemplate();
        String jsonData = restTemplate.getForObject(
                "http://api.walmartlabs.com/v1/search?apiKey=c3exxssx4eme5j56s5zk7xg7&query=" + userRequest, String.class
        );
        ObjectMapper objectMapper = new ObjectMapper();
        Items items = objectMapper.readValue(jsonData, Items.class);

        return items;
    }

    public static WalmartStore[] wmStores(String zipRequest) throws IOException{
        RestTemplate restTemplate = new RestTemplate();
        WalmartStore[] jsonData = restTemplate.getForObject(
                "http://api.walmartlabs.com/v1/stores?apiKey=c3exxssx4eme5j56s5zk7xg7&zip=" + zipRequest + "&format=json", WalmartStore[].class
        );
        System.out.println(jsonData.length);
        System.out.println(jsonData.toString());
        return jsonData;
    }
}
