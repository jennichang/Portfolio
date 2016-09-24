package com.theironyard.controllers;

import com.theironyard.entities.User;
import com.theironyard.services.UserRepository;
import com.theironyard.utilities.PasswordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@EnableAutoConfiguration
public class PortfolioStartController {

    @Autowired
    UserRepository users;


    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(HttpSession session, Model model) {
        String email = (String) session.getAttribute("email");
        User user = users.findFirstByEmail(email);
        if (user != null) {
            model.addAttribute("user", user);
        }
        return "home";
    }


    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public User login(String email, String password, HttpSession session, HttpServletResponse response) throws Exception {
        User user = users.findFirstByEmail(email);
        if (user == null) {
            response.sendRedirect("/signup");
        } else if (!PasswordStorage.verifyPassword(password, user.getPassword())) {
            throw new Exception("Wrong password");
        }
        session.setAttribute("username", email);
        response.sendRedirect("/");
        return user;
    }

    @RequestMapping(path = "/signup", method = RequestMethod.GET)
    public String signup() {
        return "signup";
    }

    @RequestMapping(path = "/signup", method = RequestMethod.POST)
    public String signupPost(String firstName, String lastName, String email, String password) {
        User user = new User(firstName, lastName, email, password);
        users.save(user);
        return "redirect:/";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }




}