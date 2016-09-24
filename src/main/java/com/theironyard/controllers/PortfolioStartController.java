package com.theironyard.controllers;

import com.theironyard.entities.User;
import com.theironyard.services.UserRepository;
import com.theironyard.utilities.PasswordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@EnableAutoConfiguration
public class PortfolioStartController {

    @Autowired
    UserRepository users;


    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home() {
        return "home";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public User login(String username, String password, HttpSession session, HttpServletResponse response) throws Exception {
        User user = users.findFirstByName(username);
        if (user == null) {
            response.sendRedirect("/signup");
        } else if (!PasswordStorage.verifyPassword(password, user.getPassword())) {
            throw new Exception("Wrong password");
        }
        session.setAttribute("username", username);
        response.sendRedirect("/");
        return user;
    }

    @RequestMapping(path = "/signup", method = RequestMethod.GET)
    public String signup() {
        return "signup";
    }



}