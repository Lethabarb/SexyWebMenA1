package net.wsm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.wsm.model.HelloWorld;

@Controller
public class HelloWorldController {
    @RequestMapping("/helloworld")
    public String handler(Model model) {
        System.out.println("entered /helloworld");
        HelloWorld e = new HelloWorld();
        e.setX("hello world!");
        model.addAttribute("x", e);
        return "HelloWorld";
    }
    @RequestMapping("/")
    public String index() {
        return "index";
    }

}
