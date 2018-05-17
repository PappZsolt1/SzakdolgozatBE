package myapp.SzakdolgozatBE.article;

import java.util.Date;
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

    public Article publishNewArticle(Article article) {
        em.getTransaction().begin();
        em.persist(article);
        em.getTransaction().commit();
        return article;
    }

    public Article publishSavedArticle(Article article, long id) {
        Article tmp = this.getArticle(id);
        tmp.setTitle(article.getTitle());
        tmp.setContent(article.getContent());
        tmp.setPublishDate(new Date());
        tmp.setSaved(false);
        tmp.setPublished(true);
        em.getTransaction().begin();
        em.merge(tmp);
        em.getTransaction().commit();
        return tmp;
    }

    public List<Article> getSavedArticles() {
        return em.createNamedQuery("getSavedArticles").getResultList();
    }

    public List<Article> getPublishedArticles() {
        return em.createNamedQuery("getPublishedArticles").getResultList();
    }

    public void deleteArticle(long id) {
        em.getTransaction().begin();
        em.remove(this.getArticle(id));
        em.getTransaction().commit();
    }

    public Article getArticle(long id) throws NullPointerException {
        Article tmp = em.find(Article.class, id);
        if (tmp != null) {
            return tmp;
        } else {
            throw new NullPointerException();
        }
    }
}
