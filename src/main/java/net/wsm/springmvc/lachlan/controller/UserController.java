package net.wsm.springmvc.lachlan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.wsm.springmvc.lachlan.model.*;

@Controller
public class UserController {
    @RequestMapping("/user")
    public String handler(Model model){
        User u = new User("abc@gmail.com", 1, "Jeff");
        model.addAttribute("thisClient", u);
        return "HelloWorld";
    }
    
}
