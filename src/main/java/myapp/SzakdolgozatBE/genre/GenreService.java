package myapp.SzakdolgozatBE.genre;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
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
    
    public Genre getGenre(long id) throws NullPointerException {
        Genre tmp = dao.getGenre(id);
        if (tmp == null) {
            throw new NullPointerException();
        } else {
            return tmp;
        }
    }

    public List<Genre> getAllGenres() {
        return dao.getAllGenres();
    }

    public Genre addGenre(String name) {
        Genre tmp = new Genre();
        tmp.setName(name);
        return dao.addGenre(tmp);
    }

    public void deleteGenre(long id) {
        Genre tmp = dao.getGenre(id);
        if (tmp == null) {
            throw new NullPointerException();
        } else {
            dao.deleteGenre(id);
        }
    }

    public Genre modifyGenre(long id, String name) throws NullPointerException {
        Genre tmp = dao.getGenre(id);
        if (tmp != null) {
            tmp.setName(name);            
            return dao.modifyGenre(tmp);
        } else {
            throw new NullPointerException();
        }
    }
    
    public boolean canBeDeleted(long id) {
        Genre tmp = dao.getGenre(id);
        if (movieDao.genreNotUsed(tmp) && seriesDao.genreNotUsed(tmp)) {
            return true;
        } else {
            return false;
        }
    }
}
