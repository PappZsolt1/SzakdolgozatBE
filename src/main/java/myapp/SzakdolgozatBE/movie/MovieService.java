package myapp.SzakdolgozatBE.movie;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import myapp.SzakdolgozatBE.MyValidationException;
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
    
    public Movie addMovie(Movie movie) throws MyValidationException {
        if (movie.getId() != null ||
                movie.getTitle().matches("^\\S.*\\S$|^\\S$") == false ||
                movie.getTitle().length() > 200 ||
                ageClassificationDao.getAgeClassification(movie.getAgeClassification().getId()) == null ||
                genreDao.getGenre(movie.getGenre().getId()) == null ||
                movie.getRatings() != null ||
                movie.getBudget() < 0 || movie.getBudget() > 1000000000 ||
                movie.getReleaseYear() < 1850 || movie.getReleaseYear() > 2100 ||
                validateLength(movie.getmLength()) == false) {// pic
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
        if (movie.getId() == null ||
                movie.getTitle().matches("^\\S.*\\S$|^\\S$") == false ||
                movie.getTitle().length() > 200 ||
                ageClassificationDao.getAgeClassification(movie.getAgeClassification().getId()) == null ||
                genreDao.getGenre(movie.getGenre().getId()) == null ||
                movie.getRatings() != null ||
                movie.getBudget() < 0 || movie.getBudget() > 1000000000 ||
                movie.getReleaseYear() < 1850 || movie.getReleaseYear() > 2100 ||
                validateLength(movie.getmLength()) == false) {// pic
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
    
    public boolean validateLength(String mLength) {
        if (mLength.matches("^[0-9]{1,3} óra [0-9]{1,2} perc$|^[0-9]{1,2} perc$") == false) {
            return false;
        }
        if (mLength.length() > 10) {
            int hour;
            try {
                hour = Integer.parseInt(mLength.substring(0, mLength.indexOf("ó") - 1));
            } catch (NumberFormatException e) {
                return false;
            }
            if (hour > 100 || hour < 0) {
                return false;
            }
            int minute;
            try {
                minute = Integer.parseInt(mLength.substring(mLength.indexOf("a") + 2, mLength.indexOf("p") - 1));
            } catch (NumberFormatException e) {
                return false;
            }
            if (minute > 60 || minute < 0) {
                return false;
            }
        } else {
            int minute;
            try {
                minute = Integer.parseInt(mLength.substring(0, mLength.indexOf("p") - 1));
            } catch (NumberFormatException e) {
                return false;
            }
            if (minute > 60 || minute < 0) {
                return false;
            }
        }
        return true;
    }
}
