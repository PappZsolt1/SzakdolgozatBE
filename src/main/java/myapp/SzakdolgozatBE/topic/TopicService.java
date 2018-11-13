package myapp.SzakdolgozatBE.topic;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import myapp.SzakdolgozatBE.MyValidationException;
import myapp.SzakdolgozatBE.myUser.MyUser;

@Stateless
public class TopicService {

    @Inject
    TopicDAO dao;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy. MM. dd. HH:mm:ss");

    public Topic addTopic(Topic topic) throws MyValidationException {
        if (topic.getId() != null ||
                topic.getCreateDate() != null ||
                topic.getTitle().matches("^\\S.*\\S$|^\\S$") == false ||
                topic.getTitle().length() > 100 ||
                topic.getDescription().matches("^\\S(.|\\s)*\\S$|^\\S$") == false ||
                topic.getDescription().length() > 60000) {
            throw new MyValidationException();
        } else {
            topic.setCreateDate(sdf.format(new Date()));
            //tmp.setMyUser(myUser);
            return dao.addTopic(topic);
        }
    }

    public List<Topic> getAllTopics() {
        return dao.getAllTopics();
    }

    public Topic getTopic(long id) throws MyValidationException {
        Topic tmp = dao.getTopic(id);
        if (tmp == null) {
            throw new MyValidationException();
        } else {
            return tmp;
        }
    }

    public void deleteTopic(long id) throws MyValidationException {
        Topic tmp = dao.getTopic(id);
        if (tmp == null) {
            throw new MyValidationException();
        } else {
            dao.deleteTopic(id);
        }
    }
}
