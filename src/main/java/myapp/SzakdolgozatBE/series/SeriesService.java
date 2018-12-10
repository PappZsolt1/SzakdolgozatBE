package myapp.SzakdolgozatBE.series;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import myapp.SzakdolgozatBE.MyValidationException;
import myapp.SzakdolgozatBE.MyValidator;
import myapp.SzakdolgozatBE.Wrapper;
import myapp.SzakdolgozatBE.ageClassification.AgeClassificationDAO;
import myapp.SzakdolgozatBE.episode.Episode;
import myapp.SzakdolgozatBE.genre.GenreDAO;
import myapp.SzakdolgozatBE.season.Season;

@Stateless
public class SeriesService {

    @Inject
    SeriesDAO dao;
    
    @Inject
    AgeClassificationDAO ageClassificationDao;
    
    @Inject
    GenreDAO genreDao;
    
    MyValidator val = new MyValidator();

    public Series addSeries(Series series) throws MyValidationException {
        if (series.getId() != null || validateSeries(series) == false) {
            throw new MyValidationException();
        } else {
            return dao.addSeries(series);
        }
    }

    public Series getSeries(long id) throws MyValidationException {
        Series tmp = dao.getSeries(id);
        if (tmp == null) {
            throw new MyValidationException();
        } else {
            int numberOfRatings = 0;
            double sumOfRatings = 0;
            for (Season season : tmp.getSeasons()) {
                for (Episode episode : season.getEpisodes()) {
                    if (episode.getRating() > 0) {
                    numberOfRatings++;
                    sumOfRatings += episode.getRating();
                    }
                }
            }
            tmp.setRating(sumOfRatings / (double)numberOfRatings);
            return tmp;
        }
    }
    
    public boolean checkIfExists(long id) {
        Series tmp = dao.getSeries(id);
        return (tmp != null);
    }
    
    public Wrapper getResultSeries(int page, int size, String title) throws MyValidationException {
        if (page < 1 || val.validateSize(size) == false || val.validateText(title, 200) == false) {
            throw new MyValidationException();
        }
        int offset = (page - 1) * size;
        List<Series> results = dao.getResultSeries(offset, size, title);
        long total = dao.getNumberOfResultSeries(title);
        if (total > 0 && offset >= total) {
            throw new MyValidationException();
        }
        return new Wrapper(results, total);
    }

    public void deleteSeries(long id) throws MyValidationException {
        Series tmp = dao.getSeries(id);
        if (tmp != null && canBeDeleted(id) == true) {
            dao.deleteSeries(id);
        } else {
            throw new MyValidationException();
        }
    }

    public Series modifySeries(Series series) throws MyValidationException {
        if (series.getId() == null
                || validateSeries(series) == false
                || dao.getSeries(series.getId()) == null) {
            throw new MyValidationException();
        } else {
            return dao.modifySeries(series);
        }
    }
    
    public boolean canBeDeleted(long id) throws MyValidationException {
        Series tmp = dao.getSeries(id);
        if (tmp == null) {
            throw new MyValidationException();
        } else {
            return tmp.getSeasons().isEmpty();
        }
    }
    
    public boolean validateSeries(Series series) {
        return (ageClassificationDao.getAgeClassification(series.getAgeClassification().getId()) != null
                && genreDao.getGenre(series.getGenre().getId()) != null
                && val.validateText(series.getTitle(), 200) == true
                && val.validateNumber(series.getReleaseYear(), 1850, 2100) == true
                && val.validateText(series.getImageUrl(), 1000) == true);
    }
}
