package myapp.SzakdolgozatBE.actor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import myapp.SzakdolgozatBE.MyValidationException;
import myapp.SzakdolgozatBE.gender.GenderDAO;

@Stateless
public class ActorService {

    @Inject
    ActorDAO dao;
    
    @Inject
    GenderDAO genderDao;

    public Actor addActor(Actor actor) throws MyValidationException {
        if (actor.getId() != null || actor.getName().trim().equals("") ||
                actor.getBirthPlace().trim().equals("") || actor.getBio().trim().equals("") ||
                genderDao.getGender(actor.getGender().getId()) == null ||
                validateDate(actor.getBirthDate()) == false) { //photo
            throw new MyValidationException();
        } else {
            return dao.addActor(actor);
        }        
    }

    public Actor getActor(long id) throws NullPointerException {
        Actor tmp = dao.getActor(id);
        if (tmp == null) {
            throw new NullPointerException();
        } else {
            return tmp;
        }
    }

    /*public List<Actor> getxxxActors() {
        //todo
    }*/
    
    public void deleteActor(long id) throws NullPointerException {
        Actor tmp = dao.getActor(id);
        if (tmp == null) {
            throw new NullPointerException();
        } else {
            dao.deleteActor(id);
        }
    }

    public Actor modifyActor(Actor actor) throws NullPointerException, MyValidationException {
        if (actor.getId() == null || actor.getName().trim().equals("") ||
                actor.getBirthPlace().trim().equals("") || actor.getBio().trim().equals("") ||
                genderDao.getGender(actor.getGender().getId()) == null ||
                validateDate(actor.getBirthDate()) == false) { //photo
            throw new MyValidationException();
        } else if (dao.getActor(actor.getId()) == null) {
            throw new NullPointerException();
        } else {
            return dao.modifyActor(actor);
        }
    }
    
    public boolean validateDate(String birthDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy. MM. dd.");
            sdf.setLenient(false);
            sdf.parse(birthDate);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
