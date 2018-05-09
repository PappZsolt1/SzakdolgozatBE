package myapp.SzakdolgozatBE.gender;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class GenderService {
    
    @Inject GenderDAO dao;
    
    public Gender getGender(long id) throws NullPointerException {
        return dao.getGender(id);
    }
    
    public List<Gender> getAllGenders() {
        return dao.getAllGenders();
    }
    
    public Gender addGender(String name) {
        Gender tmp = new Gender();
        tmp.setName(name);
        return dao.addGender(tmp);
    }
    
    public void deleteGender(long id) {
        dao.deleteGender(id);
    }
    
    public Gender modifyGender(long id, String name) throws NullPointerException {
        Gender tmp = new Gender();
        tmp.setName(name);
        return dao.modifyGender(id, tmp);
    }
}
