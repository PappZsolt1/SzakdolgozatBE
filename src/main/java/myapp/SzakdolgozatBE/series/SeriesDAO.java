package myapp.SzakdolgozatBE.series;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import myapp.SzakdolgozatBE.ageClassification.AgeClassification;
import myapp.SzakdolgozatBE.genre.Genre;

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
    
    public List<Series> getResultSeries(String title) {
        return em.createNamedQuery("getResultSeries").setParameter("title", title).getResultList();
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
    
    public boolean ageClassificationNotUsed(AgeClassification ageClassification) {
        return em.createNamedQuery("getAgeClassificationSeries")
                .setParameter("ageClassification", ageClassification).getResultList().isEmpty();
    }
    
    public boolean genreNotUsed(Genre genre) {
        return em.createNamedQuery("getGenreSeries")
                .setParameter("genre", genre).getResultList().isEmpty();
    }
}
