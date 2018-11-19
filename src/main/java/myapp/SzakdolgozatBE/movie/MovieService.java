package myapp.SzakdolgozatBE.movie;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import myapp.SzakdolgozatBE.MyValidationException;
import myapp.SzakdolgozatBE.MyValidator;
import myapp.SzakdolgozatBE.actor.Actor;
import myapp.SzakdolgozatBE.actor.ActorDAO;
import myapp.SzakdolgozatBE.ageClassification.AgeClassificationDAO;
import myapp.SzakdolgozatBE.genre.GenreDAO;

@Stateless
public class MovieService {

    @Inject
    MovieDAO dao;
    
    @Inject
    AgeClassificationDAO ageClassificationDao;
    
    @Inject
    GenreDAO genreDao;
    
    @Inject
    ActorDAO actorDao;
    
    MyValidator val = new MyValidator();
    
    public Movie addMovie(Movie movie) throws MyValidationException {
        if (movie.getId() != null
                || val.validateText(movie.getTitle(), 200) == false
                || ageClassificationDao.getAgeClassification(movie.getAgeClassification().getId()) == null
                || genreDao.getGenre(movie.getGenre().getId()) == null
                || movie.getRatings() != null
                || val.validateNumber(movie.getBudget(), 0, 1000000000) == false
                || val.validateNumber(movie.getReleaseYear(), 1850, 2100) == false
                || val.validateLength(movie.getmLength()) == false) {// pic
            throw new MyValidationException();
        } else {
            return dao.addMovie(movie);
        }
    }
    
    public boolean saveMovieActors(long id, long[] actorIds) throws MyValidationException {//todo
        Movie tmp1 = dao.getMovie(id);
        tmp1.getActors().clear();
        if (tmp1 == null) {
            throw new MyValidationException();
        }
        if (actorIds.length == 0) {
            return true;
        }
        for (int i = 0; i < actorIds.length; i++) {
            Actor tmp2 = actorDao.getActor(actorIds[i]);
            if (tmp2 == null) {
                throw new MyValidationException();
            }
            tmp1.getActors().add(tmp2);
        }
        dao.modifyMovie(tmp1);
        return true;
    }

    public Movie getMovie(long id) throws MyValidationException {
        Movie tmp = dao.getMovie(id);
        if (tmp == null) {
            throw new MyValidationException();
        } else {
            return tmp;
        }
    }

    public List<Movie> getAllMovies() {
        return dao.getAllMovies();
    }
    
    public List<Movie> getResultMovies(String title) throws MyValidationException {
        if (val.validateText(title, 200) == false) {
            throw new MyValidationException();
        } else {
            return dao.getResultMovies(title);
        }
    }

    public void deleteMovie(long id) throws MyValidationException {
        Movie tmp = dao.getMovie(id);
        if (tmp == null || canBeDeleted(id) == false) {
            throw new MyValidationException();
        } else {
            dao.deleteMovie(id);
        }
    }

    public Movie modifyMovie(Movie movie) throws MyValidationException {
        if (movie.getId() == null
                || val.validateText(movie.getTitle(), 200) == false
                || ageClassificationDao.getAgeClassification(movie.getAgeClassification().getId()) == null
                || genreDao.getGenre(movie.getGenre().getId()) == null
                || val.validateNumber(movie.getBudget(), 0, 1000000000) == false
                || val.validateNumber(movie.getReleaseYear(), 1850, 2100) == false
                || val.validateLength(movie.getmLength()) == false) {// pic
            throw new MyValidationException();
        } else if (dao.getMovie(movie.getId()) == null) {
            throw new MyValidationException();
        } else {
            return dao.modifyMovie(movie);
        }
        
    }

    public void changeRating(long id, int rating) throws MyValidationException {//todo
        Movie tmp = dao.getMovie(id);
        if (tmp != null) {
            int numberOfRatings = tmp.getNumberOfRatings();
            int sumOfRatings = tmp.getSumOfRatings();
            tmp.setNumberOfRatings(numberOfRatings + 1);
            tmp.setSumOfRatings(sumOfRatings + rating);
            tmp.setRating(sumOfRatings / numberOfRatings);
            dao.changeRating(tmp);
        } else {
            throw new MyValidationException();
        }
    }
    
    public boolean canBeDeleted(long id) throws MyValidationException {
        Movie tmp = dao.getMovie(id);
        if (tmp == null) {
            throw new MyValidationException();
        } else {
            return tmp.getActors().isEmpty();
        }
    }
}
