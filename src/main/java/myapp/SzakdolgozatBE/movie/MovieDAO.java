package myapp.SzakdolgozatBE.movie;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import myapp.SzakdolgozatBE.ageClassification.AgeClassification;
import myapp.SzakdolgozatBE.genre.Genre;

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
    
    public List<Movie> getResultMovies(int offset, int limit, String title) {
        return em.createNamedQuery("getResultMovies").setParameter("title", title)
                .setFirstResult(offset).setMaxResults(limit).getResultList();
    }
    
    public long getNumberOfResultMovies(String title) {
        return (long)em.createNamedQuery("getNumberOfResultMovies").setParameter("title", title).getSingleResult();
    }

    public void deleteMovie(long id) {
        em.getTransaction().begin();
        em.remove(this.getMovie(id));
        em.getTransaction().commit();
    }

    public Movie modifyMovie(Movie movie) {
        em.getTransaction().begin();
        em.merge(movie);
        em.getTransaction().commit();
        return movie;
    }
    
    public boolean ageClassificationNotUsed(AgeClassification ageClassification) {
        return em.createNamedQuery("getAgeClassificationMovies")
                .setParameter("ageClassification", ageClassification).getResultList().isEmpty();
    }
    
    public boolean genreNotUsed(Genre genre) {
        return em.createNamedQuery("getGenreMovies")
                .setParameter("genre", genre).getResultList().isEmpty();
    }
}
