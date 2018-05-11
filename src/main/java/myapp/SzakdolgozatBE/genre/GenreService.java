package myapp.SzakdolgozatBE.genre;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class GenreService {
    
    @Inject GenreDAO dao;
    
    public Genre getGenre(long id) throws NullPointerException {
        return dao.getGenre(id);
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
        dao.deleteGenre(id);
    }
    
    public Genre modifyGenre(long id, String name) throws NullPointerException {
        Genre tmp = new Genre();
        tmp.setName(name);
        return dao.modifyGenre(id, tmp);
    }
}
