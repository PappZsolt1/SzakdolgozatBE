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
    
    public List<Comment> getEpisodeComments(Episode episode) {
        return em.createNamedQuery("getEpisodeComments").setParameter("episode", episode).getResultList();
    }
    
    public List<Comment> getActorComments(Actor actor) {
        return em.createNamedQuery("getActorComments").setParameter("actor", actor).getResultList();
    }
    
    public List<Comment> getArticleComments(Article article) {
        return em.createNamedQuery("getArticleComments").setParameter("article", article).getResultList();
    }
    
    public List<Comment> getTopicComments(Topic topic) {
        return em.createNamedQuery("getTopicComments").setParameter("topic", topic).getResultList();
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
