package myapp.SzakdolgozatBE.comment;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import myapp.SzakdolgozatBE.actor.Actor;
import myapp.SzakdolgozatBE.article.Article;
import myapp.SzakdolgozatBE.episode.Episode;
import myapp.SzakdolgozatBE.movie.Movie;
import myapp.SzakdolgozatBE.topic.Topic;

@RequestScoped
public class CommentDAO {
    
    @PersistenceContext(unitName = "SzakdolgozatPU")
    EntityManager em;
    
    public Comment getComment(long id) {
        return em.find(Comment.class, id);        
    }
    
    public Comment addComment(Comment comment) {
        em.getTransaction().begin();
        em.persist(comment);
        em.getTransaction().commit();
        return comment;
    }
    
    public List<Comment> getMovieComments(int offset, int limit, Movie movie) {
        return em.createNamedQuery("getMovieComments").setParameter("movie", movie)
                .setFirstResult(offset).setMaxResults(limit).getResultList();
    }
    
    public long getNumberOfMovieComments(Movie movie) {
        return (long)em.createNamedQuery("getNumberOfMovieComments").setParameter("movie", movie).getSingleResult();
    }
    
    public List<Comment> getEpisodeComments(int offset, int limit, Episode episode) {
        return em.createNamedQuery("getEpisodeComments").setParameter("episode", episode)
                .setFirstResult(offset).setMaxResults(limit).getResultList();
    }
    
    public long getNumberOfEpisodeComments(Episode episode) {
        return (long)em.createNamedQuery("getNumberOfEpisodeComments").setParameter("episode", episode).getSingleResult();
    }
    
    public List<Comment> getActorComments(int offset, int limit, Actor actor) {
        return em.createNamedQuery("getActorComments").setParameter("actor", actor)
                .setFirstResult(offset).setMaxResults(limit).getResultList();
    }
    
    public long getNumberOfActorComments(Actor actor) {
        return (long)em.createNamedQuery("getNumberOfActorComments").setParameter("actor", actor).getSingleResult();
    }
    
    public List<Comment> getArticleComments(int offset, int limit, Article article) {
        return em.createNamedQuery("getArticleComments").setParameter("article", article)
                .setFirstResult(offset).setMaxResults(limit).getResultList();
    }
    
    public long getNumberOfArticleComments(Article article) {
        return (long)em.createNamedQuery("getNumberOfArticleComments").setParameter("article", article).getSingleResult();
    }
    
    public List<Comment> getTopicComments(int offset, int limit, Topic topic) {
        return em.createNamedQuery("getTopicComments").setParameter("topic", topic)
                .setFirstResult(offset).setMaxResults(limit).getResultList();
    }
    
    public List<Comment> getAllTopicComments(Topic topic) {
        return em.createNamedQuery("getAllTopicComments").setParameter("topic", topic).getResultList();
    }
    
    public long getNumberOfTopicComments(Topic topic) {
        return (long)em.createNamedQuery("getNumberOfTopicComments").setParameter("topic", topic).getSingleResult();
    }
    
    public Comment moderateComment(Comment comment) {
        em.getTransaction().begin();
        em.merge(comment);
        em.getTransaction().commit();
        return comment;
    }
    
    public void deleteComment(long id) {
        em.getTransaction().begin();
        em.remove(this.getComment(id));
        em.getTransaction().commit();
    }
}
