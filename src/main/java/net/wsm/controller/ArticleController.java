package net.wsm.controller;

import java.nio.ReadOnlyBufferException;
import java.security.KeyPairGenerator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.HashMap;

import net.wsm.model.*;
import net.wsm.repository.ArticleRepository;
import net.wsm.repository.ArticleRepository;
import net.wsm.repository.UserRepository;

@Controller
public class ArticleController {
    private ArticleRepository repository = new ArticleRepository();
    private UserRepository userRepos = new UserRepository();
    
    @RequestMapping("/articles")
    public String handler(Model model) {
        Article[] articles = repository.getAll();
        model.addAttribute("articles", articles);
        return "articles";
    }
}
