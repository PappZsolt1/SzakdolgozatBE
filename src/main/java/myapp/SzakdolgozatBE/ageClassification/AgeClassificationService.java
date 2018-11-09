package myapp.SzakdolgozatBE.ageClassification;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
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

    public AgeClassification getAgeClassification(long id) throws NullPointerException {
        AgeClassification tmp = dao.getAgeClassification(id);
        if (tmp == null) {
            throw new NullPointerException();
        } else {
            return tmp;
        }
    }

    public List<AgeClassification> getAllAgeClassifications() {
        return dao.getAllAgeClassifications();
    }

    public AgeClassification addAgeClassification(String name) {
        AgeClassification tmp = new AgeClassification();
        tmp.setName(name);
        return dao.addAgeClassification(tmp);
    }

    public void deleteAgeClassification(long id) throws NullPointerException {
        AgeClassification tmp = dao.getAgeClassification(id);
        if (tmp == null) {
            throw new NullPointerException();
        } else {
            dao.deleteAgeClassification(id);
        }
    }

    public AgeClassification modifyAgeClassification(long id, String name) throws NullPointerException {
        AgeClassification tmp = dao.getAgeClassification(id);
        if (tmp != null) {
            tmp.setName(name);
            return dao.modifyAgeClassification(tmp);
        } else {
            throw new NullPointerException();
        }
    }
    
    public boolean canBeDeleted(long id) {
        AgeClassification tmp = dao.getAgeClassification(id);
        if (movieDao.ageClassificationNotUsed(tmp) && seriesDao.ageClassificationNotUsed(tmp)) {
            return true;
        } else {
            return false;
        }
    }
}
