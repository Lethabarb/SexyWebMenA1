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
import net.wsm.repository.IssueRepository;
import net.wsm.repository.UserRepository;

@Controller
public class ArticleController {
    private IssueRepository repository = new IssueRepository();
    private UserRepository userRepos = new UserRepository();

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
