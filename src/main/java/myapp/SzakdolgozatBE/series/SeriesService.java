package myapp.SzakdolgozatBE.series;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import myapp.SzakdolgozatBE.ageClassification.AgeClassificationService;
import myapp.SzakdolgozatBE.genre.GenreService;

@Stateless
public class SeriesService {

    @Inject
    SeriesDAO dao;
    
    @Inject
    AgeClassificationService ageClassificationService;
    
    @Inject
    GenreService genreService;

    public Series addSeries(Series series) {
        return dao.addSeries(series);
    }

    public Series getSeries(long id) throws NullPointerException {
        Series tmp = dao.getSeries(id);
        if (tmp == null) {
            throw new NullPointerException();
        } else {
            return tmp;
        }
    }

    public List<Series> getAllSeries() {
        return dao.getAllSeries();
    }

    public void deleteSeries(long id) throws NullPointerException {
        Series tmp = dao.getSeries(id);
        if (tmp != null) {
            dao.deleteSeries(id);
        } else {
            throw new NullPointerException();
        }
    }

    public Series modifySeries(long id, Series series) throws NullPointerException {
        Series tmp = dao.getSeries(id);
        if (tmp != null) {
            tmp.setTitle(series.getTitle());
            tmp.setReleaseYear(series.getReleaseYear());
            tmp.setCoverPicture(series.getCoverPicture());
            tmp.setAgeClassification(series.getAgeClassification());
            tmp.setGenre(series.getGenre());
            return dao.modifySeries(tmp);
        } else {
            throw new NullPointerException();
        }
    }

    public void changeRating(long id) {
        dao.changeRating(id);
    }
}
