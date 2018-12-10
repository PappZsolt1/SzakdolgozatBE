package myapp.SzakdolgozatBE.actor;

import java.util.ArrayList;
import java.util.List;
import myapp.SzakdolgozatBE.MyValidationException;
import myapp.SzakdolgozatBE.Wrapper;
import myapp.SzakdolgozatBE.comment.Comment;
import myapp.SzakdolgozatBE.comment.CommentDAO;
import myapp.SzakdolgozatBE.gender.Gender;
import myapp.SzakdolgozatBE.gender.GenderDAO;
import myapp.SzakdolgozatBE.movie.Movie;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

public class ActorServiceTest {
    
    @Mock
    ActorDAO daoMock;
    
    @Mock
    GenderDAO genderDaoMock;
    
    @Mock
    CommentDAO commentDaoMock;
    
    @InjectMocks
    ActorService service;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        
        Gender g = new Gender();
        when(genderDaoMock.getGender(any(long.class))).thenReturn(g);
        when(daoMock.getResultActors(any(int.class), any(int.class), any(String.class)))
                .thenReturn(new ArrayList<>());
        when(daoMock.getNumberOfResultActors(any(String.class))).thenReturn(49L);
        when(daoMock.getActor(any(long.class))).thenReturn(getActor());
        when(daoMock.modifyActor(any(Actor.class))).thenReturn(getActor());
    }
    
    public Actor getActor() {
        Gender g = new Gender();
        g.setId(1L);
        
        Actor tmp = new Actor();
        tmp.setId(1L);
        tmp.setName("Béla");
        tmp.setGender(g);
        tmp.setBio("Élettörténet.");
        tmp.setBirthPlace("Budapest");
        tmp.setBirthDate("1980. 01. 01.");
        tmp.setImageUrl("a");
        tmp.setMovies(new ArrayList<>());
        tmp.setEpisodes(new ArrayList<>());
        return tmp;
    }
    
    @Test
    public void testAddActor() throws Exception {
        when(daoMock.addActor(any(Actor.class))).thenAnswer(i -> i.getArguments()[0]);
        Actor tmp = getActor();
        tmp.setId(null);
        Actor a = service.addActor(tmp);
        assertEquals(a.getName(), tmp.getName());
    }
    
    @Test(expected = MyValidationException.class)
    public void testAddActorException1() throws Exception {
        Actor tmp = getActor();
        service.addActor(tmp);
    }
    
    @Test(expected = MyValidationException.class)
    public void testAddActorException2() throws Exception {
        Actor tmp = getActor();
        tmp.setName(" Béla");
        service.addActor(tmp);
    }

    @Test
    public void testGetActor() throws Exception {
        Actor tmp = getActor();
        Actor a = service.getActor(1);
        assertEquals(a.getName(), tmp.getName());
    }

    @Test(expected = MyValidationException.class)
    public void testGetActorException() throws Exception {
        when(daoMock.getActor(any(long.class))).thenReturn(null);
        service.getActor(1);
    }
    
    @Test
    public void testCheckIfExists1() throws Exception {
        boolean tmp = service.checkIfExists(1);
        assertEquals(tmp, true);
    }
    
    @Test
    public void testCheckIfExists2() throws Exception {
        when(daoMock.getActor(any(long.class))).thenReturn(null);
        boolean tmp = service.checkIfExists(1);
        assertEquals(tmp, false);
    }

    @Test
    public void testGetResultActors() throws Exception {
        Wrapper tmp = service.getResultActors(1, 10, "a");
        assertEquals(tmp.getTotal(), 49);
        assertNotNull(tmp.getResults());
    }
    
    @Test(expected = MyValidationException.class)
    public void testGetResultActorsException1() throws Exception {
        service.getResultActors(-1, 10, "a");
    }
    
    @Test(expected = MyValidationException.class)
    public void testGetResultActorsException2() throws Exception {
        service.getResultActors(1, 11, "a");
    }
    
    @Test(expected = MyValidationException.class)
    public void testGetResultActorsException3() throws Exception {
        service.getResultActors(1, 10, " a");
    }
    
    @Test(expected = MyValidationException.class)
    public void testGetResultActorsException4() throws Exception {
        service.getResultActors(6, 10, "a");
    }

    @Test
    public void testDeleteActor() throws Exception {
        Comment c = new Comment();
        c.setId(10L);
        List<Comment> cs = new ArrayList<>();
        cs.add(c);
        when(commentDaoMock.getAllActorComments(any(Actor.class))).thenReturn(cs);
        service.deleteActor(1);
        verify(commentDaoMock).deleteComment(10);
        verify(daoMock).deleteActor(any(long.class));
    }
    
    @Test(expected = MyValidationException.class)
    public void testDeleteActorException1() throws Exception {
        when(daoMock.getActor(any(long.class))).thenReturn(null);
        service.deleteActor(1);
    }
    
    @Test(expected = MyValidationException.class)
    public void testDeleteActorException2() throws Exception {
        Movie m = new Movie();
        List<Movie> ms = new ArrayList<>();
        ms.add(m);
        Actor tmp = getActor();
        tmp.setMovies(ms);
        when(daoMock.getActor(any(long.class))).thenReturn(tmp);
        service.deleteActor(tmp.getId());
    }

    @Test
    public void testModifyActor() throws Exception {
        Actor tmp = getActor();
        Actor a = service.modifyActor(tmp);
        assertEquals(a.getName(), tmp.getName());
    }
    
    @Test(expected = MyValidationException.class)
    public void testModifyActorException1() throws Exception {
        Actor tmp = getActor();
        tmp.setId(null);
        service.modifyActor(tmp);
    }
    
    @Test(expected = MyValidationException.class)
    public void testModifyActorException2() throws Exception {
        Actor tmp = getActor();
        tmp.setName("\nBéla");
        service.modifyActor(tmp);
    }
    
    @Test(expected = MyValidationException.class)
    public void testModifyActorException3() throws Exception {
        when(daoMock.getActor(any(long.class))).thenReturn(null);
        Actor tmp = getActor();
        service.modifyActor(tmp);
    }

    @Test
    public void testCanBeDeleted1() throws Exception {
        assertEquals(service.canBeDeleted(1), true);
    }
    
    @Test
    public void testCanBeDeleted2() throws Exception {
        Movie m = new Movie();
        List<Movie> ms = new ArrayList<>();
        ms.add(m);
        Actor tmp = getActor();
        tmp.setMovies(ms);
        when(daoMock.getActor(any(long.class))).thenReturn(tmp);
        assertEquals(service.canBeDeleted(1), false);
    }
    
    @Test(expected = MyValidationException.class)
    public void testCanBeDeletedException() throws Exception {
        when(daoMock.getActor(any(long.class))).thenReturn(null);
        service.canBeDeleted(1);
    }
    
    @Test
    public void testValidateActor() throws Exception {
        Actor tmp = getActor();
        
        assertEquals(service.validateActor(tmp), true);
        tmp.setName(" Béla");
        assertEquals(service.validateActor(tmp), false);
        tmp.setName("Béla");
        tmp.setBio("Élettörténet. ");
        assertEquals(service.validateActor(tmp), false);
        tmp.setBio("Élettörténet.");
        tmp.setBirthPlace("\nBudapest");
        assertEquals(service.validateActor(tmp), false);
        tmp.setBirthPlace("Budapest");
        tmp.setBirthDate("2980. 01. 01.");
        assertEquals(service.validateActor(tmp), false);
        tmp.setBirthDate("1980. 01. 01.");
        tmp.setImageUrl("");
        assertEquals(service.validateActor(tmp), false);
        tmp.setImageUrl("a");
        when(genderDaoMock.getGender(any(long.class))).thenReturn(null);
        assertEquals(service.validateActor(tmp), false);
    }
}
