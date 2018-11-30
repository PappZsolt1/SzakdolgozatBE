package myapp.SzakdolgozatBE.actor;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import myapp.SzakdolgozatBE.MyValidationException;
import myapp.SzakdolgozatBE.MyValidator;
import myapp.SzakdolgozatBE.Wrapper;
import myapp.SzakdolgozatBE.comment.Comment;
import myapp.SzakdolgozatBE.comment.CommentDAO;
import myapp.SzakdolgozatBE.gender.GenderDAO;

@Stateless
public class ActorService {

    @Inject
    ActorDAO dao;
    
    @Inject
    GenderDAO genderDao;
    
    @Inject
    CommentDAO commentDao;
    
    MyValidator val = new MyValidator();

    public Actor addActor(Actor actor) throws MyValidationException {
        if (actor.getId() != null
                || val.validateText(actor.getName(), 200) == false
                || val.validateText(actor.getBirthPlace(), 200) == false
                || val.validateText(actor.getBio(), 60000) == false
                || genderDao.getGender(actor.getGender().getId()) == null
                || val.validateDate(actor.getBirthDate(), 1750, 2100) == false
                || val.validateText(actor.getImageUrl(), 1000) == false) {
            throw new MyValidationException();
        } else {
            return dao.addActor(actor);
        }        
    }

    public Actor getActor(long id) throws MyValidationException {
        Actor tmp = dao.getActor(id);
        if (tmp == null) {
            throw new MyValidationException();
        } else {
            return tmp;
        }
    }
    
    public boolean checkIfExists(long id) {
        Actor tmp = dao.getActor(id);
        return (tmp != null);
    }

    public Wrapper getResultActors(int page, int size, String name) throws MyValidationException {
        if (page < 1 || val.validateSize(size) == false || val.validateText(name, 200) == false) {
            throw new MyValidationException();
        }
        int offset = (page - 1) * size;
        List<Actor> results = dao.getResultActors(offset, size, name);
        long total = dao.getNumberOfResultActors(name);
        if (total > 0 && offset >= total) {
            throw new MyValidationException();
        }
        return new Wrapper(results, total);
    }
    
    public void deleteActor(long id) throws MyValidationException {
        Actor tmp = dao.getActor(id);
        if (tmp == null || canBeDeleted(id) == false) {
            throw new MyValidationException();
        } else {
            List<Comment> comments = commentDao.getAllActorComments(tmp);
            for (Comment comment : comments) {
                commentDao.deleteComment(comment.getId());
            }
            dao.deleteActor(id);
        }
    }

    public Actor modifyActor(Actor actor) throws MyValidationException {
        if (actor.getId() == null
                || val.validateText(actor.getName(), 200) == false
                || val.validateText(actor.getBirthPlace(), 200) == false
                || val.validateText(actor.getBio(), 60000) == false
                || genderDao.getGender(actor.getGender().getId()) == null
                || val.validateDate(actor.getBirthDate(), 1750, 2100) == false
                || val.validateText(actor.getImageUrl(), 1000) == false) {
            throw new MyValidationException();
        } else if (dao.getActor(actor.getId()) == null) {
            throw new MyValidationException();
        } else {
            return dao.modifyActor(actor);
        }
    }
    
    public boolean canBeDeleted(long id) throws MyValidationException {
        Actor tmp = dao.getActor(id);
        if (tmp == null) {
            throw new MyValidationException();
        } else {
            return (tmp.getMovies().isEmpty() && tmp.getEpisodes().isEmpty());
        }
    }
}
