package myapp.SzakdolgozatBE.gender;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class GenderService {

    @Inject
    GenderDAO dao;

    public Gender getGender(long id) throws NullPointerException {
        Gender tmp = dao.getGender(id);
        if (tmp == null) {
            throw new NullPointerException();
        } else {
            return tmp;
        }
    }

    public List<Gender> getAllGenders() {
        return dao.getAllGenders();
    }

    public Gender addGender(String name) {
        Gender tmp = new Gender();
        tmp.setName(name);
        return dao.addGender(tmp);
    }

    public void deleteGender(long id) throws NullPointerException {
        Gender tmp = dao.getGender(id);
        if (tmp == null) {
            throw new NullPointerException();
        } else {
            dao.deleteGender(id);
        }
    }

    public Gender modifyGender(long id, String name) throws NullPointerException {
        Gender tmp = dao.getGender(id);
        if (tmp != null) {
            tmp.setName(name);
            return dao.modifyGender(tmp);
        } else {
            throw new NullPointerException();
        }
    }
}
