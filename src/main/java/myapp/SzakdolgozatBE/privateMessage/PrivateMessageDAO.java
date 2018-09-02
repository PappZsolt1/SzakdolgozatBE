package myapp.SzakdolgozatBE.privateMessage;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RequestScoped
public class PrivateMessageDAO {
    
    @PersistenceContext(unitName = "SzakdolgozatPU")
    EntityManager em;
    
    public PrivateMessage getPrivateMessage(long id) {
        return em.find(PrivateMessage.class, id);
    }
    
    public PrivateMessage addPrivateMessage(PrivateMessage privateMessage) {
        em.getTransaction().begin();
        em.persist(privateMessage);
        em.getTransaction().commit();
        return privateMessage;
    }
    
    public void makeRead(PrivateMessage privateMessage) {
        em.getTransaction().begin();
        em.merge(privateMessage);
        em.getTransaction().commit();
    }
    
    public List<PrivateMessage> getConversationPrivateMessages() {
        return em.createNamedQuery("getAllPrivateMessages").getResultList(); //todo
    }
}
