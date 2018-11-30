package myapp.SzakdolgozatBE.episode;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import myapp.SzakdolgozatBE.MyValidationException;
import myapp.SzakdolgozatBE.MyValidator;
import myapp.SzakdolgozatBE.actor.Actor;
import myapp.SzakdolgozatBE.actor.ActorDAO;
import myapp.SzakdolgozatBE.comment.Comment;
import myapp.SzakdolgozatBE.comment.CommentDAO;
import myapp.SzakdolgozatBE.rating.Rating;
import myapp.SzakdolgozatBE.rating.RatingDAO;
import myapp.SzakdolgozatBE.season.Season;
import myapp.SzakdolgozatBE.season.SeasonDAO;

@Stateless
public class EpisodeService {

    @Inject
    EpisodeDAO dao;
    
    @Inject
    SeasonDAO seasonDao;
    
    @Inject
    ActorDAO actorDao;
    
    @Inject
    RatingDAO ratingDao;
    
    @Inject
    CommentDAO commentDao;
    
    MyValidator val = new MyValidator();
    
    public Episode addEpisode(long seasonId, Episode episode) throws MyValidationException {
        if (episode.getId() != null
                || val.validateText(episode.getTitle(), 200) == false
                || episode.getRatings() != null
                || val.validateDate(episode.getReleaseDate(), 1850, 2100) == false
                || val.validateLength(episode.geteLength()) == false) {
            throw new MyValidationException();
        } else {
            Season tmp = seasonDao.getSeason(seasonId);
            if (tmp == null) {
                throw new MyValidationException();
            } else {
                episode.setSeason(tmp);
                dao.addEpisode(episode);
                tmp.getEpisodes().add(episode);
                seasonDao.modifySeason(tmp);
                return episode;
            }
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
    
    public boolean checkIfExists(long id) {
        Episode tmp = dao.getEpisode(id);
        return (tmp != null);
    }
    
    public long getEpisodeSeasonId(long id) throws MyValidationException {
        Episode tmp = dao.getEpisode(id);
        if (tmp == null) {
            throw new MyValidationException();
        } else {
            return tmp.getSeason().getId();
        }
    }
    
    public List<Actor> getEpisodeActors(long id) throws MyValidationException {
        Episode tmp = dao.getEpisode(id);
        if (tmp == null) {
            throw new MyValidationException();
        } else {
            return tmp.getActors();
        }
    }
    
    public Actor addActorToEpisode(long id, long actorId) throws MyValidationException {
        Episode tmp1 = dao.getEpisode(id);
        if (tmp1 == null) {
            throw new MyValidationException();
        } else {
            Actor tmp2 = actorDao.getActor(actorId);
            if (tmp2 == null || tmp1.getActors().contains(tmp2) == true) {
                throw new MyValidationException();
            } else {
                tmp1.getActors().add(tmp2);
                dao.modifyEpisode(tmp1);
                tmp2.getEpisodes().add(tmp1);
                actorDao.modifyActor(tmp2);
                return tmp2;
            }
        }
    }
    
    public Actor removeActorFromEpisode(long id, long actorId) throws MyValidationException {
        Episode tmp1 = dao.getEpisode(id);
        if (tmp1 == null) {
            throw new MyValidationException();
        } else {
            Actor tmp2 = actorDao.getActor(actorId);
            if (tmp2 == null) {
                throw new MyValidationException();
            } else {
                tmp1.getActors().remove(tmp2);
                dao.modifyEpisode(tmp1);
                tmp2.getEpisodes().remove(tmp1);
                actorDao.modifyActor(tmp2);
                return tmp2;
            }
        }
    }

    public void deleteEpisode(long seasonId, long id) throws MyValidationException {
        Episode tmp1 = dao.getEpisode(id);
        if (tmp1 == null || canBeDeleted(id) == false) {
            throw new MyValidationException();
        } else {
            Season tmp2 = seasonDao.getSeason(seasonId);
            if (tmp2 == null) {
                throw new MyValidationException();
            }
            List<Comment> comments = commentDao.getAllEpisodeComments(tmp1);
            for (Comment comment : comments) {
                commentDao.deleteComment(comment.getId());
            }
            dao.deleteEpisode(id);
            tmp2.getEpisodes().remove(tmp1);
            seasonDao.modifySeason(tmp2);
        }
    }

    public Episode modifyEpisode(long seasonId, Episode episode) throws MyValidationException {
        if (episode.getId() == null
                || val.validateText(episode.getTitle(), 200) == false
                || val.validateDate(episode.getReleaseDate(), 1850, 2100) == false
                || val.validateLength(episode.geteLength()) == false) {
            throw new MyValidationException();
        } else {
            Episode tmp1 = dao.getEpisode(episode.getId());
            if (tmp1 == null) {
                throw new MyValidationException();
            }
            Season tmp2 = seasonDao.getSeason(seasonId);
            if (tmp2 == null) {
                throw new MyValidationException();
            }
            if (tmp1.getSeason().equals(tmp2) == true) {
                episode.setSeason(tmp2);
                return dao.modifyEpisode(episode);
            } else {
                episode.setSeason(tmp2);
                seasonDao.modifySeason(tmp2);
                tmp1.getSeason().getEpisodes().remove(episode);
                dao.modifyEpisode(episode);
                tmp2.getEpisodes().add(episode);
                seasonDao.modifySeason(tmp1.getSeason());
                return episode;
            }
        }
    }

    public void changeRating(long id, byte rating) throws MyValidationException {//todo
        Episode tmp = dao.getEpisode(id);
        if (tmp == null || val.validateNumber(rating, 1, 10) == false) {
            throw new MyValidationException();
        } else {
            tmp.setNumberOfRatings(tmp.getNumberOfRatings() + 1);
            tmp.setSumOfRatings(tmp.getSumOfRatings() + rating);
            tmp.setRating((double)tmp.getSumOfRatings() / (double)tmp.getNumberOfRatings());
            dao.modifyEpisode(tmp);
            Rating r = new Rating();
            r.setRating(rating);
            r.setEpisode(tmp);
            //r.setMyUser(myUser);
            ratingDao.addRating(r);
        }
    }
    
    public boolean canBeDeleted(long id) throws MyValidationException {
        Episode tmp = dao.getEpisode(id);
        if (tmp == null) {
            throw new MyValidationException();
        } else {
            return tmp.getActors().isEmpty();
        }
    }
}
