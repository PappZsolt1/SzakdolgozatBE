package myapp.SzakdolgozatBE.topic;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RequestScoped
public class TopicDAO {

    @PersistenceContext(unitName = "SzakdolgozatPU")
    EntityManager em;

    public Topic addTopic(Topic topic) {
        em.getTransaction().begin();
        em.persist(topic);
        em.getTransaction().commit();
        return topic;
    }

    public List<Topic> getAllTopics() {
        return em.createNamedQuery("getAllTopics").getResultList();
    }

    public Topic getTopic(long id) {
        return em.find(Topic.class, id);
    }

    public void deleteTopic(long id) {
        em.getTransaction().begin();
        em.remove(this.getTopic(id));
        em.getTransaction().commit();
    }
}
