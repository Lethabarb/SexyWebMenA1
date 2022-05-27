package net.wsm.controller;
import net.wsm.model.*;
import net.wsm.repository.UserRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
    private UserRepository repository = new UserRepository();

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String init(Model model){
        model.addAttribute("message", "Please Enter Login Details");
        return "login";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String submit(Model model, @ModelAttribute("loginModel") loginModel loginModel){

        if(loginModel != null && loginModel.getPassword() != null && loginModel.getEmail() != null){
            User loginUser = repository.getByEmail(loginModel.getEmail()); //check if password matches
            if(loginModel.getPassword().equals(loginUser.getPassword())){
                model.addAttribute("loggedIn", loginUser);
            }
            else{
                model.addAttribute("error", "Invalid email or password");
                return "login";
            }
        }
        model.addAttribute("error", "Please input details");
        return "login";
    }


}
