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

    public Series getSeries(long id) {
        return em.find(Series.class, id);
    }

    public List<Series> getAllSeries() {
        return em.createNamedQuery("getAllMovies").getResultList();
    }

    public void deleteSeries(long id) {
        em.getTransaction().begin();
        em.remove(this.getSeries(id));
        em.getTransaction().commit();
    }

    public Series modifySeries(Series series) {
        em.getTransaction().begin();
        em.merge(series);
        em.getTransaction().commit();
        return series;
    }

    public void changeRating(Series series) {        
        em.getTransaction().begin();
        em.merge(series);
        em.getTransaction().commit();
    }
}
