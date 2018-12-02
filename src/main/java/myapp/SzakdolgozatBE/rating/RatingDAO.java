package myapp.SzakdolgozatBE.rating;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import myapp.SzakdolgozatBE.episode.Episode;
import myapp.SzakdolgozatBE.movie.Movie;

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

    public boolean canRateThisMovie(String username, Movie movie) {
        return em.createNamedQuery("canRateThisMovie")
                .setParameter("username", username).setParameter("movie", movie).getResultList().isEmpty();
    }

    public boolean canRateThisEpisode(String username, Episode episode) {
        return em.createNamedQuery("canRateThisEpisode")
                .setParameter("username", username).setParameter("episode", episode).getResultList().isEmpty();
    }
}
