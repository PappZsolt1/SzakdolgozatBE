package myapp.SzakdolgozatBE.episode;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import myapp.SzakdolgozatBE.season.Season;

@Stateless
public class EpisodeService {
    
    @Inject EpisodeDAO dao;
    
    public Episode addEpisode(String title, Date releaseDate, int length, Season season) {
        Episode tmp = new Episode();
        tmp.setLength(length);
        tmp.setReleaseDate(releaseDate);
        tmp.setTitle(title);
        tmp.setSeason(season);
        return dao.addEpisode(tmp);
    }
    
    public Episode getEpisode(long id) throws NullPointerException {
        return dao.getEpisode(id);
    }
    
    public List<Episode> getSeasonEpisodes(Season season) {
        return dao.getSeasonEpisodes(season);
    }
    
    public void deleteEpisode(long id) throws NullPointerException {
        dao.deleteEpisode(id);
    }
    
    public Episode modifyEpisode(long id, String title, Date releaseDate, int length, Season season) throws NullPointerException {
        Episode tmp = new Episode();
        tmp.setTitle(title);
        tmp.setReleaseDate(releaseDate);
        tmp.setLength(length);
        tmp.setSeason(season);
        return dao.modifyEpisode(id, tmp);
    }
    
    public void changeRating(long id, double rating) {
        dao.changeRating(id, rating);
    }
}
