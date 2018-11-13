package myapp.SzakdolgozatBE.privateMessage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import myapp.SzakdolgozatBE.MyValidationException;

@Stateless
public class PrivateMessageService {
    
    @Inject
    PrivateMessageDAO dao;
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy. MM. dd. HH:mm:ss");
    
    public PrivateMessage addPrivateMessage(String content) throws MyValidationException {
        if (content.matches("^\\S(.|\\s)*\\S$|^\\S$") == false ||
                content.length() > 60000) {
            throw new MyValidationException();
        } else {
            PrivateMessage tmp = new PrivateMessage();
            tmp.setContent(content);
            tmp.setSendingDate(sdf.format(new Date()));
            tmp.setUnread(true);
            return dao.addPrivateMessage(tmp);
        }
    }
    
    public void makeRead(long id) throws MyValidationException {
        PrivateMessage tmp = dao.getPrivateMessage(id);
        if (tmp != null) {
            tmp.setUnread(false);
            dao.makeRead(tmp);
        } else {
            throw new MyValidationException();
        }
    }
    
    public List<PrivateMessage> getConversationPrivateMessages() {
        return dao.getConversationPrivateMessages();
    }
}
