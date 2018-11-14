package myapp.SzakdolgozatBE.genre;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import myapp.SzakdolgozatBE.MyValidationException;
import myapp.SzakdolgozatBE.MyValidator;
import myapp.SzakdolgozatBE.movie.MovieDAO;
import myapp.SzakdolgozatBE.series.SeriesDAO;

@Stateless
public class GenreService {

    @Inject
    GenreDAO dao;
    
    @Inject
    MovieDAO movieDao;

    @Inject
    SeriesDAO seriesDao;
    
    MyValidator val = new MyValidator();
    
    public Genre getGenre(long id) throws MyValidationException {
        Genre tmp = dao.getGenre(id);
        if (tmp == null) {
            throw new MyValidationException();
        } else {
            return tmp;
        }
    }

    public List<Genre> getAllGenres() {
        return dao.getAllGenres();
    }

    public Genre addGenre(String name) throws MyValidationException {
        if (val.validateText(name, 60000) == false) {
            throw new MyValidationException();
        } else {
            Genre tmp = new Genre();
            tmp.setName(name);
            return dao.addGenre(tmp);
        }
    }

    public void deleteGenre(long id) throws MyValidationException {
        Genre tmp = dao.getGenre(id);
        if (tmp == null || canBeDeleted(id) == false) {
            throw new MyValidationException();
        } else {
            dao.deleteGenre(id);
        }
    }

    public Genre modifyGenre(long id, String name) throws MyValidationException {
        Genre tmp = dao.getGenre(id);
        if (tmp == null || val.validateText(name, 60000) == false) {
            throw new MyValidationException();
        } else {
            tmp.setName(name);            
            return dao.modifyGenre(tmp);
        }
    }
    
    public boolean canBeDeleted(long id) throws MyValidationException {
        Genre tmp = dao.getGenre(id);
        if (tmp == null) {
            throw new MyValidationException();
        } else if (movieDao.genreNotUsed(tmp) && seriesDao.genreNotUsed(tmp)) {
            return true;
        } else {
            return false;
        }
    }
}
