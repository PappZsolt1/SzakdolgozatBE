package myapp.SzakdolgozatBE.genre;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class GenreService {

    @Inject
    GenreDAO dao;

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
}
