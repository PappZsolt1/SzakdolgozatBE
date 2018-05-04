package myapp.SzakdolgozatBE.article;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ArticleService {
    
    @Inject ArticleDAO dao;
    
    public Article addArticle(String title, String content) {
        Article tmp = new Article();
        tmp.setTitle(title);
        tmp.setContent(content);
        tmp.setPublishDate(new Date());
        return dao.addArticle(tmp);
    }
    //user elérés szerverről az aktuális bejelentkező
    
    public List<Article> getAllArticles() {
        return dao.getAllArticles();
    }
    
    public Article getArticle(long id) throws NullPointerException {
        return dao.getArticle(id);
    }
}
