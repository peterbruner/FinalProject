package com.novauc.controllers;

import com.novauc.entities.Items;
import com.novauc.entities.SearchMethods;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class NileController {

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session) {
        model.addAttribute("modeledSupermarketItems", session.getAttribute("supermarketItems"));
        model.addAttribute("modeledSupermarketStores", session.getAttribute("supermarketStores"));
        model.addAttribute("modeledWalmartItems", session.getAttribute("walmartItems"));
        model.addAttribute("modeledWalmartStores", session.getAttribute("walmartStores"));
        model.addAttribute("craigslistItems", session.getAttribute("craigslistItems"));
        return "index";
    }

    @RequestMapping(path = "/search", method = RequestMethod.POST)
    public String getSearchByProductName(String input, String zipcode, String city, HttpSession session) throws IOException, JAXBException {
        session.setAttribute("supermarketItems", SearchMethods.supermarketItems(input));
        session.setAttribute("supermarketStores", SearchMethods.smStoreSearch(zipcode));
        Items items = SearchMethods.walmartItems(input);
        session.setAttribute("walmartItems", items.getItems());
        session.setAttribute("walmartStores", SearchMethods.wmStores(zipcode));
        session.setAttribute("craigslistItems", SearchMethods.craigslistItems(city, input));
        return "redirect:/";
    }
}

