package myapp.SzakdolgozatBE.season;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import myapp.SzakdolgozatBE.series.SeriesDAO;

@Stateless
public class SeasonSerivce {

    @Inject
    SeasonDAO dao;
    
    @Inject
    SeriesDAO seriesDAO;

    public Season addSeason(int number, long seriesId) {
        Season tmp = new Season();
        tmp.setNumber(number);
        tmp.setSeries(seriesDAO.getSeries(seriesId));
        return dao.addSeason(tmp);
    }

    public Season getSeason(long id) throws NullPointerException {
        return dao.getSeason(id);
    }

    public List<Season> getSeriesSeasons(long seriesId) {
        return dao.getSeriesSeasons(seriesDAO.getSeries(seriesId));
    }

    public void deleteSeason(long id) throws NullPointerException {
        dao.deleteSeason(id);
    }

    public Season modifySeason(long id, int number, long seriesId) throws NullPointerException {
        Season tmp = new Season();
        tmp.setNumber(number);
        tmp.setSeries(seriesDAO.getSeries(seriesId));
        return dao.modifySeason(id, tmp);
    }
}
