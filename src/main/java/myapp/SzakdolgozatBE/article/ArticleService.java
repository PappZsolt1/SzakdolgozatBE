package myapp.SzakdolgozatBE.article;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ArticleService {
    
    @Inject ArticleDAO dao;
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy. MM. dd. HH:mm:ss");
    
    public Article saveArticle(String title, String content) {
        Article tmp = new Article();
        tmp.setTitle(title);
        tmp.setContent(content);
        tmp.setSaved(true);
        //tmp.setMyUser(myUser);
        return dao.saveArticle(tmp);
    }
    
    public Article publishNewArticle(String title, String content) {
        Article tmp = new Article();
        tmp.setTitle(title);
        tmp.setContent(content);
        tmp.setPublishDate(sdf.format(new Date()));
        tmp.setPublished(true);
        //tmp.setMyUser(myUser);
        return dao.publishNewArticle(tmp);
    }
    
    public Article publishSavedArticle(long id, String title, String content) throws NullPointerException {
        Article tmp = dao.getArticle(id);
        if (tmp != null) {
            tmp.setTitle(title);
            tmp.setContent(content);
            tmp.setPublishDate(sdf.format(new Date()));
            tmp.setSaved(false);
            tmp.setPublished(true);
            return dao.publishSavedArticle(tmp, id);
        } else {
            throw new NullPointerException();
        }        
    }
    
    public List<Article> getSavedArticles() {
        return dao.getSavedArticles(1);//todo
    }
    
    public List<Article> getPublishedArticles() {
        return dao.getPublishedArticles();
    }
    
    public void deleteArticle(long id) throws NullPointerException {
        Article tmp = dao.getArticle(id);
        if (tmp != null) {
            dao.deleteArticle(id);
        } else {
            throw new NullPointerException();
        }
    }
    
    public Article getArticle(long id) throws NullPointerException {
        Article tmp = dao.getArticle(id);
        if (tmp != null) {
            return tmp;
        } else {
            throw new NullPointerException();
        }
    }
}
