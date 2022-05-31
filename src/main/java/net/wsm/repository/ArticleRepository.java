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

    public synchronized Article[] getByCatagory(String category){
        Article[] articles = context.getAsync(Article.class, String.format("catagory = '%s'", category));
        return articles;
    }

    public synchronized Article[] getBySubCatagory(String catagory, String subcatagory){
        Article[] articles = context.getAsync(Article.class, String.format("catagory = '%s' AND subCatagory = '%s'", catagory, subcatagory));
        return articles;
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