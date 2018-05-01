package myapp.SzakdolgozatBE.article;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

@RequestScoped
public class ArticleDAO {
    
    EntityManager em = Persistence.createEntityManagerFactory("SzakdolgozatPU").createEntityManager();
    
    public Article add(Article article) {
        em.getTransaction().begin();
        em.persist(article);
        em.getTransaction().commit();
        return article;
    }
    
    public List<Article> getAll() {
        return em.createNamedQuery("getAll").getResultList();
    }
    
    public Article get(long id) throws NullPointerException {
        Article tmp = em.find(Article.class, id);
        if(tmp != null)    
                return tmp;
        else
            throw new NullPointerException();
    }
}
