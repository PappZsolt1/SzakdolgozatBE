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
    
    public Season getSeason(long id) throws NullPointerException {
        Season tmp = em.find(Season.class, id);
        if (tmp != null)    
                return tmp;
        else
            throw new NullPointerException();
    }
    
    public List<Season> getSeriesSeasons(Series series) {
        return em.createNamedQuery("getSeriesSeasons").setParameter("series", series).getResultList();
    }
    
    public void deleteSeason(long id) throws NullPointerException {
        em.getTransaction().begin();
        em.remove(this.getSeason(id));
        em.getTransaction().commit();
    }
    
    public Season modifySeason(long id, Season season) throws NullPointerException {
        Season tmp = this.getSeason(id);
        if (tmp != null) {
            tmp.setNumber(season.getNumber());
            tmp.setSeries(season.getSeries());
            em.getTransaction().begin();
            em.merge(tmp);
            em.getTransaction().commit();
            return tmp;
        } else throw new NullPointerException();
    }
}
