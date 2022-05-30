package net.wsm.controller;
import net.wsm.helper.UserManager;
import net.wsm.model.*;
import net.wsm.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController{
    @Autowired
    UserManager userManager;

    private UserRepository repository = new UserRepository();

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String init(Model model){
        model.addAttribute("message", "Please Enter Login Details");
        
        return "login";
    }

    @PostMapping("/createLogin")
    public String submit(Model model, @ModelAttribute("loginModel") loginModel loginModel){
        boolean success = userManager.SignIn(loginModel);
        if (success) return "redirect:";
        model.addAttribute("error", true);
        model.addAttribute("errorMessage", "login details incorrect");
        return "redirect:login";
    }

    @RequestMapping(value="/logout")
    public String logout(){
        userManager.SignOut();
        return "home";
    }
}
