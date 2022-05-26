package net.wsm.controller;

import java.security.KeyPairGenerator;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 
import net.wsm.model.*;
import net.wsm.repository.IssueRepository;
import net.wsm.repository.UserRepository;

@Controller
public class UserController {
    private UserRepository repository = new UserRepository();

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String login(String logindata) {

        return "";
    }

    public KeyPairGenerator encrypt(String password) {
        // KeyPairGenerator kPG = KeyPairGenerator.getInstance(password);
        return null;
    }
}
