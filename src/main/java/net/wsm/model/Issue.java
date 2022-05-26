package net.wsm.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.cglib.core.Local;

public class Issue {
    private String id;
    private String title;
    private String description;
    private String solution = "";
    private LocalDateTime opened;
    private LocalDateTime closed;
    private String catagory;
    private String subCatagory;
    private String reporterId;
    private int status;
    private ArrayList<Comment> comments = new ArrayList<>();
    public Issue() {}
    public Issue(String t, String d, String s, String c, String sc, String r) {
        title = t;
        description = d;
        solution = s;
        id = UUID.randomUUID().toString();
        catagory = c;
        subCatagory = sc;
        reporterId = r;
    }
    public Issue(String i, String t, String d, String s, String c, String sc, String r) {
        title = t;
        description = d;
        solution = s;
        id = i;
        catagory = c;
        subCatagory = sc;
        reporterId = r;
    }
    public Issue(String i, String t, String d, String s, LocalDateTime op, LocalDateTime cl, String c, String sc, String r) {
        title = t;
        description = d;
        solution = s;
        id = i;
        catagory = c;
        subCatagory = sc;
        opened = op;
        closed = cl;
        reporterId = r;
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
    public void setDateClosed(LocalDateTime d) {
        closed = d;
    }
    public LocalDateTime getDateClosed() {
        return closed;
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
    public void setCatagory(String c) {
        catagory = c;
    }
    public String getCatagory() {
        return catagory;
    }
    public void setSubCatagory(String sc) {
        subCatagory = sc;
    }
    public String getSubCatagory() {
        return subCatagory;
    }
    public String getId() {
        return id;
    }
    public void setReporterId(String id) {
        reporterId = id;
    }
    public String getReportedId() {
        return reporterId;
    }
    public void setStatus(int s) {
        status = s;
    }
    public void incrementStatus() {
        status++;
    }
    public int getStatus() {
        return status;
    }
}
