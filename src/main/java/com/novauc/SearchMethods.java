package com.novauc;


import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.novauc.entities.Items;
import com.novauc.entities.SearchByProductName;
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
    public static ArrayList<SearchByProductName> supermarketItems(String userRequest){

        ArrayList<SearchByProductName> results = new ArrayList<>();

        RestTemplate restTemplate = new RestTemplate();
        String xmlData = restTemplate.getForObject(
                "http://www.SupermarketAPI.com/api.asmx/SearchByProductName?APIKEY=59837307ef&ItemName=" + userRequest,
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

                results.add(new SearchByProductName(itemName, itemDesc, itemCat, itemID, itemImage, aisleNumber));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
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


    public static ArrayList<SearchByProductName> supermarketStores(String userRequest){

        ArrayList<SearchByProductName> results = new ArrayList<>();

        RestTemplate restTemplate = new RestTemplate();
        String XMLdata = restTemplate.getForObject(
                "http://www.SupermarketAPI.com/api.asmx/StoresByZip?APIKEY=59837307&ZipCode=" + userRequest,
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
                String itemName = getCharacterDataFromElement(line);

                NodeList address = element.getElementsByTagName("Address");
                line = (Element) address.item(0);
                String itemAddress = getCharacterDataFromElement(line);

                NodeList city = element.getElementsByTagName("City");
                line = (Element) city.item(0);
                String itemCity = getCharacterDataFromElement(line);

                NodeList state = element.getElementsByTagName("State");
                line = (Element) state.item(0);
                String itemState = getCharacterDataFromElement(line);

                NodeList zip = element.getElementsByTagName("Zip");
                line = (Element) zip.item(0);
                String itemZip = getCharacterDataFromElement(line);

                NodeList phone = element.getElementsByTagName("Phone");
                line = (Element) phone.item(0);
                String itemPhone = getCharacterDataFromElement(line);

                NodeList id = element.getElementsByTagName("StoreId");
                line = (Element) id.item(0);
                String itemId = getCharacterDataFromElement(line);

                results.add(new SearchByProductName(itemName, itemDesc, itemCat, itemID, itemImage, aisleNumber));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }


    //Walmart Items
    public static Items walmartItems(String userRequest) throws IOException{
        RestTemplate restTemplate = new RestTemplate();
        String jsonData = restTemplate.getForObject(
                "http://api.walmartlabs.com/v1/search?apiKey=c3exxssx4eme5j56s5zk7xg7&query=" + userRequest, String.class
        );
        ObjectMapper objectMapper = new ObjectMapper();
        Items items = objectMapper.readValue(jsonData, Items.class);
        return items;
    }

    //Walmart Stores
    public static WalmartStore[] wmStores(String zipRequest) throws IOException{
        RestTemplate restTemplate = new RestTemplate();
        WalmartStore[] jsonData = restTemplate.getForObject(
                "http://api.walmartlabs.com/v1/stores?apiKey=c3exxssx4eme5j56s5zk7xg7&zip=" + zipRequest + "&format=json", WalmartStore[].class
        );
        return jsonData;
    }
}
