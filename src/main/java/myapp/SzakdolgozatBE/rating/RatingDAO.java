package myapp.SzakdolgozatBE.rating;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import myapp.SzakdolgozatBE.episode.Episode;
import myapp.SzakdolgozatBE.movie.Movie;
import myapp.SzakdolgozatBE.myUser.MyUser;

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

    public Rating getMovieRating(MyUser user, Movie movie) {
        return (Rating) em.createNamedQuery("getMovieRating")
                .setParameter("user", user).setParameter("movie", movie).getSingleResult();
    }

    public Rating getEpisodeRating(MyUser user, Episode episode) {
        return (Rating) em.createNamedQuery("getEpisodeRating")
                .setParameter("user", user).setParameter("episode", episode).getSingleResult();
    }
}
