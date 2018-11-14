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
    SeriesDAO seriesDAO;
    
    MyValidator val = new MyValidator();

    public Season addSeason(Season season) throws MyValidationException {
        if (season.getId() != null || val.validateNumber(season.getNumber(), 0, 100) == false) {
            throw new MyValidationException();
        } else {
            return dao.addSeason(season);
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

    public List<Season> getSeriesSeasons(long seriesId) throws MyValidationException {
        Series tmp = seriesDAO.getSeries(seriesId);
        if (tmp != null) {
            return dao.getSeriesSeasons(tmp);
        } else {
            throw new MyValidationException();
        }
    }

    public void deleteSeason(long id) throws MyValidationException {
        Season tmp = dao.getSeason(id);
        if (tmp != null) {
            dao.deleteSeason(id);
        } else {
            throw new MyValidationException();
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
}
