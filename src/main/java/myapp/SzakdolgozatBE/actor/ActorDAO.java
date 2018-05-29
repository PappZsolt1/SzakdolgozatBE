package myapp.SzakdolgozatBE.actor;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RequestScoped
public class ActorDAO {

    @PersistenceContext(unitName = "SzakdolgozatPU")
    EntityManager em;

    public Actor addActor(Actor actor) {
        em.getTransaction().begin();
        em.persist(actor);
        em.getTransaction().commit();
        return actor;
    }

    public Actor getActor(long id) {
        return em.find(Actor.class, id);
    }

    /*public List<Actor> getxxxActors() {
        //todo
    }*/
    
    public void deleteActor(long id) {
        em.getTransaction().begin();
        em.remove(this.getActor(id));
        em.getTransaction().commit();
    }

    public Actor modifyActor(Actor actor) {
        em.getTransaction().begin();
        em.merge(actor);
        em.getTransaction().commit();
        return actor;
    }
}
