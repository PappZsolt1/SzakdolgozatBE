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
        if (actor.getId() != null ||
                actor.getName().matches("^\\S.*\\S$|^\\S$") == false ||
                actor.getBirthPlace().matches("^\\S.*\\S$|^\\S$") == false ||
                actor.getBio().matches("^\\S(.|\\s)*\\S$|^\\S$") == false ||
                genderDao.getGender(actor.getGender().getId()) == null ||
                validateBirthDate(actor.getBirthDate()) == false) { //photo
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
        if (actor.getId() == null ||
                actor.getName().matches("^\\S.*\\S$|^\\S$") == false ||
                actor.getBirthPlace().matches("^\\S.*\\S$|^\\S$") == false ||
                actor.getBio().matches("^\\S(.|\\s)*\\S$|^\\S$") == false ||
                genderDao.getGender(actor.getGender().getId()) == null ||
                validateBirthDate(actor.getBirthDate()) == false) { //photo
            throw new MyValidationException();
        } else if (dao.getActor(actor.getId()) == null) {
            throw new MyValidationException();
        } else {
            return dao.modifyActor(actor);
        }
    }
    
    public boolean validateBirthDate(String birthDate) {
        int year;
        try {
            year = Integer.parseInt(birthDate.substring(0, 4));
        } catch (NumberFormatException e) {
            return false;
        }
        if (year < 1800 || year > 2100) {
            return false;
        }
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
