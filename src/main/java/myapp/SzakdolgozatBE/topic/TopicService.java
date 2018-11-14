package myapp.SzakdolgozatBE.topic;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import myapp.SzakdolgozatBE.MyValidationException;
import myapp.SzakdolgozatBE.MyValidator;
import myapp.SzakdolgozatBE.myUser.MyUser;

@Stateless
public class TopicService {

    @Inject
    TopicDAO dao;
    
    MyValidator val = new MyValidator();

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy. MM. dd. HH:mm:ss");

    public Topic addTopic(Topic topic) throws MyValidationException {
        if (topic.getId() != null
                || topic.getCreateDate() != null
                || val.validateText(topic.getTitle(), 200) == false
                || val.validateText(topic.getDescription(), 60000) == false) {
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
