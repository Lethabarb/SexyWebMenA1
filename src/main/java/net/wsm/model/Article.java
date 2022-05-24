package net.wsm.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.cglib.core.Local;

public class Article {
    private String id;
    private String title;
    private String description;
    private String solution = "";
    private LocalDateTime opened;
    private ArrayList<Comment> comments = new ArrayList<>();
    public Article() {}
    public Article(String t, String d, String s) {
        title = t;
        description = d;
        solution = s;
        id = UUID.randomUUID().toString();
    }
    public Article(String t, String d, String s, String i) {
        title = t;
        description = d;
        solution = s;
        id = i;
    }

    public void setTitle(String t) {
        title = t;
    }
    public String getTitle() {
        return title;
    }
    public void setDescription(String d) {
        description = d;
    }
    public String getDescription() {
        return description;
    }
    public void setSolution(String s) {
        solution = s;
    }
    public void addToSolution(String s) {
        solution += s;
    }
    public String getSolution() {
        return solution;
    }
    public void setDateOpened(LocalDateTime d) {
        opened = d;
    }
    public LocalDateTime getDateOpened() {
        return opened;
    }
    public void addComment(Comment c) {
        comments.add(c);
    }
    public void setComments(ArrayList<Comment> c) {
        comments = c;
    }
    public ArrayList<Comment> getComments() {
        return comments;
    }
}
