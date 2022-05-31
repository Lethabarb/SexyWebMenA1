package net.wsm.controller;


import java.time.LocalDateTime;
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
    private String selectedCategory;
    private String selectedSubCategory;

    ArticleController(){
        setUpSubcategories();
        selectedCategory = "";
        selectedSubCategory = "";
    }

    private void setUpSubcategories(){
        subcategories.put("", new String[] {""});
        subcategories.put("Network", new String[] {"Can't connent", "Speed", "Constant dropouts"});
        subcategories.put("Software", new String[] {"Slow to load", "Won't load at all"});
        subcategories.put("Hardware", new String[] {"Computer won't turn on", "Computer \"blue screens\"", "Disk drive", "Peripherals"});
        subcategories.put("Email", new String[] {"Can't send", "Can't recieve", "SPAM/Phishing"});
        subcategories.put("Account", new String[] {"Password reset", "Wrong details"});

    }

    @Resource(name = "userManager")
    private UserManager userManager;
    
    @RequestMapping("/articles") //dropdown box defaults back to "no filter"!!!!
    public String articles(Model model, @RequestParam(defaultValue = "") String category, @RequestParam(defaultValue = "") String subCategory) {
        setUpSubcategories();
        selectedCategory = category;
        selectedSubCategory = subCategory;
        Article[] articles = repository.getAll();
        if(!selectedCategory.equals("") && selectedSubCategory.equals("")){
            articles = repository.getByCatagory(selectedCategory);
        }
        else if(!selectedCategory.equals("") && !selectedSubCategory.equals("")){
            articles = repository.getBySubCatagory(selectedCategory, selectedSubCategory);
        }
        model.addAttribute("subcategories", subcategories.get(selectedCategory));
        model.addAttribute("selectedCategory", selectedCategory);
        model.addAttribute("selectedSubcategory", selectedSubCategory);
        model.addAttribute("userManager", userManager);
        model.addAttribute("articles", articles);
        for(int i=0; i<subcategories.get(selectedCategory).length; i++){
            System.out.println("Subcategory = " + subcategories.get(selectedCategory)[i]);
        }
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
    @PostMapping("/admin/editArticle")
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
    @RequestMapping("/admin/createArticle")
    public String CreateArticle(Model model) {
        Article a = new Article();
        model.addAttribute("article", a);
        return "createArticle";
    }
    @PostMapping("/admin/articleCreate")
    public String ArticleCreate(Model model,@RequestParam("id") String id, @RequestParam String title, @RequestParam String description, @RequestParam String solution, @RequestParam String catagory, @RequestParam String subCatagory) {
        Article a = new Article(title, description, solution, catagory, subCatagory);
        a.setDateOpened(LocalDateTime.now());
        repository.createArticle(a);
        return String.format("redirect:/article/%s", a.getId());
    }
}
