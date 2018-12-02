package myapp.SzakdolgozatBE.rating;

import javax.ejb.Stateless;
import javax.inject.Inject;
import myapp.SzakdolgozatBE.MyValidationException;
import myapp.SzakdolgozatBE.episode.Episode;
import myapp.SzakdolgozatBE.episode.EpisodeDAO;
import myapp.SzakdolgozatBE.movie.Movie;
import myapp.SzakdolgozatBE.movie.MovieDAO;

@Stateless
public class RatingService {
    
    @Inject
    RatingDAO dao;
    
    @Inject
    MovieDAO movieDao;
    
    @Inject
    EpisodeDAO episodeDao;

    public boolean canRateThisMovie( long movieId, String username) throws MyValidationException {
        if (username == null || movieDao.getMovie(movieId) == null) {
            throw new MyValidationException();
        } else {
            Movie tmp = movieDao.getMovie(movieId);
            return dao.canRateThisMovie(username, tmp);
        }
    }
    
    public boolean canRateThisEpisode(long episodeId, String username) throws MyValidationException {
        if (username == null || episodeDao.getEpisode(episodeId) == null) {
            throw new MyValidationException();
        } else {
            Episode tmp = episodeDao.getEpisode(episodeId);
            return dao.canRateThisEpisode(username, tmp);
        }
    }
}
