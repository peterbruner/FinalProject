package com.novauc.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.novauc.entities.Item;
import com.novauc.entities.SearchByProductName;
import com.novauc.entities.Items;
import com.novauc.entities.SearchMethods;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import javax.xml.parsers.*;
import org.xml.sax.InputSource;
import org.w3c.dom.*;


import javax.xml.bind.JAXBException;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

@Controller
public class TheController {

    //public static ArrayList<SearchByProductName> results = new ArrayList<>();

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session, String userRequest) {

//            Items something = new Items();
            model.addAttribute("modeledSupermarket", session.getAttribute("supermarket"));
//            something = (Items) session.getAttribute("walmart");
            model.addAttribute("modeledWalmart", session.getAttribute("walmart"));


//        if(results.size() > 0) {
//            model.addAttribute("modeledWalmart", results);
//
//        }
        return "index";
    }

    @RequestMapping(path = "/find", method = RequestMethod.POST)
    public String getSearchByProductName(String userRequest, HttpSession session) throws IOException, JAXBException {
        session.setAttribute("supermarket", SearchMethods.getSupermarket(userRequest));
        Items something = SearchMethods.setWalmart(userRequest);
        session.setAttribute("walmart", something.getItems());
//        session.setAttribute("walmart", SearchMethods.getWalmart(userRequest));
//        session.setAttribute("walmart1", Items.get);
        return "redirect:/";
    }
}