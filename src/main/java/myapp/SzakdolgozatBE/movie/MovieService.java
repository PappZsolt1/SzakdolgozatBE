package myapp.SzakdolgozatBE.movie;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import myapp.SzakdolgozatBE.ageClassification.AgeClassificationService;
import myapp.SzakdolgozatBE.genre.GenreService;

@Stateless
public class MovieService {

    @Inject
    MovieDAO dao;
    
    @Inject
    AgeClassificationService ageClassificationService;
    
    @Inject
    GenreService genreService;
    
    public Movie addMovie(Movie movie) {
        return dao.addMovie(movie);
    }

    public Movie getMovie(long id) throws NullPointerException {
        Movie tmp = dao.getMovie(id);
        if (tmp == null) {
            throw new NullPointerException();
        } else {
            return tmp;
        }
    }

    public List<Movie> getAllMovies() {
        return dao.getAllMovies();
    }

    public void deleteMovie(long id) throws NullPointerException {
        Movie tmp = dao.getMovie(id);
        if (tmp == null) {
            throw new NullPointerException();
        } else {
            dao.deleteMovie(id);
        }
    }

    public Movie modifyMovie(Movie movie) throws NullPointerException {
        Movie tmp = dao.getMovie(movie.getId());
        if (tmp != null) {
            tmp.setTitle(movie.getTitle());
            tmp.setBudget(movie.getBudget());
            tmp.setmLength(movie.getmLength());
            tmp.setReleaseYear(movie.getReleaseYear());
            tmp.setCoverPicture(movie.getCoverPicture());
            tmp.setAgeClassification(movie.getAgeClassification());
            tmp.setGenre(movie.getGenre());
            return dao.modifyMovie(tmp);
        } else {
            throw new NullPointerException();
        }
        
    }

    public void changeRating(long id, int rating) throws NullPointerException {
        Movie tmp = dao.getMovie(id);
        if (tmp != null) {
            int numberOfRatings = tmp.getNumberOfRatings();
            int sumOfRatings = tmp.getSumOfRatings();
            tmp.setNumberOfRatings(numberOfRatings + 1);
            tmp.setSumOfRatings(sumOfRatings + rating);
            tmp.setRating(sumOfRatings / numberOfRatings);
            dao.changeRating(tmp);
        } else {
            throw new NullPointerException();
        }
    }
}
