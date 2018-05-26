package myapp.SzakdolgozatBE.rating;

import javax.ejb.Stateless;
import javax.inject.Inject;
import myapp.SzakdolgozatBE.episode.EpisodeDAO;
import myapp.SzakdolgozatBE.movie.MovieDAO;

@Stateless
public class RatingService {

    @Inject
    RatingDAO dao;
    
    @Inject
    MovieDAO movieDAO;
    
    @Inject
    EpisodeDAO episodeDAO;

    public Rating addMovieRating(byte rating, long mId) {
        Rating tmp = new Rating();
        tmp.setRating(rating);
        tmp.setMovie(movieDAO.getMovie(mId));
        //tmp.setMyUser(myUser);
        return dao.addRating(tmp);
    }

    public Rating addEpisodeRating(byte rating, long eId) {
        Rating tmp = new Rating();
        tmp.setRating(rating);
        tmp.setEpisode(episodeDAO.getEpisode(eId));
        //tmp.setMyUser(myUser);
        return dao.addRating(tmp);
    }

    public Rating getMovieRating(long mId) throws NullPointerException {
        return dao.getMovieRating(1, mId);//todo
    }

    public Rating getEpisodeRating(long eId) throws NullPointerException {
        return dao.getEpisodeRating(1, eId);//todo
    }
}
