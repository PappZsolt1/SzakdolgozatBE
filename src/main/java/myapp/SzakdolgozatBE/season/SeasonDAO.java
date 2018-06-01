package myapp.SzakdolgozatBE.season;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import myapp.SzakdolgozatBE.series.Series;

@RequestScoped
public class SeasonDAO {

    @PersistenceContext(unitName = "SzakdolgozatPU")
    EntityManager em;

    public Season addSeason(Season season) {
        em.getTransaction().begin();
        em.persist(season);
        em.getTransaction().commit();
        return season;
    }

    public Season getSeason(long id) {
        return em.find(Season.class, id);
    }

    public List<Season> getSeriesSeasons(Series series) {
        return em.createNamedQuery("getSeriesSeasons").setParameter("series", series).getResultList();
    }

    public void deleteSeason(long id) {
        em.getTransaction().begin();
        em.remove(this.getSeason(id));
        em.getTransaction().commit();
    }

    public Season modifySeason(Season season) {
        em.getTransaction().begin();
        em.merge(season);
        em.getTransaction().commit();
        return season;
    }
}
