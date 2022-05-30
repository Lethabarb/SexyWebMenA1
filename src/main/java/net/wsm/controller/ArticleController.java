package net.wsm.controller;

import java.nio.ReadOnlyBufferException;
import java.security.KeyPairGenerator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.HashMap;

import net.wsm.model.*;
import net.wsm.repository.ArticleRepository;
import net.wsm.repository.CommentRepository;
import net.wsm.repository.ArticleRepository;
import net.wsm.repository.UserRepository;

@Controller
public class ArticleController {
    private ArticleRepository repository = new ArticleRepository();
    private UserRepository userRepos = new UserRepository();
    private CommentRepository commentRepos = new CommentRepository();
    
    @RequestMapping("/articles")
    public String articles(Model model) {
        Article[] articles = repository.getAll();
        model.addAttribute("articles", articles);
        return "articles";
    }

    @RequestMapping("/article/{id}")
    public String article(Model model, @PathVariable("id") String id) {
        System.out.println(id);
        Article article = repository.getById(id);
        Comment[] comments = commentRepos.getArticleComments(id);
        for (Comment comment : comments) {
            System.out.println(comment.getContent());
        }
        model.addAttribute("article", article);
        model.addAttribute("comments", comments);
        return "article";
    }
}