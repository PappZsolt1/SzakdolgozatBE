package myapp.SzakdolgozatBE.article;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import myapp.SzakdolgozatBE.MyValidationException;
import myapp.SzakdolgozatBE.MyValidator;
import myapp.SzakdolgozatBE.Wrapper;

@Stateless
public class ArticleService {
    
    @Inject
    ArticleDAO dao;
    
    MyValidator val = new MyValidator();
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy. MM. dd. HH:mm:ss");
    
    public Article saveArticle(Article article) throws MyValidationException {
        if (validateArticle(article) == false || article.getUsername() != null) {
            throw new MyValidationException();
        } else if (article.getId() == null && article.isSaved() == false) {
            article.setSaved(true);
            return dao.saveArticle(article);
        } else if (article.getId() != null && article.isSaved() == true) {
            Article tmp = dao.getArticle(article.getId());
            if (tmp == null) {
                throw new MyValidationException();
            } else {
                tmp.setTitle(article.getTitle());
                tmp.setContent(article.getContent());
                return dao.saveAgainArticle(tmp);
            }
        } else {
            throw new MyValidationException();
        }
    }     

    
    public Article publishArticle(Article article) throws MyValidationException {
        if (validateArticle(article) == false || article.getUsername() == null) {
            throw new MyValidationException();
        } else if (article.getId() == null && article.isSaved() == false) {
            article.setPublishDate(sdf.format(new Date()));
            article.setPublished(true);
            return dao.publishNewArticle(article);
        } else if (article.getId() != null && article.isSaved() == true) {
            Article tmp = dao.getArticle(article.getId());
            if (tmp == null) {
                throw new MyValidationException();
            } else {
                tmp.setUsername(article.getUsername());
                tmp.setTitle(article.getTitle());
                tmp.setContent(article.getContent());
                tmp.setPublishDate(sdf.format(new Date()));
                tmp.setSaved(false);
                tmp.setPublished(true);
                return dao.publishSavedArticle(tmp);
            }
        } else {
            throw new MyValidationException();
        }
    }
    
    public List<Article> getSavedArticles() {
        return dao.getSavedArticles();
    }
    
    public Wrapper getPublishedArticles(int page, int size) throws MyValidationException {
        if (page < 1 || val.validateSize(size) == false) {
            throw new MyValidationException();
        }
        int offset = (page - 1) * size;
        List<Article> results = dao.getPublishedArticles(offset, size);
        long total = dao.getNumberOfPublishedArticles();
        if (total > 0 && offset >= total) {
            throw new MyValidationException();
        }
        return new Wrapper(results, total);
    }
    
    public void deleteArticle(long id) throws MyValidationException {
        Article tmp = dao.getArticle(id);
        if (tmp != null) {
            dao.deleteArticle(id);
        } else {
            throw new MyValidationException();
        }
    }
    
    public Article getArticle(long id) throws MyValidationException {
        Article tmp = dao.getArticle(id);
        if (tmp != null) {
            return tmp;
        } else {
            throw new MyValidationException();
        }
    }
    
    public boolean validateArticle(Article article) {
        return (val.validateText(article.getTitle(), 200) == true
                && val.validateText(article.getContent(), 60000) == true
                && article.getPublishDate() == null
                && article.isPublished() == false);
    }
}
