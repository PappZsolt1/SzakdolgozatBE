package myapp.SzakdolgozatBE.movie;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RequestScoped
public class MovieDAO {

    @PersistenceContext(unitName = "SzakdolgozatPU")
    EntityManager em;

    public Movie addMovie(Movie movie) {
        em.getTransaction().begin();
        em.persist(movie);
        em.getTransaction().commit();
        return movie;
    }

    public Movie getMovie(long id) {
        return em.find(Movie.class, id);        
    }

    public List<Movie> getAllMovies() {
        return em.createNamedQuery("getAllMovies").getResultList();
    }

    public void deleteMovie(long id) {
        em.getTransaction().begin();
        em.remove(this.getMovie(id));
        em.getTransaction().commit();
    }

    public Movie modifyMovie(Movie movie) throws NullPointerException {
        em.getTransaction().begin();
        em.merge(movie);
        em.getTransaction().commit();
        return movie;
    }

    public void changeRating(long id, int rating) {
        Movie tmp = this.getMovie(id);
        tmp.setRating(rating);
        em.getTransaction().begin();
        em.merge(tmp);
        em.getTransaction().commit();
    }
}
