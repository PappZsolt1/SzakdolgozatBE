package myapp.SzakdolgozatBE.episode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import myapp.SzakdolgozatBE.MyValidationException;
import myapp.SzakdolgozatBE.MyValidator;
import myapp.SzakdolgozatBE.season.SeasonDAO;

@Stateless
public class EpisodeService {

    @Inject
    EpisodeDAO dao;
    
    @Inject
    SeasonDAO seasonDAO;
    
    MyValidator val = new MyValidator();
    
    public Episode addEpisode(Episode episode) throws MyValidationException {
        if (episode.getId() != null
                || val.validateText(episode.getTitle(), 200) == false
                || episode.getRatings() != null
                || val.validateDate(episode.getReleaseDate(), 1850, 2100) == false
                || val.validateLength(episode.geteLength()) == false) {
            throw new MyValidationException();
        } else {
            return dao.addEpisode(episode);
        }
    }

    public Episode getEpisode(long id) throws MyValidationException {
        Episode tmp = dao.getEpisode(id);
        if (tmp == null) {
            throw new MyValidationException();
        } else {
            return tmp;
        }
    }

    public List<Episode> getSeasonEpisodes(long seasonId) {
        return dao.getSeasonEpisodes(seasonDAO.getSeason(seasonId));
    }

    public void deleteEpisode(long id) throws MyValidationException {
        Episode tmp = dao.getEpisode(id);
        if (tmp == null) {
            throw new MyValidationException();
        } else {
            dao.deleteEpisode(id);
        }
    }

    public Episode modifyEpisode(Episode episode) throws MyValidationException {
        if (episode.getId() == null
                || val.validateText(episode.getTitle(), 200) == false
                || episode.getRatings() != null
                || val.validateDate(episode.getReleaseDate(), 1850, 2100) == false
                || val.validateLength(episode.geteLength()) == false) {
            throw new MyValidationException();
        } else if (dao.getEpisode(episode.getId()) == null) {
            throw new MyValidationException();
        } else {
            return dao.modifyEpisode(episode);
        }
    }

    public void changeRating(long id, int rating) throws MyValidationException {//todo
         Episode tmp = dao.getEpisode(id);
        if (tmp != null) {
            int numberOfRatings = tmp.getNumberOfRatings();
            int sumOfRatings = tmp.getSumOfRatings();
            tmp.setNumberOfRatings(numberOfRatings + 1);
            tmp.setSumOfRatings(sumOfRatings + rating);
            tmp.setRating(sumOfRatings / numberOfRatings);
            dao.changeRating(tmp);
        } else {
            throw new MyValidationException();
        }
    }
}
