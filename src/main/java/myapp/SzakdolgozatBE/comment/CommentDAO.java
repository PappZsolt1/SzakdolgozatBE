package myapp.SzakdolgozatBE.comment;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

@RequestScoped
public class CommentDAO {
    
    EntityManager em = Persistence.createEntityManagerFactory("SzakdolgozatPU").createEntityManager();
    
    public Comment addComment(Comment comment) {
        em.getTransaction().begin();
        em.persist(comment);
        em.getTransaction().commit();
        return comment;
    }
    
    public List<Comment> getxxxComments () {
        //todo
    }
}
