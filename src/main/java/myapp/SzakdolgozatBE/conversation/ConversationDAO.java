package myapp.SzakdolgozatBE.conversation;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RequestScoped
public class ConversationDAO {
    
    @PersistenceContext(unitName = "SzakdolgozatPU")
    EntityManager em;
    
    public Conversation getConversation(long id) {
        return em.find(Conversation.class, id);
    }
    
    public Conversation addConversation(Conversation conversation) {
        em.getTransaction().begin();
        em.persist(conversation);
        em.getTransaction().commit();
        return conversation;
    }
    
    public List<Conversation> getUserConversations() {
        return em.createNamedQuery("getAllConversations").getResultList(); //todo
    }
}
