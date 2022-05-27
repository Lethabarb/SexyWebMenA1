package net.wsm.controller;
//when it is working, PULL REQUEST
import java.security.KeyPairGenerator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.sql.*;
import java.time.LocalDateTime;

import net.wsm.model.*;
import net.wsm.repository.UserRepository;

@Controller
public class UserController {
    private UserRepository repository = new UserRepository();

    @RequestMapping("/test")
    public String test(Model m) {
        User[] users = repository.getAll();
        m.addAttribute("thisClient", users[0]);
        return "HelloWorld";
    }

    @RequestMapping("/user")
    public String handler(Model model) {
        User u = new User("abcd@gmail.com", 1, "Jeff");
        model.addAttribute("thisClient", u);

        return "HelloWorld";
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String login(String logindata) {

        return "";
    }

    public KeyPairGenerator encrypt(String password) {
        // KeyPairGenerator kPG = KeyPairGenerator.getInstance(password);
        return null;
    }
}
