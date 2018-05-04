package myapp.SzakdolgozatBE.actor;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

@RequestScoped
public class ActorDAO {

    EntityManager em = Persistence.createEntityManagerFactory("SzakdolgozatPU").createEntityManager();

    public Actor addActor(Actor actor) {
        em.getTransaction().begin();
        em.persist(actor);
        em.getTransaction().commit();
        return actor;
    }

    public Actor getActor(long id) throws NullPointerException {
        Actor tmp = em.find(Actor.class, id);
        if (tmp != null) {
            return tmp;
        } else {
            throw new NullPointerException();
        }
    }

    public List<Actor> getxxxActors() {
        //todo
    }

    public void deleteActor(long id) throws NullPointerException {
        em.getTransaction().begin();
        em.remove(this.getActor(id));
        em.getTransaction().commit();
    }

    public Actor modifyOne(long id, Actor actor) throws NullPointerException {
        Actor tmp = this.getActor(id);
        if (tmp != null) {
            tmp.setBio(actor.getBio());
            tmp.setBirthDate(actor.getBirthDate());
            tmp.setBirthPlace(actor.getBirthPlace());
            tmp.setName(actor.getName());
            tmp.setGender(actor.getGender());
            em.getTransaction().begin();
            em.merge(tmp);
            em.getTransaction().commit();
            return tmp;
        } else {
            throw new NullPointerException();
        }
    }
}
