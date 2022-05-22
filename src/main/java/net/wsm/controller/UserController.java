package net.wsm.controller;

import java.security.KeyPairGenerator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.wsm.model.*;

@Controller
public class UserController {
    @RequestMapping("/user")
    public String handler(Model model){
        User u = new User("abc@gmail.com", 1, "Jeff");
        model.addAttribute("thisClient", u);


        return "HelloWorld";
    }

    @RequestMapping(value="/submit", method = RequestMethod.POST)
    public String login(String logindata){

        return "";
    }
    
    public KeyPairGenerator encrypt(String password){
        //KeyPairGenerator kPG = KeyPairGenerator.getInstance(password);
        return null;
    }
}
