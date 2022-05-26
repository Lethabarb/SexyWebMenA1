package net.wsm.controller;

import java.nio.ReadOnlyBufferException;
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
public class IssueController {
    private UserRepository repository = new UserRepository();
    


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
