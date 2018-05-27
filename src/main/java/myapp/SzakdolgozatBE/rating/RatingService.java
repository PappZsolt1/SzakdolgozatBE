package myapp.SzakdolgozatBE.rating;

import javax.ejb.Stateless;
import javax.inject.Inject;
import myapp.SzakdolgozatBE.episode.EpisodeDAO;
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

    public Rating addMovieRating(byte rating, long movieId) {
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
        return dao.getMovieRating(myUserDao.getMyUser(1), movieDao.getMovie(movieId));//todo
    }

    public Rating getEpisodeRating(long episodeId) throws NullPointerException {
        return dao.getEpisodeRating(myUserDao.getMyUser(1), episodeDao.getEpisode(episodeId));//todo
    }
}
