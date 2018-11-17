package myapp.SzakdolgozatBE.season;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import myapp.SzakdolgozatBE.MyValidationException;
import myapp.SzakdolgozatBE.MyValidator;
import myapp.SzakdolgozatBE.series.Series;
import myapp.SzakdolgozatBE.series.SeriesDAO;

@Stateless
public class SeasonSerivce {

    @Inject
    SeasonDAO dao;
    
    @Inject
    SeriesDAO seriesDao;
    
    MyValidator val = new MyValidator();

    public Season addSeason(long seriesId, Season season) throws MyValidationException {
        if (season.getId() != null || val.validateNumber(season.getNumber(), 0, 100) == false) {
            throw new MyValidationException();
        } else {
            Series tmp = seriesDao.getSeries(seriesId);
            if (tmp == null) {
                throw new MyValidationException();
            } else {
                season.setSeries(tmp);
                dao.addSeason(season);
                tmp.getSeasons().add(season);
                seriesDao.modifySeries(tmp);
                return season;
            }
        }
    }

    public Season getSeason(long id) throws MyValidationException {
        Season tmp = dao.getSeason(id);
        if (tmp == null) {
            throw new MyValidationException();
        } else {
            return tmp;
        }
    }
    
    public boolean checkIfExists(long id) {
        Season tmp = dao.getSeason(id);
        return (tmp != null);
    }

    public List<Season> getSeriesSeasons(long seriesId) throws MyValidationException {
        Series tmp = seriesDao.getSeries(seriesId);
        if (tmp != null) {
            return dao.getSeriesSeasons(tmp);
        } else {
            throw new MyValidationException();
        }
    }

    public void deleteSeason(long seriesId, long id) throws MyValidationException {
        Season tmp1 = dao.getSeason(id);
        if (tmp1 == null || canBeDeleted(id) == false) {
            throw new MyValidationException();
        } else {
            Series tmp2 = seriesDao.getSeries(seriesId);
            if (tmp2 == null) {
                throw new MyValidationException();
            }
            dao.deleteSeason(id);
            tmp2.getSeasons().remove(tmp1);
            seriesDao.modifySeries(tmp2);
        }
    }

    public Season modifySeason(Season season) throws MyValidationException {
        if (season.getId() == null || val.validateNumber(season.getNumber(), 0, 100) == false) {
            throw new MyValidationException();
        }
        Season tmp = dao.getSeason(season.getId());
        if (tmp != null) {
            tmp.setNumber(season.getNumber());
            tmp.setSeries(season.getSeries());
            return dao.modifySeason(tmp);
        } else {
            throw new MyValidationException();
        }
    }
    
    public boolean canBeDeleted(long id) throws MyValidationException {
        Season tmp = dao.getSeason(id);
        if (tmp == null) {
            throw new MyValidationException();
        } else {
            return tmp.getEpisodes().isEmpty();
        }
    }
}
