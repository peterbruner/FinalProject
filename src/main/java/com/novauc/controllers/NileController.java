package com.novauc.controllers;

import com.novauc.entities.Items;
import com.novauc.SearchMethods;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
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
    public String home(Model model, HttpSession session, String userRequest) {
        model.addAttribute("modeledSupermarketItems", session.getAttribute("supermarketItems"));
        model.addAttribute("modeledWalmartItems", session.getAttribute("walmartItems"));
        model.addAttribute("modeledWalmartStores", session.getAttribute("walmartStores"));
        model.addAttribute("modeledSupermarketStores", session.getAttribute("supermarketStores"));
        return "index";
    }

    @RequestMapping(path = "/find", method = RequestMethod.POST)
    public String getSearchByProductName(String userRequest, String zipRequest, HttpSession session) throws IOException, JAXBException {
        session.setAttribute("supermarketItems", SearchMethods.supermarketItems(userRequest));
        Items items = SearchMethods.walmartItems(userRequest);
        session.setAttribute("walmartItems", items.getItems());
        session.setAttribute("walmartStores", SearchMethods.wmStores(zipRequest));
        session.setAttribute("supermarketStores", SearchMethods.supermarketStores(zipRequest));
        return "redirect:/";
    }

//    @RequestMapping(path = "/login")
//    public String login() {
//        return "login";
//    }
//
//    @RequestMapping(path = "/logout")
//    public String logout(HttpServletRequest request, HttpServletResponse response) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if(auth != null){
//            new SecurityContextLogoutHandler().logout(request, response, auth);
//        }
//        request.setAttribute("logout", "logout");
//        return "login";
//    }
}