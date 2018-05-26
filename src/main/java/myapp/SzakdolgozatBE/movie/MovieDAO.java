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

    public Movie getMovie(long id) throws NullPointerException {
        Movie tmp = em.find(Movie.class, id);
        if (tmp != null) {
            return tmp;
        } else {
            throw new NullPointerException();
        }
    }

    public List<Movie> getAllMovies() {
        return em.createNamedQuery("getAllMovies").getResultList();
    }

    public void deleteMovie(long id) throws NullPointerException {
        em.getTransaction().begin();
        em.remove(this.getMovie(id));
        em.getTransaction().commit();
    }

    public Movie modifyMovie(long id, Movie movie) throws NullPointerException {
        Movie tmp = this.getMovie(id);
        if (tmp != null) {
            tmp.setAgeClassification(movie.getAgeClassification());
            tmp.setBudget(movie.getBudget());
            tmp.setCoverPicture(movie.getCoverPicture());
            tmp.setGenre(movie.getGenre());
            tmp.setLength(movie.getLength());
            tmp.setReleaseYear(movie.getReleaseYear());
            tmp.setTitle(movie.getTitle());
            em.getTransaction().begin();
            em.merge(tmp);
            em.getTransaction().commit();
            return tmp;
        } else {
            throw new NullPointerException();
        }
    }

    public void changeRating(long id, int rating) {
        Movie tmp = this.getMovie(id);
        tmp.setRating(rating);
        em.getTransaction().begin();
        em.merge(tmp);
        em.getTransaction().commit();
    }
}
