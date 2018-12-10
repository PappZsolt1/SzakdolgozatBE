package myapp.SzakdolgozatBE.topic;

import java.util.ArrayList;
import java.util.List;
import myapp.SzakdolgozatBE.MyValidationException;
import myapp.SzakdolgozatBE.Wrapper;
import myapp.SzakdolgozatBE.comment.Comment;
import myapp.SzakdolgozatBE.comment.CommentDAO;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

public class TopicServiceTest {
    
    @Mock
    TopicDAO daoMock;
    
    @Mock
    CommentDAO commentDaoMock;
    
    @InjectMocks
    TopicService service;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        
        when(daoMock.getTopic(any(long.class))).thenReturn(getTopic());
        when(daoMock.getAllTopics(any(int.class), any(int.class))).thenReturn(new ArrayList<>());
        when(daoMock.getNumberOfAllTopics()).thenReturn(100L);
    }
    
    public Topic getTopic() {
        Topic tmp = new Topic();
        tmp.setTitle("cím");
        tmp.setDescription("leírás");
        tmp.setUsername("valaki");
        tmp.setComments(new ArrayList<>());
        return tmp;
    }

    @Test
    public void testAddTopic() throws Exception {
        when(daoMock.addTopic(any(Topic.class))).thenAnswer(i -> i.getArguments()[0]);
        Topic tmp = service.addTopic(getTopic());
        assertEquals(tmp.getTitle(), "cím");
    }
    
    @Test(expected = MyValidationException.class)
    public void testAddTopicException1() throws Exception {
        Topic tmp = getTopic();
        tmp.setId(1L);
        service.addTopic(tmp);
    }
    
    @Test(expected = MyValidationException.class)
    public void testAddTopicException2() throws Exception {
        Topic tmp = getTopic();
        tmp.setUsername(null);
        service.addTopic(tmp);
    }
    
    @Test(expected = MyValidationException.class)
    public void testAddTopicException3() throws Exception {
        Topic tmp = getTopic();
        tmp.setCreateDate("a");
        service.addTopic(tmp);
    }
    
    @Test(expected = MyValidationException.class)
    public void testAddTopicException4() throws Exception {
        Topic tmp = getTopic();
        tmp.setTitle(" cím");
        service.addTopic(tmp);
    }
    
    @Test(expected = MyValidationException.class)
    public void testAddTopicException5() throws Exception {
        Topic tmp = getTopic();
        tmp.setDescription(" leírás");
        service.addTopic(tmp);
    }

    @Test
    public void testGetAllTopics() throws Exception {
        Wrapper tmp = service.getAllTopics(1, 10);
        assertEquals(tmp.getTotal(), 100);
        assertNotNull(tmp.getResults());
    }
    
    @Test(expected = MyValidationException.class)
    public void testGetAllTopicsException1() throws Exception {
        service.getAllTopics(0, 10);
    }
    
    @Test(expected = MyValidationException.class)
    public void testGetAllTopicsException2() throws Exception {
        service.getAllTopics(1, 11);
    }
    
    @Test(expected = MyValidationException.class)
    public void testGetAllTopicsException3() throws Exception {
        service.getAllTopics(5, 30);
    }

    @Test
    public void testGetTopic() throws Exception {
        Topic tmp = service.getTopic(1);
        assertEquals(tmp.getTitle(), "cím");
    }
    
    @Test(expected = MyValidationException.class)
    public void testGetTopicException() throws Exception {
        when(daoMock.getTopic(any(long.class))).thenReturn(null);
        service.getTopic(1);
    }

    @Test
    public void testDeleteTopic() throws Exception {
        Comment c = new Comment();
        c.setId(10L);
        List<Comment> cs = new ArrayList<>();
        cs.add(c);
        when(commentDaoMock.getAllTopicComments(any(Topic.class))).thenReturn(cs);
        service.deleteTopic(1);
        verify(commentDaoMock).deleteComment(10);
        verify(daoMock).deleteTopic(any(long.class));
    }
    
    @Test(expected = MyValidationException.class)
    public void testDeleteTopicException() throws Exception {
        when(daoMock.getTopic(any(long.class))).thenReturn(null);
        service.deleteTopic(1);
    }
}
