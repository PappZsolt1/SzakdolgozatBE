package myapp.SzakdolgozatBE.topic;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

@RequestScoped
public class TopicDAO {

    EntityManager em = Persistence.createEntityManagerFactory("SzakdolgozatPU").createEntityManager();

    public Topic addTopic(Topic topic) {
        em.getTransaction().begin();
        em.persist(topic);
        em.getTransaction().commit();
        return topic;
    }

    public List<Topic> getAllTopics() {
        return em.createNamedQuery("getAllTopics").getResultList();
    }

    public Topic getTopic(long id) throws NullPointerException {
        Topic tmp = em.find(Topic.class, id);
        if (tmp != null) {
            return tmp;
        } else {
            throw new NullPointerException();
        }
    }
    
    public void deleteTopic(long id) throws NullPointerException {
        em.getTransaction().begin();
        em.remove(this.getTopic(id));
        em.getTransaction().commit();
    }
}
