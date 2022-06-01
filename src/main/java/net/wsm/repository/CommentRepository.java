package net.wsm.repository;

import net.wsm.model.*;

public class CommentRepository {
    private DbContext context = new DbContext();

    public synchronized Comment[] getAll() {
        Comment[] c = context.getAsync(Comment.class);
        return c;
    }

    public synchronized Comment getById(String id) {
        Comment[] comments = context.getAsync(Comment.class, String.format("id = '%s'", id));
        return comments[0];
    }

    public synchronized boolean createComment(Comment comment) {
        return context.CreateAsync(comment);
    }

    public synchronized boolean updateComment(Comment comment) {
        return context.updateAsync(comment, comment.getId());
    }

    public boolean deleteComment(String id) {
        Comment u = getById(id);
        return context.deleteAsync(Comment.class, u.getId());
    }

    public synchronized Comment[] getReplies(String commentId) {
        Comment[] comments = context.getAsync(Comment.class, "[relation] = 'C'",
                String.format("parent = '%s'", commentId));
        if (comments == null)
            return null;
        for (Comment comment : comments) {
            Comment[] replies = getReplies(comment.getId());
            if (replies != null) {
                for (Comment comment2 : replies) {
                    comment.addReply(comment2);
                }
            }

        }
        return comments;
    }

    public synchronized Comment[] getArticleComments(String articleId) {
        Comment[] comments = context.getAsync(Comment.class, "[relation] = 'A'",
                String.format("parent = '%s'", articleId));
        if (comments != null) {
            for (Comment comment : comments) {
                Comment[] replies = getReplies(comment.getId());
                if (replies != null) {
                    for (Comment comment2 : replies) {
                        comment.addReply(comment2);
                    }
                }

            }
        }
        return comments == null ? new Comment[0] : comments;
    }

    public synchronized Comment[] getIssueComments(String issueId) {
        Comment[] comments = context.getAsync(Comment.class, "[relation] = 'I'",
                String.format("parent = '%s'", issueId));
        if (comments != null) {
            for (Comment comment : comments) {
                Comment[] replies = getReplies(comment.getId());
                if (replies != null) {
                    for (Comment comment2 : replies) {
                        comment.addReply(comment2);
                    }
                }
            }
        }
        return comments == null ? new Comment[0] : comments;
    }
}