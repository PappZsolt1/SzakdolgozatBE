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
            tmp.setUnreadMessages(0);
            return tmp;
        }
    }
    
    public Conversation addConversation(Conversation conversation) {
        return dao.addConversation(conversation);
    }
    
    public List<Conversation> getUserConversations() {
        int unreadMessages = 0;
        List<Conversation> tmp = dao.getUserConversations();
        for (int i = 0; i < tmp.size(); i++) {
            for (int j = 0; j < tmp.get(i).getPrivateMessages().size(); j++) {
                if (tmp.get(i).getPrivateMessages().get(j).isUnread() == true) {
                    unreadMessages++;
                }
            }
            tmp.get(i).setUnreadMessages(unreadMessages);
            unreadMessages = 0;
        }
        return tmp;
    }
}
