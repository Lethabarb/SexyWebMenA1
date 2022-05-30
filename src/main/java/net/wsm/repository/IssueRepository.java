package net.wsm.repository;


import net.wsm.model.*;

public class IssueRepository {
    private DbContext context = new DbContext();

    public synchronized Issue[] getAll() {
        Issue[] issues = context.getAsync(Issue.class);
        return context.getAsync(Issue.class);
    }

    public synchronized Issue getById(String id) {
        Issue[] issues = context.getAsync(Issue.class, String.format("id = '%s'", id));
        return issues[0];
    }

    public synchronized boolean createIssue(Issue issue) {
        return context.CreateAsync(issue);
    }

    public synchronized boolean updateIssue(Issue issue) {
        return context.updateAsync(issue, issue.getId());
    }

    public boolean deleteIssue(String id) {
        Issue u = getById(id);
        return context.deleteAsync(Issue.class, u.getId());
    }
}