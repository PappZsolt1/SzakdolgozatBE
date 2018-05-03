package myapp.SzakdolgozatBE.article;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import myapp.SzakdolgozatBE.user.User;

@Stateless
public class ArticleService {
    
    @Inject ArticleDAO dao;
    
    public Article add(User user, String title, String content) {
        Article tmp = new Article();
        tmp.setUser(user);
        tmp.setTitle(title);
        tmp.setContent(content);
        return dao.add(tmp);
    }
    
    public List<Article> getAll() {
        return dao.getAll();
    }
    
    public Article get(long id) throws NullPointerException {
        return dao.get(id);
    }
}
