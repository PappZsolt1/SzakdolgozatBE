package myapp.SzakdolgozatBE.series;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RequestScoped
public class SeriesDAO {

    @PersistenceContext(unitName = "SzakdolgozatPU")
    EntityManager em;
    
    public Series addSeries(Series series) {
        em.getTransaction().begin();
        em.persist(series);
        em.getTransaction().commit();
        return series;
    }
    
    public Series getSeries(long id) throws NullPointerException {
        Series tmp = em.find(Series.class, id);
        if (tmp != null)    
                return tmp;
        else
            throw new NullPointerException();
    }
    
    public List<Series> getAllSeries () {
        return em.createNamedQuery("getAllMovies").getResultList();
    }
    
    public void deleteSeries(long id) throws NullPointerException {
        em.getTransaction().begin();
        em.remove(this.getSeries(id));
        em.getTransaction().commit();
    }
    
    public Series modifySeries(long id, Series series) throws NullPointerException {
        Series tmp = this.getSeries(id);
        if (tmp != null) {
            tmp.setAgeClassification(series.getAgeClassification());
            tmp.setCoverPicture(series.getCoverPicture());
            tmp.setGenre(series.getGenre());
            tmp.setReleaseYear(series.getReleaseYear());
            tmp.setTitle(series.getTitle());
            em.getTransaction().begin();
            em.merge(tmp);
            em.getTransaction().commit();
            return tmp;
        } else throw new NullPointerException();
    }
    
    public void changeRating(long id) {
        Series tmp = this.getSeries(id);
        em.createNamedQuery("getEpisodeRatings"); //todo
        em.getTransaction().begin();
        em.merge(tmp);
        em.getTransaction().commit();
    }
}
