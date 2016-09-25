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
import java.io.IOException;

@Controller
@EnableAutoConfiguration
public class PortfolioStartController {

    @Autowired
    UserRepository users;


    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(HttpSession session, Model model) {
        String email = (String) session.getAttribute("email");
        User user = users.findFirstByEmail(email);
        model.addAttribute("user", user);
        return "home";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(String email, String password, HttpSession session, HttpServletResponse response) throws Exception {
        User user = users.findFirstByEmail(email);
        if (user == null) {
            response.sendRedirect("/signup");
        } else if (!PasswordStorage.verifyPassword(password, user.getPassword())) {
            throw new Exception("Wrong password");
        }
        session.setAttribute("email", email);
        return "redirect:/";
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String loginGet() {
        return "login";
    }










///////////////////////////////////







    @RequestMapping(path = "/user", method = RequestMethod.GET)
    public String user(HttpSession session, Model model) {
        String email = (String) session.getAttribute("email");
        User user = users.findFirstByEmail(email);
        model.addAttribute("user", user);
        return "loggedIn";
    }

    @RequestMapping(path = "/about", method = RequestMethod.GET)
    public String aboutMe(HttpSession session, Model model) {
        String email = (String) session.getAttribute("email");
        User user = users.findFirstByEmail(email);
        model.addAttribute("user", user);
        return "aboutme";
    }













    @RequestMapping(path = "/signup", method = RequestMethod.GET)
    public String signup() {
        return "signup";
    }

    @RequestMapping(path = "/resume", method = RequestMethod.GET)
    public String resume() {
        return "resume";
    }

    @RequestMapping(path = "/signup", method = RequestMethod.POST)
    public String signupPost(String firstName, String lastName, String email, String password) throws Exception {
        User user = new User(firstName, lastName, email, PasswordStorage.createHash(password));
        users.save(user);
        return "redirect:/user";
    }

    @RequestMapping("/logout")
    public void logout(HttpSession session, HttpServletResponse response) throws IOException {
        session.invalidate();
        response.sendRedirect("/");
    }


}