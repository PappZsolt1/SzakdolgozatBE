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
    
    public Article saveArticle(Article article) {
        if (article.getId() == null) {
            article.setSaved(true);
            //tmp.setMyUser(myUser);
            return dao.saveArticle(article);
        } else {
            Article tmp = dao.getArticle(article.getId());
            tmp.setTitle(article.getTitle());
            tmp.setContent(article.getContent());
            return dao.saveAgainArticle(tmp);
        }        
    }
    
    public Article publishNewArticle(Article article) {
        article.setPublishDate(sdf.format(new Date()));
        article.setPublished(true);
        //tmp.setMyUser(myUser);
        return dao.publishNewArticle(article);
    }
    
    public Article publishSavedArticle(long id, Article article) throws NullPointerException {
        Article tmp = dao.getArticle(id);
        if (tmp != null) {
            tmp.setTitle(article.getTitle());
            tmp.setContent(article.getContent());
            tmp.setPublishDate(sdf.format(new Date()));
            tmp.setSaved(false);
            tmp.setPublished(true);
            return dao.publishSavedArticle(tmp);
        } else {
            throw new NullPointerException();
        }        
    }
    
    public List<Article> getSavedArticles() {
        //return dao.getSavedArticles(1);//todo
        return dao.getSavedArticles();
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
