package com.theironyard.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@EnableAutoConfiguration
public class PortfolioStartController {


    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home() {
        return "home";
    }


}