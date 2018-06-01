package myapp.SzakdolgozatBE.episode;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import myapp.SzakdolgozatBE.season.SeasonDAO;

@Stateless
public class EpisodeService {

    @Inject
    EpisodeDAO dao;
    
    @Inject
    SeasonDAO seasonDAO;
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy. MM. dd.");

    public Episode addEpisode(Episode episode) {
        return dao.addEpisode(episode);
    }

    public Episode getEpisode(long id) throws NullPointerException {
        Episode tmp = dao.getEpisode(id);
        if (tmp == null) {
            throw new NullPointerException();
        } else {
            return tmp;
        }
    }

    public List<Episode> getSeasonEpisodes(long seasonId) {
        return dao.getSeasonEpisodes(seasonDAO.getSeason(seasonId));
    }

    public void deleteEpisode(long id) throws NullPointerException {
        Episode tmp = dao.getEpisode(id);
        if (tmp == null) {
            throw new NullPointerException();
        } else {
            dao.deleteEpisode(id);
        }
    }

    public Episode modifyEpisode(long id, Episode episode) throws NullPointerException {
        Episode tmp = dao.getEpisode(id);
        if (tmp != null) {
            tmp.setTitle(episode.getTitle());
            tmp.setReleaseDate(episode.getReleaseDate());
            tmp.setLength(episode.getLength());
            tmp.setSeason(episode.getSeason());
            tmp.setActors(episode.getActors());
            return dao.modifyEpisode(tmp);
        } else {
            throw new NullPointerException();
        }
    }

    public void changeRating(long id, int rating) throws NullPointerException {
         Episode tmp = dao.getEpisode(id);
        if (tmp != null) {
            int numberOfRatings = tmp.getNumberOfRatings();
            int sumOfRatings = tmp.getSumOfRatings();
            tmp.setNumberOfRatings(numberOfRatings + 1);
            tmp.setSumOfRatings(sumOfRatings + rating);
            tmp.setRating(sumOfRatings / numberOfRatings);
            dao.changeRating(tmp);
        } else {
            throw new NullPointerException();
        }
    }
}
