package myapp.SzakdolgozatBE.episode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import myapp.SzakdolgozatBE.MyValidationException;
import myapp.SzakdolgozatBE.season.SeasonDAO;

@Stateless
public class EpisodeService {

    @Inject
    EpisodeDAO dao;
    
    @Inject
    SeasonDAO seasonDAO;
    
    public Episode addEpisode(Episode episode) throws MyValidationException {
        if (episode.getId() != null ||
                episode.getTitle().matches("^\\S.*\\S$|^\\S$") == false ||
                episode.getTitle().length() > 200 ||
                episode.getRatings() != null ||
                validateReleaseDate(episode.getReleaseDate()) == false ||
                validateLength(episode.geteLength()) == false) {
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
        if (episode.getId() == null ||
                episode.getTitle().matches("^\\S.*\\S$|^\\S$") == false ||
                episode.getTitle().length() > 200 ||
                episode.getRatings() != null ||
                validateReleaseDate(episode.getReleaseDate()) == false ||
                validateLength(episode.geteLength()) == false) {
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
    
    public boolean validateReleaseDate(String releaseDate) {
        int year;
        try {
            year = Integer.parseInt(releaseDate.substring(0, 4));
        } catch (NumberFormatException e) {
            return false;
        }
        if (year < 1850 || year > 2100) {
            return false;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy. MM. dd.");
            sdf.setLenient(false);
            sdf.parse(releaseDate);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
    
    public boolean validateLength(String eLength) {
        if (eLength.matches("^[0-9]{1,3} óra [0-9]{1,2} perc$|^[0-9]{1,2} perc$") == false) {
            return false;
        }
        if (eLength.length() > 10) {
            int hour;
            try {
                hour = Integer.parseInt(eLength.substring(0, eLength.indexOf("ó") - 1));
            } catch (NumberFormatException e) {
                return false;
            }
            if (hour > 100 || hour < 0) {
                return false;
            }
            int minute;
            try {
                minute = Integer.parseInt(eLength.substring(eLength.indexOf("a") + 2, eLength.indexOf("p") - 1));
            } catch (NumberFormatException e) {
                return false;
            }
            if (minute > 60 || minute < 0) {
                return false;
            }
        } else {
            int minute;
            try {
                minute = Integer.parseInt(eLength.substring(0, eLength.indexOf("p") - 1));
            } catch (NumberFormatException e) {
                return false;
            }
            if (minute > 60 || minute < 0) {
                return false;
            }
        }
        return true;
    }
}
