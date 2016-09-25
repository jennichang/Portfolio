package com.theironyard.controllers;

import com.theironyard.entities.Update;
import com.theironyard.entities.User;
import com.theironyard.services.UpdateRepository;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@EnableAutoConfiguration
public class PortfolioStartController {

    @Autowired
    UserRepository users;

    @Autowired
    UpdateRepository updates;


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

    @RequestMapping(path = "/portfolio", method = RequestMethod.GET)
    public String portfolio() {
        return "portfolio";
    }

    @RequestMapping(path = "/updates", method = RequestMethod.GET)
    public String home(Model model) { // have parameter list contain model
        List<Update> updateList = (List) updates.findAll(); // put into list everything in message repository
        model.addAttribute("updates", updateList); // give list of messages to model
        return "updates";
    }

    @RequestMapping(path = "/add-update", method = RequestMethod.POST)
    public String addUpdate(HttpSession session, String update, LocalDateTime dateTime) {
        session.setAttribute("update", update);
        Update addedUpdate = new Update(update, dateTime);
        updates.save(addedUpdate);
        return "redirect:/updates";
    }







///////////////////////////////////






//
//    @RequestMapping(path = "/user", method = RequestMethod.GET)
//    public String user(HttpSession session, Model model) {
//        String email = (String) session.getAttribute("email");
//        User user = users.findFirstByEmail(email);
//        model.addAttribute("user", user);
//        return "loggedIn";
//    }

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