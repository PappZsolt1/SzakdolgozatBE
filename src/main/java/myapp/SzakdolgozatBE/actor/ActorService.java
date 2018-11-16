package myapp.SzakdolgozatBE.actor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import myapp.SzakdolgozatBE.MyValidationException;
import myapp.SzakdolgozatBE.MyValidator;
import myapp.SzakdolgozatBE.gender.GenderDAO;

@Stateless
public class ActorService {

    @Inject
    ActorDAO dao;
    
    @Inject
    GenderDAO genderDao;
    
    MyValidator val = new MyValidator();

    public Actor addActor(Actor actor) throws MyValidationException {
        if (actor.getId() != null
                || val.validateText(actor.getName(), 200) == false
                || val.validateText(actor.getBirthPlace(), 200) == false
                || val.validateText(actor.getBio(), 60000) == false
                || genderDao.getGender(actor.getGender().getId()) == null
                || val.validateDate(actor.getBirthDate(), 1750, 2100) == false) { //photo
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

    public List<Actor> getResultActors(String name) throws MyValidationException {
        if (val.validateText(name, 200) == false) {
            throw new MyValidationException();
        } else {
            return dao.getResultActors(name);
        }
    }
    
    /*public List<Actor> getxxxActors() {
        //todo
    }*/
    
    public void deleteActor(long id) throws MyValidationException {
        Actor tmp = dao.getActor(id);
        if (tmp == null) {
            throw new MyValidationException();
        } else {
            dao.deleteActor(id);
        }
    }

    public Actor modifyActor(Actor actor) throws MyValidationException {
        if (actor.getId() == null
                || val.validateText(actor.getName(), 200) == false
                || val.validateText(actor.getBirthPlace(), 200) == false
                || val.validateText(actor.getBio(), 60000) == false
                || genderDao.getGender(actor.getGender().getId()) == null
                || val.validateDate(actor.getBirthDate(), 1750, 2100) == false) { //photo
            throw new MyValidationException();
        } else if (dao.getActor(actor.getId()) == null) {
            throw new MyValidationException();
        } else {
            return dao.modifyActor(actor);
        }
    }
}
