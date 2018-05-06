package myapp.SzakdolgozatBE.episode;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import myapp.SzakdolgozatBE.season.Season;

@RequestScoped
public class EpisodeDAO {
    
    EntityManager em = Persistence.createEntityManagerFactory("SzakdolgozatPU").createEntityManager();
    
    public Episode addEpisode(Episode episode) {
        em.getTransaction().begin();
        em.persist(episode);
        em.getTransaction().commit();
        return episode;
    }
    
    public Episode getEpisode(long id) throws NullPointerException {
        Episode tmp = em.find(Episode.class, id);
        if (tmp != null)    
                return tmp;
        else
            throw new NullPointerException();
    }
    
    public List<Episode> getSeasonEpisodes(Season season) {
        return em.createNamedQuery("getSeasonEpisodes").setParameter("season", season).getResultList();
    }
    
    public void deleteEpisode(long id) throws NullPointerException {
        em.getTransaction().begin();
        em.remove(this.getEpisode(id));
        em.getTransaction().commit();
    }
    
    public Episode modifyEpisode(long id, Episode episode) throws NullPointerException {
        Episode tmp = this.getEpisode(id);
        if (tmp != null) {
            tmp.setLength(episode.getLength());
            tmp.setReleaseDate(episode.getReleaseDate());
            tmp.setTitle(episode.getTitle());
            tmp.setSeason(episode.getSeason());
            em.getTransaction().begin();
            em.merge(tmp);
            em.getTransaction().commit();
            return tmp;
        } else throw new NullPointerException();
    }
    
    public void changeRating(long id, int rating) {
        Episode tmp = this.getEpisode(id);
        tmp.setRating(rating);
        em.getTransaction().begin();
        em.merge(tmp);
        em.getTransaction().commit();
    }
}
