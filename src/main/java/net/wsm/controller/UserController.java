package net.wsm.controller;

import java.security.KeyPairGenerator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.sql.*;
import java.time.LocalDateTime;

import net.wsm.model.*;

@Controller
public class UserController {
    

    @RequestMapping("/test")
    public String test(Model model) {
        Connection c = getDbConnection();
        User u = new User();
        try {
            Statement s = c.createStatement();
            ResultSet res = s.executeQuery("SELECT * FROM [User]");
            res.next();
            u = new User(res.getString("email"), res.getInt("role"), res.getString("authToken"), res.getString("firstName"), res.getString("lastName"),
                    res.getString("contactNumber"), LocalDateTime.now());
        } catch (Exception e) {
            u = new User(e.getMessage(), 1, "Jeff");
        }
        model.addAttribute("thisClient", u);
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
