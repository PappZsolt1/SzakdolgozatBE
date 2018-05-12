package myapp.SzakdolgozatBE.movie;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import myapp.SzakdolgozatBE.ageClassification.AgeClassificationService;
import myapp.SzakdolgozatBE.genre.GenreService;

@Stateless
public class MovieService {
    
    @Inject MovieDAO dao;
    @Inject AgeClassificationService ageClassificationService;
    @Inject GenreService genreService;
    
    public Movie addMovie(String title, int budget, int length, int releaseYear, byte[] coverPicture, long ageClassificationId, long genreId) {
        Movie tmp = new Movie();
        tmp.setTitle(title);
        tmp.setBudget(budget);
        tmp.setLength(length);
        tmp.setReleaseYear(releaseYear);
        tmp.setCoverPicture(coverPicture);
        tmp.setAgeClassification(ageClassificationService.getAgeClassification(ageClassificationId));
        tmp.setGenre(genreService.getGenre(genreId));
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
    
    public Movie modifyMovie(long id, String title, int budget, int length, int releaseYear, byte[] coverPicture, long ageClassificationId, long genreId) throws NullPointerException {
        Movie tmp = new Movie();
        tmp.setTitle(title);
        tmp.setBudget(budget);
        tmp.setLength(length);
        tmp.setReleaseYear(releaseYear);
        tmp.setCoverPicture(coverPicture);
        tmp.setAgeClassification(ageClassificationService.getAgeClassification(ageClassificationId));
        tmp.setGenre(genreService.getGenre(genreId));
        return dao.modifyMovie(id, tmp);
    }
    
    public void changeRating(long id, int rating) {
        dao.changeRating(id, rating);
    }
}
