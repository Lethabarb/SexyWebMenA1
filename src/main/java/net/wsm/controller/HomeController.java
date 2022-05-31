package net.wsm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import net.wsm.helper.UserManager;

@Controller
public class HomeController {
    @Resource(name = "userManager")
    private UserManager userManager;
    
    @RequestMapping("/")
    public String home(Model model){
        System.out.println("home");
        model.addAttribute("userManager", userManager);
        return "home";
    }
}
