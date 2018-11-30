package myapp.SzakdolgozatBE.episode;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import myapp.SzakdolgozatBE.season.Season;

@RequestScoped
public class EpisodeDAO {

    @PersistenceContext(unitName = "SzakdolgozatPU")
    EntityManager em;

    public Episode addEpisode(Episode episode) {
        em.getTransaction().begin();
        em.persist(episode);
        em.getTransaction().commit();
        return episode;
    }

    public Episode getEpisode(long id) {
        return em.find(Episode.class, id);        
    }

    public void deleteEpisode(long id) {
        em.getTransaction().begin();
        em.remove(this.getEpisode(id));
        em.getTransaction().commit();
    }

    public Episode modifyEpisode(Episode episode) {
        em.getTransaction().begin();
        em.merge(episode);
        em.getTransaction().commit();
        return episode;
    }
}
