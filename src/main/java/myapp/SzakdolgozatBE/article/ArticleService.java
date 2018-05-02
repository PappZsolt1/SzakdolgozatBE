package myapp.SzakdolgozatBE.article;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import myapp.SzakdolgozatBE.myUser.MyUser;

@Stateless
public class ArticleService {
    
    @EJB ArticleDAO dao;
    
    public Article addArticle(String title, String content) {
        Article tmp = new Article();
        tmp.setTitle(title);
        tmp.setContent(content);
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
