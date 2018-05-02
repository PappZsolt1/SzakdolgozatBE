package myapp.SzakdolgozatBE.episode;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class EpisodeService {
    
    @Inject EpisodeDAO dao;
    
    public Episode addEpisode(String title, int releaseYear, int length) {
        Episode tmp = new Episode();
        tmp.setLength(length);
        tmp.setReleaseYear(releaseYear);
        tmp.setTitle(title);
        return dao.addEpisode(tmp);
    }
    
    public Episode getEpisode(long id) throws NullPointerException {
        return dao.getEpisode(id);
    }
    
    public List<Episode> getSeasonEpisodes() {
        //todo
    }
    
    public void deleteEpisode(long id) throws NullPointerException {
        dao.deleteEpisode(id);
    }
    
    public Episode modifyEpisode(long id, String title, int releaseYear, int length) throws NullPointerException {
        Episode tmp = new Episode();
        tmp.setTitle(title);
        tmp.setReleaseYear(releaseYear);
        tmp.setLength(length);
        return dao.modifyEpisode(id, tmp);
    }
    
    public void changeRating(long id, double rating) {
        dao.changeRating(id, rating);
    }
}
