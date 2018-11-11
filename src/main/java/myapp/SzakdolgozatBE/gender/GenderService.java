package myapp.SzakdolgozatBE.gender;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import myapp.SzakdolgozatBE.MyValidationException;
import myapp.SzakdolgozatBE.actor.ActorDAO;

@Stateless
public class GenderService {

    @Inject
    GenderDAO dao;
    
    @Inject
    ActorDAO actorDao;

    public Gender getGender(long id) throws MyValidationException {
        Gender tmp = dao.getGender(id);
        if (tmp == null) {
            throw new MyValidationException();
        } else {
            return tmp;
        }
    }

    public List<Gender> getAllGenders() {
        return dao.getAllGenders();
    }

    public Gender addGender(String name) throws MyValidationException {
        if (name.matches("^\\S.*\\S$|^\\S$") == false) {
            throw new MyValidationException();
        } else {
        Gender tmp = new Gender();
        tmp.setName(name);
        return dao.addGender(tmp);        
        }
    }

    public void deleteGender(long id) throws MyValidationException {
        Gender tmp = dao.getGender(id);
        if (tmp == null || canBeDeleted(id) == false) {
            throw new MyValidationException();
        } else {
            dao.deleteGender(id);
        }
    }

    public Gender modifyGender(long id, String name) throws MyValidationException {
        Gender tmp = dao.getGender(id);
        if (tmp == null || name.matches("^\\S.*\\S$|^\\S$") == false) {
            throw new MyValidationException();
        } else {
            tmp.setName(name);
            return dao.modifyGender(tmp);
        }
    }
    
    public boolean canBeDeleted(long id) throws MyValidationException {
        Gender tmp = dao.getGender(id);
        if (tmp == null) {
            throw new MyValidationException();
        } else if (actorDao.genreNotUsed(tmp) == true) {
            return true;
        } else {
            return false;
        }
    }
}
