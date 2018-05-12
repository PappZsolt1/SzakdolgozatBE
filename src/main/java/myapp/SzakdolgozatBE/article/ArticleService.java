package myapp.SzakdolgozatBE.article;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ArticleService {
    
    @Inject ArticleDAO dao;
    
    public Article saveArticle(String title, String content) {
        Article tmp = new Article();
        tmp.setTitle(title);
        tmp.setContent(content);
        tmp.setSaved(true);
        return dao.saveArticle(tmp);
    }
    //user elérés szerverről az aktuális bejelentkező
    
    public Article publishNewArticle(String title, String content) {
        Article tmp = new Article();
        tmp.setTitle(title);
        tmp.setContent(content);
        tmp.setPublishDate(new Date());
        tmp.setPublished(true);
        return dao.publishNewArticle(tmp);
    }
    
    public Article publishSavedArticle(long id, String title, String content) {
        Article tmp = new Article();
        tmp.setTitle(title);
        tmp.setContent(content);
        return dao.publishSavedArticle(tmp, id);
    }
    
    public List<Article> getSavedArticles() {
        return dao.getSavedArticles();
    }
    
    public List<Article> getPublishedArticles() {
        return dao.getPublishedArticles();
    }
    
    public void deleteArticle(long id) {
        dao.deleteArticle(id);
    }
    
    public Article getArticle(long id) throws NullPointerException {
        return dao.getArticle(id);
    }
}
