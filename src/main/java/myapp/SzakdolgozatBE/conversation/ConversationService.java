package myapp.SzakdolgozatBE.conversation;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ConversationService {
    
    @Inject
    ConversationDAO dao;
    
    public Conversation getConversation(long id) throws NullPointerException {
        Conversation tmp = dao.getConversation(id);
        if (tmp == null) {
            throw new NullPointerException();
        } else {
            return tmp;
        }
    }
    
    public Conversation addConversation(Conversation conversation) {
        return dao.addConversation(conversation);
    }
    
    public List<Conversation> getUserConversations() {
        return dao.getUserConversations();
    }
}
