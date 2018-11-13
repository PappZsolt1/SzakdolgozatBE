package myapp.SzakdolgozatBE.rating;

import javax.ejb.Stateless;
import javax.inject.Inject;
import myapp.SzakdolgozatBE.episode.Episode;
import myapp.SzakdolgozatBE.episode.EpisodeDAO;
import myapp.SzakdolgozatBE.movie.Movie;
import myapp.SzakdolgozatBE.movie.MovieDAO;
import myapp.SzakdolgozatBE.myUser.MyUserDAO;

@Stateless
public class RatingService {

    @Inject
    RatingDAO dao;
    
    @Inject
    MovieDAO movieDao;
    
    @Inject
    EpisodeDAO episodeDao;
    
    @Inject
    MyUserDAO myUserDao;

    public Rating addMovieRating(byte rating, long movieId) { //these needed?
        Rating tmp = new Rating();
        tmp.setRating(rating);
        tmp.setMovie(movieDao.getMovie(movieId));
        //tmp.setMyUser(myUser);
        return dao.addRating(tmp);
    }

    public Rating addEpisodeRating(byte rating, long episodeId) {
        Rating tmp = new Rating();
        tmp.setRating(rating);
        tmp.setEpisode(episodeDao.getEpisode(episodeId));
        //tmp.setMyUser(myUser);
        return dao.addRating(tmp);
    }

    public Rating getMovieRating(long movieId) throws NullPointerException {
        Movie tmp = movieDao.getMovie(movieId);
        if (tmp != null) {
            return dao.getMovieRating(myUserDao.getMyUser(1), movieDao.getMovie(movieId));//todo
        } else {
            throw new NullPointerException();
        }
    }

    public Rating getEpisodeRating(long episodeId) throws NullPointerException {
        Episode tmp = episodeDao.getEpisode(episodeId);
        if (tmp != null) {
            return dao.getEpisodeRating(myUserDao.getMyUser(1), episodeDao.getEpisode(episodeId));//todo
        } else {
            throw new NullPointerException();
        }
    }
}
