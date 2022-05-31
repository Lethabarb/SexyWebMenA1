package net.wsm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.wsm.helper.UserManager;

@Controller
public class HomeController {
    @Resource(name = "userManager")
    private UserManager userManager;
    
    @RequestMapping("/")
    public String home(Model model, ServletRequest request){
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        session.setAttribute("isSignedIn", userManager.getIsSignedIn());
        model.addAttribute("userManager", userManager);
        return "home";
    }
}
