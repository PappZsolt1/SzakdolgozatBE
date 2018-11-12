package myapp.SzakdolgozatBE.ageClassification;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import myapp.SzakdolgozatBE.MyValidationException;
import myapp.SzakdolgozatBE.movie.MovieDAO;
import myapp.SzakdolgozatBE.series.SeriesDAO;

@Stateless
public class AgeClassificationService {

    @Inject
    AgeClassificationDAO dao;
    
    @Inject
    MovieDAO movieDao;
    
    @Inject
    SeriesDAO seriesDao;

    public AgeClassification getAgeClassification(long id) throws MyValidationException {
        AgeClassification tmp = dao.getAgeClassification(id);
        if (tmp == null) {
            throw new MyValidationException();
        } else {
            return tmp;
        }
    }

    public List<AgeClassification> getAllAgeClassifications() {
        return dao.getAllAgeClassifications();
    }

    public AgeClassification addAgeClassification(String name) throws MyValidationException {
        if (name.matches("^\\S.*\\S$|^\\S$") == false || name.length() > 200) {
            throw new MyValidationException();
        } else {
            AgeClassification tmp = new AgeClassification();
            tmp.setName(name);
            return dao.addAgeClassification(tmp);            
        }
    }

    public void deleteAgeClassification(long id) throws MyValidationException {
        AgeClassification tmp = dao.getAgeClassification(id);
        if (tmp == null || canBeDeleted(id) == false) {
            throw new MyValidationException();
        } else {
            dao.deleteAgeClassification(id);
        }
    }

    public AgeClassification modifyAgeClassification(long id, String name) throws MyValidationException {
        AgeClassification tmp = dao.getAgeClassification(id);
        if (tmp == null || name.matches("^\\S.*\\S$|^\\S$") == false || name.length() > 200) {
            throw new MyValidationException();
        } else {
            tmp.setName(name);
            return dao.modifyAgeClassification(tmp);
        }
    }
    
    public boolean canBeDeleted(long id) throws MyValidationException {
        AgeClassification tmp = dao.getAgeClassification(id);
        if (tmp == null) {
            throw new MyValidationException();
        } else if (movieDao.ageClassificationNotUsed(tmp) == true && seriesDao.ageClassificationNotUsed(tmp) == true) {
            return true;
        } else {
            return false;
        }
    }
}
