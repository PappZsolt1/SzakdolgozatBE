package myapp.SzakdolgozatBE.article;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ArticleService {
    
    @EJB ArticleDAO dao;
    
    public Article add(long userId, String title, String content) {
        Article tmp = new Article();
        
    }
    
}
