package com.novauc.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.novauc.entities.SearchByProductName;
import com.novauc.entities.Items;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import javax.xml.parsers.*;
import org.xml.sax.InputSource;
import org.w3c.dom.*;


import javax.xml.bind.JAXBException;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

@Controller
public class TheController {

    public static ArrayList<SearchByProductName> results = new ArrayList<>();

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model) {
        if(results.size() > 0) {
            model.addAttribute("modeledResult", results);
        }
        return "index";
    }

    @RequestMapping(path = "/find", method = RequestMethod.POST)
    public String getSearchByProductName(String userRequest) throws IOException, JAXBException {
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
        int x = 0;

        return "redirect:/";
    }

    public static String getCharacterDataFromElement(Element e) {
        Node child = e.getFirstChild();
        if (child instanceof CharacterData) {
            CharacterData cd = (CharacterData) child;
            return cd.getData();
        }
        return "?";
    }


    @RequestMapping(path = "/find1", method = RequestMethod.POST)
    public String getWalmart(String userRequest1) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        String walmart = restTemplate.getForObject(
                "http://api.walmartlabs.com/v1/search?apiKey=c3exxssx4eme5j56s5zk7xg7&query=" + userRequest1, String.class
        );
        ObjectMapper objectMapper = new ObjectMapper();
        Items items1 = objectMapper.readValue(walmart, Items.class);
//        System.out.println(items1.getName());
//        System.out.println(items1.getAvailableOnline());
        int x = 0;
        return "redirect:/";
    }
}