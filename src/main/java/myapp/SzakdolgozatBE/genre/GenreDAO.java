package myapp.SzakdolgozatBE.genre;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RequestScoped
public class GenreDAO {

    @PersistenceContext(unitName = "SzakdolgozatPU")
    EntityManager em;

    public Genre getGenre(long id) throws NullPointerException {
        Genre tmp = em.find(Genre.class, id);
        if (tmp != null) {
            return tmp;
        } else {
            throw new NullPointerException();
        }
    }

    public List<Genre> getAllGenres() {
        return em.createNamedQuery("getAllGenres").getResultList();
    }

    public Genre addGenre(Genre genre) {
        em.getTransaction().begin();
        em.persist(genre);
        em.getTransaction().commit();
        return genre;
    }

    public void deleteGenre(long id) {
        em.getTransaction().begin();
        em.remove(this.getGenre(id));
        em.getTransaction().commit();
    }

    public Genre modifyGenre(long id, Genre genre) throws NullPointerException {
        Genre tmp = this.getGenre(id);
        if (tmp != null) {
            tmp.setName(genre.getName());
            em.getTransaction().begin();
            em.merge(tmp);
            em.getTransaction().commit();
            return tmp;
        } else {
            throw new NullPointerException();
        }
    }
}
