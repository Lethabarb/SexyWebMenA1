package net.wsm.controller;

import java.nio.ReadOnlyBufferException;
import java.security.KeyPairGenerator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;

import net.wsm.helper.UserManager;
import net.wsm.model.*;
import net.wsm.repository.CommentRepository;
import net.wsm.repository.IssueRepository;
import net.wsm.repository.UserRepository;

@Controller
public class IssueController {
    private IssueRepository repository = new IssueRepository();
    private UserRepository userRepos = new UserRepository();
    private CommentRepository commentRepos = new CommentRepository();

    private HashMap<String, String[]> subcategories = new HashMap<>();
    private String selectedCategory;
    private String selectedSubCategory;

    IssueController(){
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

    @RequestMapping("/issues")
    public String issues(Model model, @RequestParam(defaultValue = "") String category, @RequestParam(defaultValue = "") String subCategory) {
        setUpSubcategories();
        selectedCategory = category;
        selectedSubCategory = subCategory;
        Issue[] issues = repository.getAll();
        if(!selectedCategory.equals("") && selectedSubCategory.equals("")){
            issues = repository.getByCatagory(selectedCategory);
        }
        else if(!selectedCategory.equals("") && !selectedSubCategory.equals("")){
            issues = repository.getBySubCatagory(selectedCategory, selectedSubCategory);
        }
        User[] users = userRepos.getAll();
        HashMap<Issue, User> issueMap = new HashMap<>();
        if (issues != null)
        {
            for (Issue i : issues) {
                for (User u : users) {
                    if (i.getReporter() == u.getId()) {             
                        issueMap.put(i, u);
                    }
                }
            }
        }
        model.addAttribute("subcategories", subcategories.get(selectedCategory));
        model.addAttribute("selectedCategory", selectedCategory);
        model.addAttribute("selectedSubcategory", selectedSubCategory);
        model.addAttribute("userManager", userManager);
        model.addAttribute("issues", issues);
        model.addAttribute("issueMap", issueMap);
        return "issues";
    }

    @RequestMapping(value = "admin/newIssue", method = RequestMethod.GET)
    public ModelAndView issue() {
        return new ModelAndView("newIssue", "command", new Issue());
    }

    @RequestMapping(value = "/addIssue", method = RequestMethod.POST)
    public String addIssue(@ModelAttribute("issue")Issue issue,
        BindingResult result, ModelMap model) {
            if (result.hasErrors()) {
                //return "error";
                System.out.println("error!");
            }
            User[] users = userRepos.getAll();
            Issue newIssue = new Issue(issue.getTitle(), issue.getDescription(),
                "solution", issue.getCatagory(), issue.getSubCatagory(), users[1].getId());
            System.out.println(newIssue.toString());          
            repository.createIssue(newIssue);       
            Issue[] issues = repository.getAll();
            HashMap<Issue, User> issueMap = new HashMap<>();
            for (Issue i : issues) {
                for (User user : users) {
                    if (i.getReporter() == user.getId()) {
                        issueMap.put(i, user);
                    }
                }
            }
            model.addAttribute("issueMap", issueMap);
            model.addAttribute("issues", issues);
            return "issues";
        }

    @RequestMapping("/issue/{id}")
    public String issue(Model model, @PathVariable("id") String id) {
        Issue issue = repository.getById(id);
        Comment[] comments = commentRepos.getIssueComments(id);
        User[] usersArray = userRepos.getAll();
        HashMap<Integer, User> users = new HashMap<>();
        for (User user : usersArray) {
            users.put(user.getId(), user);
        }
        model.addAttribute("userManager", userManager);
        model.addAttribute("users", users);
        model.addAttribute("issue", issue);
        model.addAttribute("comments", comments);
        return "issue";
    }

    @RequestMapping("/admin/issue/{id}")
    public String editIssue(Model model, @PathVariable("id") String id, ServletRequest request) {
        Issue issue = repository.getById(id);
        Comment[] comments = commentRepos.getIssueComments(id);
        User[] usersArray = userRepos.getAll();
        HashMap<Integer, User> users = new HashMap<>();
        for (User user : usersArray) {
            users.put(user.getId(), user);
        }
        model.addAttribute("userManager", userManager);
        model.addAttribute("users", users);
        model.addAttribute("issue", issue);
        model.addAttribute("comments", comments);
        return "editIssue";
    }

    @PostMapping("/admin/editIssue")
    public String issueEdit(Model model,@RequestParam("id") String id, @RequestParam String title, @RequestParam String description, @RequestParam String solution, @RequestParam String catagory, @RequestParam String subCatagory, @RequestParam int status) {
        Issue i = repository.getById(id);
        i.setTitle(title);
        i.setDescription(description);
        i.setSolution(solution);
        i.setCatagory(catagory);
        i.setSubCatagory(subCatagory);
        i.setStatus(status);
        if (i.getStatus() == 3)
        {
            i.setDateClosed(LocalDateTime.now());
        }
        repository.updateIssue(i);
        return String.format("redirect:/issue/%s", id);
    }
}
