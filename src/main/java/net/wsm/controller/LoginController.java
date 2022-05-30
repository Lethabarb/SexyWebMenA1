package net.wsm.controller;
import net.wsm.model.*;
import net.wsm.repository.UserRepository;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/account")
public class LoginController{
    private UserRepository repository = new UserRepository();

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String init(Model model){
        model.addAttribute("message", "Please Enter Login Details");
        
        return "login";
    }

    @PostMapping("/createLogin")
    public String submit(Model model, @ModelAttribute("loginModel") loginModel loginModel, HttpSession session){
        System.out.println(loginModel.getEmail());
        System.out.println("Password: " + loginModel.getPassword());
        if(loginModel != null && loginModel.getPassword() != null && loginModel.getEmail() != null){
            User loginUser = repository.getByEmail(loginModel.getEmail()); //check if password matches
            System.out.println("loginUser password: " + loginUser.getPassword());
            if(loginModel.getPassword().equals(loginUser.getPassword())){
                model.addAttribute("loggedIn", loginUser);
                session.setAttribute("thisClient", loginUser);
                return "home";
            }
            else{
                model.addAttribute("error", "Invalid email or password");
                System.out.println("Error: Invalid email or password");
                return "login";
            }
        }
        model.addAttribute("error", "Please input details");
        System.out.println("Error: please input details");
        return "login";
    }

    @RequestMapping(value="/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "home";
    }
}
