package myapp.SzakdolgozatBE.series;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import myapp.SzakdolgozatBE.MyValidationException;
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

    public Series addSeries(Series series) throws MyValidationException {
        if (series.getId() != null ||
                ageClassificationDao.getAgeClassification(series.getAgeClassification().getId()) == null ||
                genreDao.getGenre(series.getGenre().getId()) == null ||
                series.getTitle().matches("^\\S.*\\S$|^\\S$") == false ||
                series.getTitle().length() > 100 ||
                series.getReleaseYear() < 1850 || series.getReleaseYear() > 2100) { //photo
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
            return tmp;
        }
    }

    public List<Series> getAllSeries() {
        return dao.getAllSeries();
    }

    public void deleteSeries(long id) throws MyValidationException {
        Series tmp = dao.getSeries(id);
        if (tmp != null) {
            dao.deleteSeries(id);
        } else {
            throw new MyValidationException();
        }
    }

    public Series modifySeries(Series series) throws MyValidationException {
        if (series.getId() != null ||
                ageClassificationDao.getAgeClassification(series.getAgeClassification().getId()) == null ||
                genreDao.getGenre(series.getGenre().getId()) == null ||
                series.getTitle().matches("^\\S.*\\S$|^\\S$") == false ||
                series.getTitle().length() > 100 ||
                series.getReleaseYear() < 1850 || series.getReleaseYear() > 2100) {
            throw new MyValidationException();
        } else if (dao.getSeries(series.getId()) == null) {
            throw new MyValidationException();
        } else {
            return dao.modifySeries(series);
        }
    }

    public void changeRating(long id) throws MyValidationException {//todo
        Series tmp = dao.getSeries(id);
        double numberOfRatings = 0;
        int sumOfRatings = 0;
        if (tmp != null) {
            for (Season season : tmp.getSeasons()) {
                for (Episode episode: season.getEpisodes()) {
                    numberOfRatings++;
                    sumOfRatings += episode.getRating();
                }
            }
            tmp.setRating(sumOfRatings / numberOfRatings);
            dao.changeRating(tmp);
        } else {
            throw new MyValidationException();
        }
    }
}
