package net.wsm.controller;

import java.nio.ReadOnlyBufferException;
import java.security.KeyPairGenerator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.HashMap;

import net.wsm.model.*;
import net.wsm.repository.IssueRepository;
import net.wsm.repository.UserRepository;

@Controller
public class IssueController {
    private IssueRepository repository = new IssueRepository();
    private UserRepository userRepos = new UserRepository();

    @RequestMapping(value = "/newIssue", method = RequestMethod.GET)
    public ModelAndView issue() {
        return new ModelAndView("NewIssue", "command", new Issue());
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
                "sol", issue.getCatagory(), "sc", users[0].getId());


            System.out.println(newIssue.toString());          
            repository.createIssue(newIssue);       
            Issue[] issues = repository.getAll();

            HashMap<Issue, User> issueMap = new HashMap<>();

            for (Issue i : issues) {

                System.out.println(i.toString());

                for (User user : users) {
                    if (i.getReporterId() == user.getId()) {
                        issueMap.put(i, user);
                    }
                }
            }
            model.addAttribute("issueMap", issueMap);
            model.addAttribute("issues", issues);
            return "issues";
        }

    @RequestMapping("/test")
    public String test(Model model) {
        User[] users = userRepos.getAll();
        User u = users[0];
        System.out.println(u.getTokenExp().toString());
        return "issues";
    }
   
    @RequestMapping("/issues")
    public String handler(Model model) {
        Issue[] issues = repository.getAll();
        User[] users = userRepos.getAll();
        HashMap<Issue, User> issueMap = new HashMap<>();
        for (Issue issue : issues) {
            System.out.println(issue.toString());
            User u;
            for (User user : users) {
                if (issue.getReporterId() == user.getId()) {
                    issueMap.put(issue, user);
                }
            }
        }
        model.addAttribute("issueMap", issueMap);
        model.addAttribute("issues", issues);
        return "issues";
    }
}
