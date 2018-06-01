package myapp.SzakdolgozatBE.topic;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import myapp.SzakdolgozatBE.myUser.MyUser;

@Stateless
public class TopicService {

    @Inject
    TopicDAO dao;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy. MM. dd. HH:mm:ss");

    public Topic addTopic(String title, String description) {
        Topic tmp = new Topic();
        tmp.setTitle(title);
        tmp.setDescription(description);
        tmp.setCreateDate(sdf.format(new Date()));
        //tmp.setMyUser(myUser);
        return dao.addTopic(tmp);
    }

    public List<Topic> getAllTopics() {
        return dao.getAllTopics();
    }

    public Topic getTopic(long id) throws NullPointerException {
        Topic tmp = dao.getTopic(id);
        if (tmp == null) {
            throw new NullPointerException();
        } else {
            return tmp;
        }
    }

    public void deleteTopic(long id) throws NullPointerException {
        Topic tmp = dao.getTopic(id);
        if (tmp == null) {
            throw new NullPointerException();
        } else {
            dao.deleteTopic(id);
        }
    }
}
