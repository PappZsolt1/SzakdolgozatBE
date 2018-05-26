package myapp.SzakdolgozatBE.rating;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RequestScoped
public class RatingDAO {

    @PersistenceContext(unitName = "SzakdolgozatPU")
    EntityManager em;
    
    public Rating addRating(Rating rating) {
        em.getTransaction().begin();
        em.persist(rating);
        em.getTransaction().commit();
        return rating;
    }
    
    public Rating getMovieRating(long uId, long mId) throws NullPointerException {
        Rating tmp = (Rating)em.createNamedQuery("getMovieRating").setParameter("uid", uId).setParameter("mid", mId).getSingleResult();
        if (tmp != null) {
            return tmp;
        } else {
            throw new NullPointerException();
        }
    }
    
    public Rating getEpisodeRating(long uId, long eId) throws NullPointerException {
        Rating tmp = (Rating)em.createNamedQuery("getEpisodeRating").setParameter("uid", uId).setParameter("eid", eId).getSingleResult();
        if (tmp != null) {
            return tmp;
        } else {
            throw new NullPointerException();
        }
    }
}
