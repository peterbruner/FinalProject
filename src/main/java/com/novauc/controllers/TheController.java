package com.novauc.controllers;

import com.novauc.entities.SearchByProductName;
import com.novauc.repositories.SearchByProductNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;

@Controller
public class TheController {

    @Autowired
    SearchByProductNameRepository sbpnRepository;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model) {
        return "index";
    }

    //this should get the information from the api. Without a datastructure through, it will only present
    //the most recent information for a single entry?

    @RequestMapping(path = "/find", method = RequestMethod.POST)
    public SearchByProductName getSearchByProductName(String userRequest) {
        RestTemplate restTemplate = new RestTemplate();
        SearchByProductName searchByProductName = restTemplate.getForObject(
                "http://www.SupermarketAPI.com/api.asmx/SearchByProductName?APIKEY=59837307ef&ItemName=" + "coconut",
                SearchByProductName.class);
        System.out.println(searchByProductName.getItemName());
        return searchByProductName;
    }


}
