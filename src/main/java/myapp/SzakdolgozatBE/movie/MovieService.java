package myapp.SzakdolgozatBE.movie;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import myapp.SzakdolgozatBE.enums.AgeClassification;
import myapp.SzakdolgozatBE.enums.Genre;

@Stateless
public class MovieService {
    
    @Inject MovieDAO dao;
    
    public Movie addMovie(String title, int budget, int length, int releaseYear, byte[] coverPicture, AgeClassification ageClassification, Genre genre) {
        Movie tmp = new Movie();
        tmp.setTitle(title);
        tmp.setBudget(budget);
        tmp.setLength(length);
        tmp.setReleaseYear(releaseYear);
        tmp.setCoverPicture(coverPicture);
        tmp.setAgeClassification(ageClassification);
        tmp.setGenre(genre);
        return dao.addMovie(tmp);
    }
    
    public Movie getMovie(long id) throws NullPointerException {
        return dao.getMovie(id);
    }
    
    public List<Movie> getAllMovies() {
        return dao.getAllMovies();
    }
    
    public void deleteMovie(long id) throws NullPointerException {
        dao.deleteMovie(id);
    }
    
    public Movie modifyMovie(long id, String title, int budget, int length, int releaseYear, byte[] coverPicture, AgeClassification ageClassification, Genre genre) throws NullPointerException {
        Movie tmp = new Movie();
        tmp.setTitle(title);
        tmp.setBudget(budget);
        tmp.setLength(length);
        tmp.setReleaseYear(releaseYear);
        tmp.setCoverPicture(coverPicture);
        tmp.setAgeClassification(ageClassification);
        tmp.setGenre(genre);
        return dao.modifyMovie(id, tmp);
    }
    
    public void changeRating(long id, int rating) {
        dao.changeRating(id, rating);
    }
}
