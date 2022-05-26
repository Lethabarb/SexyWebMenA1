package net.wsm.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class Comment {
    private String id;
    private String pageId;
    private String parent;
    private Comment Parent = null;
    private String author;
    private LocalDateTime date;
    private String content;
    private ArrayList<Comment> replies = new ArrayList<>();
    public Comment() {
        id = UUID.randomUUID().toString();
        parent = id;
        author = "";
        date = LocalDateTime.now();
        content = "";
    }
    public Comment(User u, String content, String page) {
        id = UUID.randomUUID().toString();
        parent = id;
        author = u.getEmail();
        date = LocalDateTime.now();
        this.content = content;
        pageId = page;
    }
    public Comment(User u, String content, String page, String i) {
        id = i;
        parent = id;
        author = u.getEmail();
        date = LocalDateTime.now();
        this.content = content;
        pageId = page;
    }

    public Comment(User u, String content, Comment p) {
        id = UUID.randomUUID().toString();
        parent = p.getId();
        Parent = p;
        author = u.getEmail();
        date = LocalDateTime.now();
        this.content = content;
        pageId = p.getPageId();
    }

    public void setId(String a) {
        id = a;
    }
    public String getId() {
        return id.toString();
    }
    public void setAuthor(String a) {
        author = a;
    }
    public String getAuthor() {
        return author;
    }
    public void setDate(LocalDateTime d) {
        date = d;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public void setContent(String c) {
        content = c;
    }
    public String getContent() {
        return content;
    }
    public void addReply(Comment c) {
        replies.add(c);
    }
    public void setReplies(ArrayList<Comment> r) {
        replies = r;
    }
    public ArrayList<Comment> getReplies() {
        return replies;
    }
    public Comment getParent() {
        return Parent;
    }
    public String getPageId() {
        return pageId;
    }
}
