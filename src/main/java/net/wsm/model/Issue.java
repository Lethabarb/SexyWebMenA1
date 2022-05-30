package net.wsm.model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class Issue {
    private String id;
    private String title;
    private String description;
    private String solution = "";
    private LocalDateTime dateOpened;
    private LocalDateTime dateClosed;
    private String catagory;
    private String subCatagory;
    private int reporterId;
    private int status;
    private ArrayList<Comment> comments = new ArrayList<>();
    public Issue() {}
    public Issue(String t, String d, String s, String c, String sc, int r) {
        title = t;
        description = d;
        solution = s;
        id = UUID.randomUUID().toString();
        catagory = c;
        subCatagory = sc;
        reporterId = r;
    }
    public Issue(String i, String t, String d, String s, String c, String sc, int r) {
        title = t;
        description = d;
        solution = s;
        id = i;
        catagory = c;
        subCatagory = sc;
        reporterId = r;
    }
    public Issue(String i, String t, String d, String s, LocalDateTime op, LocalDateTime cl, String c, String sc, int r) {
        title = t;
        description = d;
        solution = s;
        id = i;
        catagory = c;
        subCatagory = sc;
        dateOpened = op;
        dateClosed = cl;
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
        dateOpened = d;
    }
    public LocalDateTime getDateOpened() {
        return dateOpened;
    }
    public String getDateOpenedString() {
        return dateOpened.toLocalDate().toString();
    }
    public void setDateClosed(LocalDateTime d) {
        dateClosed = d;
    }
    public LocalDateTime getDateClosed() {
        return dateClosed;
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
    public void setReporterId(int id) {
        reporterId = id;
    }
    public int getReporterId() {
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
    public String toString() {
        return String.format("USER: \n id: %s \n title: %s \n desc: %s \n soln: %s \n reporter: %s \n dateOpened: %s \n dateClosed: %s \n cat: %s \n subcat: %s \n status: %s  ", id, title, description, solution, reporterId, dateOpened.toString(), dateClosed.toString(), catagory, subCatagory, status);
    }
}
