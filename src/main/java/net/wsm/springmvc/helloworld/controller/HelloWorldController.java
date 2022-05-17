package net.wsm.springmvc.helloworld.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.wsm.springmvc.helloworld.model.*;

@Controller
public class HelloWorldController {
	@RequestMapping("/helloworld")
	public String handler(Model model) {
		
		HelloWorld helloWorld = new HelloWorld();
		helloWorld.setX("Hello World Example Using Spring MVC 5!!!");
		model.addAttribute("helloWorld", helloWorld);
		return "helloworld";
	}
    // @RequestMapping("/")
    // public String index() {
    //     return "index";
    // }

}
