package net.wsm.controller;

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

    @RequestMapping("/testCreate")
    public String testCreate(Model m) {
        User u = new User("testCreate@wsm", 1, "oli");
        repository.createUser(u);
        User u2 = repository.getByEmail(u.getEmail());
        m.addAttribute("thisClient", u2);
        return "HelloWorld";
    }
    
    @RequestMapping("/testEdit")
    public String testEdit(Model m) {
        User u = new User("testUpate@wsm", 1, "oli");
        repository.updateUser(u, "testCreate@wsm");
        User u2 = repository.getByEmail(u.getEmail());
        m.addAttribute("thisClient", u2);
        return "HelloWorld";
    }

    @RequestMapping("/testDelete")
    public String testDelete(Model m) {
        repository.deleteUser("testUpdate@wsm");
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
