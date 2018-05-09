package myapp.SzakdolgozatBE.series;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import myapp.SzakdolgozatBE.ageClassification.AgeClassification;
import myapp.SzakdolgozatBE.genre.Genre;

@Stateless
public class SeriesService {
    
    @Inject SeriesDAO dao;
    
    public Series addSeries(String title, int releaseYear, byte[] coverPicture, AgeClassification ageClassification, Genre genre) {
        Series tmp = new Series();
        tmp.setTitle(title);
        tmp.setReleaseYear(releaseYear);
        tmp.setCoverPicture(coverPicture);
        tmp.setAgeClassification(ageClassification);
        tmp.setGenre(genre);
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
    
    public Series modifySeries(long id, String title, int releaseYear, byte[] coverPicture, AgeClassification ageClassification, Genre genre) throws NullPointerException {
        Series tmp = new Series();
        tmp.setTitle(title);
        tmp.setReleaseYear(releaseYear);
        tmp.setCoverPicture(coverPicture);
        tmp.setAgeClassification(ageClassification);
        tmp.setGenre(genre);
        return dao.modifySeries(id, tmp);
    }
    
    public void changeRating(long id) {
        dao.changeRating(id);
    }
}
