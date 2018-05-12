package myapp.SzakdolgozatBE.episode;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import myapp.SzakdolgozatBE.season.SeasonDAO;

@Stateless
public class EpisodeService {
    
    @Inject EpisodeDAO dao;
    @Inject SeasonDAO seasonDAO;
    
    public Episode addEpisode(String title, Date releaseDate, int length, long seasonId) {
        Episode tmp = new Episode();
        tmp.setLength(length);
        tmp.setReleaseDate(releaseDate);
        tmp.setTitle(title);
        tmp.setSeason(seasonDAO.getSeason(seasonId));
        return dao.addEpisode(tmp);
    }
    
    public Episode getEpisode(long id) throws NullPointerException {
        return dao.getEpisode(id);
    }
    
    public List<Episode> getSeasonEpisodes(long seasonId) {
        return dao.getSeasonEpisodes(seasonDAO.getSeason(seasonId));
    }
    
    public void deleteEpisode(long id) throws NullPointerException {
        dao.deleteEpisode(id);
    }
    
    public Episode modifyEpisode(long id, String title, Date releaseDate, int length, long seasonId) throws NullPointerException {
        Episode tmp = new Episode();
        tmp.setTitle(title);
        tmp.setReleaseDate(releaseDate);
        tmp.setLength(length);
        tmp.setSeason(seasonDAO.getSeason(seasonId));
        return dao.modifyEpisode(id, tmp);
    }
    
    public void changeRating(long id, int rating) {
        dao.changeRating(id, rating);
    }
}
