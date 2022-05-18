package net.wsm.springmvc.joe.controller;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.wsm.springmvc.joe.model.*;

@Controller
public class HelloWorldControllerJoe {

    @RequestMapping("/helloworld_joe")
    public String handler(Model model) {

        HelloWorldJoe helloWorld = new HelloWorldJoe();
        helloWorld.setMessage("Hello World Example Using Spring MVC 5!!!");
        helloWorld.setDateTime(LocalDateTime.now().toString());
        model.addAttribute("helloWorld", helloWorld);
        return "hello";
    }
}