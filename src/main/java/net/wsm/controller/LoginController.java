package net.wsm.controller;
import net.wsm.helper.UserManager;
import net.wsm.model.*;
import net.wsm.repository.UserRepository;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController{
    @Resource(name = "userManager")
    private UserManager userManager;

    private UserRepository repository = new UserRepository();

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String init(Model model, @RequestParam(name="error", defaultValue = "false") boolean error, @RequestParam(name="errorMessage", defaultValue = "test") String errorMessage){
        model.addAttribute("message", "Please Enter Login Details");
        model.addAttribute("error", error);
        model.addAttribute("errorMessage", errorMessage);
        return "login";
    }

    @PostMapping("/createLogin")
    public String submit(Model model, @ModelAttribute("loginModel") loginModel loginModel, ServletRequest request){
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        boolean success = userManager.SignIn(loginModel);
        session.setAttribute("isSignedIn", userManager.getIsSignedIn());
        session.setAttribute("role", userManager.getUser().getRole());
        session.setAttribute("Exp", userManager.getUser().getTokenExp());
        if (success) return "redirect:/";
        model.addAttribute("error", true);
        model.addAttribute("errorMessage", "login details incorrect");
        return "redirect:/login";
    }

    @RequestMapping(value="/logout")
    public String logout(ServletRequest request){
        userManager.SignOut();
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        session.setAttribute("isSignedIn", userManager.getIsSignedIn());
        session.setAttribute("role", userManager.getUser().getRole());
        return "home";
    }
}
