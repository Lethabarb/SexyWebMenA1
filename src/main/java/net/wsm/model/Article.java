package net.wsm.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class Article {
    private String id;
    private String title;
    private String description;
    private String solution = "";
    private LocalDateTime dateOpened;
    private String catagory;
    private String subCatagory;
    public Article() {
        id = "new";
        title = "";
        description = "";
        dateOpened = LocalDateTime.now();
        catagory = "";
        subCatagory = "";
    }
    public Article(String t, String d, String s, String c, String sc) {
        title = t;
        description = d;
        solution = s;
        id = UUID.randomUUID().toString();
        catagory = c;
        subCatagory = sc;
    }
    public Article(String t, String d, String s, String i, String c, String sc, LocalDateTime dt) {
        title = t;
        description = d;
        solution = s;
        id = i;
        catagory = c;
        subCatagory = sc;
        dateOpened = dt;
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
    public void setId(String id) {
        this.id = id;
    }
}
