package net.wsm.repository;


import net.wsm.model.*;

public class ArticleRepository {
    private DbContext context = new DbContext();

    public synchronized Article[] getAll() {
        Article[] articles = context.getAsync(Article.class);
        return context.getAsync(Article.class);
    }

    public synchronized Article getById(String id) {
        Article[] articles = context.getAsync(Article.class, String.format("id = '%s'", id));
        return articles[0];
    }

    public synchronized boolean createArticle(Article article) {
        return context.CreateAsync(article);
    }

    public synchronized boolean updateArticle(Article article) {
        return context.updateAsync(article, article.getId());
    }

    public boolean deleteArticle(String id) {
        Article u = getById(id);
        return context.deleteAsync(Article.class, u.getId());
    }
}