package myapp.SzakdolgozatBE.movie;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import myapp.SzakdolgozatBE.MyValidationException;
import myapp.SzakdolgozatBE.MyValidator;
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

    public void deleteMovie(long id) throws MyValidationException {
        Movie tmp = dao.getMovie(id);
        if (tmp == null) {
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
                || movie.getRatings() != null
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
}
