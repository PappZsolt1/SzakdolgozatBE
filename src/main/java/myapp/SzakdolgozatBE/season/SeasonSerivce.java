package myapp.SzakdolgozatBE.season;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import myapp.SzakdolgozatBE.episode.Episode;
import myapp.SzakdolgozatBE.series.Series;

@Stateless
public class SeasonSerivce {
    
    @Inject SeasonDAO dao;
    
    public Season addSeason(int number, List<Episode> episodes, Series series) {
        Season tmp = new Season();
        tmp.setNumber(number);
        tmp.setEpisodes(episodes);
        tmp.setSeries(series);
        return dao.addSeason(tmp);
    }
    
    public Season getSeason(long id) throws NullPointerException {
        return dao.getSeason(id);
    }
    
    public List<Season> getSeriesSeasons(Series series) {
        return dao.getSeriesSeasons(series);
    }
    
    public void deleteSeason(long id) throws NullPointerException {
        dao.deleteSeason(id);
    }
    
    public Season modifySeason(long id, int number, List<Episode> episodes, Series series) throws NullPointerException {
        Season tmp = new Season();
        tmp.setNumber(number);
        tmp.setEpisodes(episodes);
        tmp.setSeries(series);
        return dao.modifySeason(id, tmp);
    }
}
