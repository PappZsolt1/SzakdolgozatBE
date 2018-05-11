package myapp.SzakdolgozatBE.ageClassification;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class AgeClassificationService {
    
    @Inject AgeClassificationDAO dao;
    
    public AgeClassification getAgeClassification(long id) throws NullPointerException {
        return dao.getAgeClassification(id);
    }
    
    public List<AgeClassification> getAllAgeClassifications() {
        return dao.getAllAgeClassifications();
    }
    
    public AgeClassification addAgeClassification(String name) {
        AgeClassification tmp = new AgeClassification();
        tmp.setName(name);
        return dao.addAgeClassification(tmp);
    }
    
    public void deleteAgeClassification(long id) {
        dao.deleteAgeClassification(id);
    }
    
    public AgeClassification modifyAgeClassification(long id, String name) throws NullPointerException {
        AgeClassification tmp = new AgeClassification();
        tmp.setName(name);
        return dao.modifyAgeClassification(id, tmp);
    }
}
