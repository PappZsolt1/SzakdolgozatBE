package myapp.SzakdolgozatBE.comment;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RequestScoped
public class CommentDAO {
    
    @PersistenceContext(unitName = "SzakdolgozatPU")
    EntityManager em;
    
    public Comment addComment(Comment comment) {
        em.getTransaction().begin();
        em.persist(comment);
        em.getTransaction().commit();
        return comment;
    }
    
    /*public List<Comment> getxxxComments () {
        //todo
    }*/
}
