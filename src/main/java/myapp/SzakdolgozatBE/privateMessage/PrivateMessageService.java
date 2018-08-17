package myapp.SzakdolgozatBE.privateMessage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class PrivateMessageService {
    
    @Inject
    PrivateMessageDAO dao;
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy. MM. dd. HH:mm:ss");
    
    public PrivateMessage addPrivateMessage(String content) {
        PrivateMessage tmp = new PrivateMessage();
        tmp.setContent(content);
        tmp.setSendingDate(sdf.format(new Date()));
        tmp.setUnread(true);
        return dao.addPrivateMessage(tmp);
    }
    
    public void makeRead(long id) throws NullPointerException {
        PrivateMessage tmp = dao.getPrivateMessage(id);
        if (tmp != null) {
            tmp.setUnread(false);
            dao.makeRead(tmp);
        } else {
            throw new NullPointerException();
        }
    }
    
    public List<PrivateMessage> getSentPrivateMessages() {
        return dao.getSentPrivateMessages();
    }
    
    public List<PrivateMessage> getReceivedPrivateMessages() {
        return dao.getReceivedPrivateMessages();
    }
}
