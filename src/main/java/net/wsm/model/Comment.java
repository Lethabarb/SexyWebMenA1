package net.wsm.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Comment {
    private String id;
    private String parent;
    private String relation;
    private int author;
    private LocalDateTime date;
    private String content;
    private Comment[] replies = new Comment[0];
    public Comment() {
        id = UUID.randomUUID().toString();
        parent = id;
        relation = "";
        author = 0;
        date = LocalDateTime.now();
        content = "";
    }
    public Comment(int u, String content, String relation, String parent) {
        id = UUID.randomUUID().toString();
        this.parent = parent;
        this.relation = relation;
        author = u;
        date = LocalDateTime.now();
        this.content = content;
    }
    public Comment(int u, String content, Comment parent) {
        id = UUID.randomUUID().toString();
        this.parent = parent.getId();
        this.relation = "C";
        author = u;
        date = LocalDateTime.now();
        this.content = content;
    }
    public void setId(String a) {
        id = a;
    }
    public String getId() {
        return id.toString();
    }
    public void setAuthor(int a) {
        author = a;
    }
    public int getAuthor() {
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
        Comment[] newArr = new Comment[replies.length + 1];
        for (int i = 0; i < replies.length; i++) {
            newArr[i] = replies[i];
        }
        newArr[replies.length] = c;
        replies = newArr;
    }
    public void setReplies(Comment[] r) {
        replies = r;
    }
    public Comment[] getReplies() {
        return replies;
    }
    public String getParent() {
        return parent;
    }
}
