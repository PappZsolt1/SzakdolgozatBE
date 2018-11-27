package myapp.SzakdolgozatBE.topic;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import myapp.SzakdolgozatBE.MyValidationException;
import myapp.SzakdolgozatBE.MyValidator;
import myapp.SzakdolgozatBE.Wrapper;
import myapp.SzakdolgozatBE.comment.Comment;
import myapp.SzakdolgozatBE.comment.CommentDAO;
import myapp.SzakdolgozatBE.myUser.MyUser;

@Stateless
public class TopicService {

    @Inject
    TopicDAO dao;
    
    @Inject
    CommentDAO commentDao;
    
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

    public Wrapper getAllTopics(int page, int size) throws MyValidationException {
        if (page < 1 || val.validateSize(size) == false) {
            throw new MyValidationException();
        }
        int offset = (page - 1) * size;
        List<Topic> results = dao.getAllTopics(offset, size);
        long total = dao.getNumberOfAllTopics();
        if (total > 0 && offset >= total) {
            throw new MyValidationException();
        }
        return new Wrapper(results, total);
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
            List<Comment> comments = commentDao.getAllTopicComments(tmp);
            for (Comment comment : comments) {
                commentDao.deleteComment(comment.getId());
            }
            dao.deleteTopic(id);
        }
    }
}
