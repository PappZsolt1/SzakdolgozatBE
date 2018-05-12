package myapp.SzakdolgozatBE.series;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import myapp.SzakdolgozatBE.ageClassification.AgeClassificationService;
import myapp.SzakdolgozatBE.genre.GenreService;

@Stateless
public class SeriesService {
    
    @Inject SeriesDAO dao;
    @Inject AgeClassificationService ageClassificationService;
    @Inject GenreService genreService;
    
    public Series addSeries(String title, int releaseYear, byte[] coverPicture, long ageClassificationId, long genreId) {
        Series tmp = new Series();
        tmp.setTitle(title);
        tmp.setReleaseYear(releaseYear);
        tmp.setCoverPicture(coverPicture);
        tmp.setAgeClassification(ageClassificationService.getAgeClassification(ageClassificationId));
        tmp.setGenre(genreService.getGenre(genreId));
        return dao.addSeries(tmp);
    }
    
    public Series getSeries(long id) throws NullPointerException {
        return dao.getSeries(id);
    }
    
    public List<Series> getAllSeries() {
        return dao.getAllSeries();
    }
    
    public void deleteSeries(long id) throws NullPointerException {
        dao.deleteSeries(id);
    }
    
    public Series modifySeries(long id, String title, int releaseYear, byte[] coverPicture, long ageClassificationId, long genreId) throws NullPointerException {
        Series tmp = new Series();
        tmp.setTitle(title);
        tmp.setReleaseYear(releaseYear);
        tmp.setCoverPicture(coverPicture);
        tmp.setAgeClassification(ageClassificationService.getAgeClassification(ageClassificationId));
        tmp.setGenre(genreService.getGenre(genreId));
        return dao.modifySeries(id, tmp);
    }
    
    public void changeRating(long id) {
        dao.changeRating(id);
    }
}
