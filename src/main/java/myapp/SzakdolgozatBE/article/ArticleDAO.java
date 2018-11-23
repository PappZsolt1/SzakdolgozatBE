package myapp.SzakdolgozatBE.article;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RequestScoped
public class ArticleDAO {

    @PersistenceContext(unitName = "SzakdolgozatPU")
    EntityManager em;

    public Article saveArticle(Article article) {
        em.getTransaction().begin();
        em.persist(article);
        em.getTransaction().commit();
        return article;
    }
    
    public Article saveAgainArticle(Article article) {
        em.getTransaction().begin();
        em.merge(article);
        em.getTransaction().commit();
        return article;
    }

    public Article publishNewArticle(Article article) {
        em.getTransaction().begin();
        em.persist(article);
        em.getTransaction().commit();
        return article;
    }

    public Article publishSavedArticle(Article article) {
        em.getTransaction().begin();
        em.merge(article);
        em.getTransaction().commit();
        return article;
    }

    public List<Article> getSavedArticles(/*long userId*/) {
        //return em.createNamedQuery("getSavedArticles").setParameter("userId", userId).getResultList();
        return em.createNamedQuery("getSavedArticles").getResultList();
    }

    public List<Article> getPublishedArticles(int offset, int limit) {
        return em.createNamedQuery("getPublishedArticles")
                .setFirstResult(offset).setMaxResults(limit).getResultList();
    }
    
    public long getNumberOfPublishedArticles() {
        return (long)em.createNamedQuery("getNumberOfPublishedArticles").getSingleResult();
    }

    public void deleteArticle(long id) {
        em.getTransaction().begin();
        em.remove(this.getArticle(id));
        em.getTransaction().commit();
    }

    public Article getArticle(long id) {
        return em.find(Article.class, id);        
    }
}
