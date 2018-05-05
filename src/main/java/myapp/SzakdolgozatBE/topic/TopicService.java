package myapp.SzakdolgozatBE.topic;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import myapp.SzakdolgozatBE.myUser.MyUser;

@Stateless
public class TopicService {
    
    @Inject TopicDAO dao;
    
    public Topic addTopic(String title, String description, MyUser myUser) {
        Topic tmp = new Topic();
        tmp.setTitle(title);
        tmp.setDescription(description);
        tmp.setMyUser(myUser);
        return dao.addTopic(tmp);
    }
    
    public List<Topic> getAllTopics() {
        return dao.getAllTopics();
    }
    
    public Topic getTopic(long id) throws NullPointerException {
        return dao.getTopic(id);
    }
    
    public void deleteTopic(long id) throws NullPointerException {
        dao.deleteTopic(id);
    }
}
