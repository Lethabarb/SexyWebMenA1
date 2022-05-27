package net.wsm.controller;
//when it is working, PULL REQUEST

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.sql.*;
import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

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

    @RequestMapping("/authorize")
    public String authorize(HttpSession session, String pageAccessing){
        User u = (User)session.getAttribute("thisClient");
        if(u.getRole() > 0){
            
        }
        return pageAccessing;
    }

}
