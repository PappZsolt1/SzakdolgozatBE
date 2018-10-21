package myapp.SzakdolgozatBE.season;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import myapp.SzakdolgozatBE.series.Series;
import myapp.SzakdolgozatBE.series.SeriesDAO;

@Stateless
public class SeasonSerivce {

    @Inject
    SeasonDAO dao;
    
    @Inject
    SeriesDAO seriesDAO;

    public Season addSeason(Season season) throws NullPointerException {
        Series tmp = seriesDAO.getSeries(season.getSeries().getId());
        if (tmp != null) {            
            return dao.addSeason(season);
        } else {
            throw new NullPointerException();
        }
    }

    public Season getSeason(long id) throws NullPointerException {
        Season tmp = dao.getSeason(id);
        if (tmp == null) {
            throw new NullPointerException();
        } else {
            return tmp;
        }
    }

    public List<Season> getSeriesSeasons(long seriesId) throws NullPointerException {
        Series tmp = seriesDAO.getSeries(seriesId);
        if (tmp != null) {
            return dao.getSeriesSeasons(tmp);
        } else {
            throw new NullPointerException();
        }
    }

    public void deleteSeason(long id) throws NullPointerException {
        Season tmp = dao.getSeason(id);
        if (tmp != null) {
            dao.deleteSeason(id);
        } else {
            throw new NullPointerException();
        }
    }

    public Season modifySeason(Season season) throws NullPointerException {
        Season tmp = dao.getSeason(season.getId());
        if (tmp != null) {
            tmp.setNumber(season.getNumber());
            tmp.setSeries(season.getSeries());
            return dao.modifySeason(tmp);
        } else {
            throw new NullPointerException();
        }
    }
}
