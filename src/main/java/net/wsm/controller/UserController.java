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

    public Connection getDbConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection c = DriverManager.getConnection("jdbc:sqlserver://OOGWAY\\OOGWAY;databaseName=wsm",
                    "adminLogin", "admin");
            return c;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @RequestMapping("/test")
    public String test(Model model) {
        Connection c = getDbConnection();
        User u = new User();
        try {
            Statement s = c.createStatement();
            ResultSet res = s.executeQuery("SELECT * FROM [User]");
            res.next();
            u = new User(res.getString(0), res.getInt(1), res.getString(2), res.getString(3), res.getString(4),
                    res.getString(5), LocalDateTime.parse(res.getString(6)));
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
