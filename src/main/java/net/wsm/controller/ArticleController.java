package net.wsm.controller;


import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.wsm.helper.UserManager;
import net.wsm.model.*;
import net.wsm.repository.ArticleRepository;
import net.wsm.repository.CommentRepository;
import net.wsm.repository.UserRepository;

@Controller
public class ArticleController {
    private ArticleRepository repository = new ArticleRepository();
    private UserRepository userRepos = new UserRepository();
    private CommentRepository commentRepos = new CommentRepository();

    private HashMap<String, String[]> subcategories = new HashMap<>();
    //subcategories.put("Network", new String[] {"Can't connent", "Speed", "Constant dropouts"});

    @Resource(name = "userManager")
    private UserManager userManager;
    
    @RequestMapping("/articles")
    public String articles(Model model) {
        Article[] articles = repository.getAll();
        model.addAttribute("userManager", userManager);
        model.addAttribute("articles", articles);
        return "articles";
    }

    @RequestMapping("/article/{id}")
    public String article(Model model, @PathVariable("id") String id) {
        Article article = repository.getById(id);
        Comment[] comments = commentRepos.getArticleComments(id);
        User[] usersArray = userRepos.getAll();
        HashMap<Integer, User> users = new HashMap<>();
        for (User user : usersArray) {
            users.put(user.getId(), user);
        }
        model.addAttribute("userManager", userManager);
        model.addAttribute("users", users);
        model.addAttribute("article", article);
        model.addAttribute("comments", comments);
        return "article";
    }

    @PostMapping("/comment")
    public String Comment(HttpServletRequest request, @RequestParam int author, @RequestParam String relation, @RequestParam String parent, @RequestParam String content, @RequestParam(defaultValue = "/") String redirectPath) {
        request.getSession().setAttribute("userManager", userManager);
        Comment c = new Comment(author, content, relation, parent);
        commentRepos.createComment(c);
        return String.format("redirect:%s", redirectPath);
    }

    @RequestMapping("/reply")
    public String createComment(@RequestParam String parent, @RequestParam String redirectPath, Model model) {
        Comment Cparent = commentRepos.getById(parent);
        model.addAttribute("redirectPath", redirectPath);
        model.addAttribute("userManager", userManager);
        model.addAttribute("comment", Cparent);
        return "createComment";
    }
    @RequestMapping("/admin/article/{id}")
    public String editArticle(Model model, @PathVariable("id") String id, ServletRequest request) {
         System.out.print("admjin controller thing");
        Article article = repository.getById(id);
        Comment[] comments = commentRepos.getArticleComments(id);
        User[] usersArray = userRepos.getAll();
        HashMap<Integer, User> users = new HashMap<>();
        for (User user : usersArray) {
            users.put(user.getId(), user);
        }
        model.addAttribute("userManager", userManager);
        model.addAttribute("users", users);
        model.addAttribute("article", article);
        model.addAttribute("comments", comments);
        return "editArticle";
    }
    @PostMapping("/admin/editArticle/{id}")
    public String ArticleEdit(Model model,@RequestParam("id") String id, @RequestParam String title, @RequestParam String description, @RequestParam String solution, @RequestParam String catagory, @RequestParam String subCatagory) {
        Article a = repository.getById(id);
        a.setTitle(title);
        a.setDescription(description);
        a.setSolution(solution);
        a.setCatagory(catagory);
        a.setSubCatagory(subCatagory);
        repository.updateArticle(a);
        return String.format("redirect:/article/%s", id);
    }
}
