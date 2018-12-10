package myapp.SzakdolgozatBE.episode;

import java.util.ArrayList;
import java.util.List;
import myapp.SzakdolgozatBE.MyValidationException;
import myapp.SzakdolgozatBE.actor.Actor;
import myapp.SzakdolgozatBE.actor.ActorDAO;
import myapp.SzakdolgozatBE.comment.Comment;
import myapp.SzakdolgozatBE.comment.CommentDAO;
import myapp.SzakdolgozatBE.rating.Rating;
import myapp.SzakdolgozatBE.rating.RatingDAO;
import myapp.SzakdolgozatBE.season.Season;
import myapp.SzakdolgozatBE.season.SeasonDAO;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.ArgumentCaptor;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

public class EpisodeServiceTest {
    
    @Mock
    EpisodeDAO daoMock;
    
    @Mock
    SeasonDAO seasonDaoMock;
    
    @Mock
    ActorDAO actorDaoMock;
    
    @Mock
    RatingDAO ratingDaoMock;
    
    @Mock
    CommentDAO commentDaoMock;
    
    @InjectMocks
    EpisodeService service;
    
    @Captor
    private ArgumentCaptor<Rating> argR;
    
    @Captor
    private ArgumentCaptor<Episode> argE;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        
        when(daoMock.getEpisode(any(long.class))).thenReturn(getEpisode());
        
        Season s = new Season();
        s.setId(1L);
        s.setEpisodes(new ArrayList<>());
        when(seasonDaoMock.getSeason(any(long.class))).thenReturn(s);
        when(seasonDaoMock.modifySeason(any(Season.class))).thenAnswer(i -> i.getArguments()[0]);
        
        Actor a = new Actor();
        a.setId(1L);
        a.setEpisodes(new ArrayList<>());
        when(actorDaoMock.getActor(any(long.class))).thenReturn(a);
        when(actorDaoMock.modifyActor(any(Actor.class))).thenAnswer(i -> i.getArguments()[0]);
    }
    
    public Episode getEpisode() {
        Season s = new Season();
        s.setId(1L);
        
        Episode tmp = new Episode();
        tmp.setTitle("cím");
        tmp.seteLength("20 perc");
        tmp.setReleaseDate("2010. 01. 01.");
        tmp.setSeason(s);
        tmp.setActors(new ArrayList<>());
        return tmp;
    }

    @Test
    public void testAddEpisode() throws Exception {
        Episode tmp = getEpisode();
        tmp.setSeason(null);
        Episode a = service.addEpisode(1, tmp);
        assertEquals(a.getTitle(), tmp.getTitle());
        assertEquals((long)a.getSeason().getId(), 1L);
        verify(seasonDaoMock).modifySeason(any(Season.class));
    }
    
    @Test(expected = MyValidationException.class)
    public void testAddEpisodeException1() throws Exception {
        Episode tmp = getEpisode();
        tmp.setSeason(null);
        tmp.setId(1L);
        service.addEpisode(1, tmp);
    }
    
    @Test(expected = MyValidationException.class)
    public void testAddEpisodeException2() throws Exception {
        Episode tmp = getEpisode();
        tmp.setSeason(null);
        tmp.setTitle(" a");
        service.addEpisode(1, tmp);
    }
    
    @Test(expected = MyValidationException.class)
    public void testAddEpisodeException3() throws Exception {
        when(seasonDaoMock.getSeason(any(long.class))).thenReturn(null);
        Episode tmp = getEpisode();
        tmp.setSeason(null);
        service.addEpisode(1, tmp);
    }

    @Test
    public void testGetEpisode() throws Exception {
        Episode tmp = service.getEpisode(1);
        assertEquals(tmp.getTitle(), "cím");
    }
    
    @Test(expected = MyValidationException.class)
    public void testGetEpisodeException() throws Exception {
        when(daoMock.getEpisode(any(long.class))).thenReturn(null);
        service.getEpisode(1);
    }

    @Test
    public void testCheckIfExists1() throws Exception {
        assertEquals(service.checkIfExists(1), true);
    }

    @Test
    public void testCheckIfExists2() throws Exception {
        when(daoMock.getEpisode(any(long.class))).thenReturn(null);
        assertEquals(service.checkIfExists(1), false);
    }
    
    @Test
    public void testGetEpisodeSeasonId() throws Exception {
        long id = service.getEpisodeSeasonId(1);
        assertEquals(id, 1L);
    }
    
    @Test(expected = MyValidationException.class)
    public void testGetEpisodeSeasonIdException() throws Exception {
        when(daoMock.getEpisode(any(long.class))).thenReturn(null);
        service.getEpisodeSeasonId(1);
    }

    @Test
    public void testGetEpisodeActors() throws Exception {
        Episode tmp = getEpisode();
        Actor a = new Actor();
        a.setId(2L);
        tmp.getActors().add(a);
        when(daoMock.getEpisode(any(long.class))).thenReturn(tmp);
        List<Actor> x = service.getEpisodeActors(1);
        assertEquals((long)x.get(0).getId(), 2L);
    }
    
    @Test(expected = MyValidationException.class)
    public void testGetEpisodeActorsException() throws Exception {
        Episode tmp = getEpisode();
        Actor a = new Actor();
        a.setId(2L);
        tmp.getActors().add(a);
        when(daoMock.getEpisode(any(long.class))).thenReturn(null);
        service.getEpisodeActors(1);
    }

    @Test
    public void testAddActorToEpisode() throws Exception {
        Actor tmp = service.addActorToEpisode(1, 1);
        assertEquals((long)tmp.getId(), 1L);
        verify(daoMock).modifyEpisode(any(Episode.class));
        verify(actorDaoMock).modifyActor(any(Actor.class));
    }
    
    @Test(expected = MyValidationException.class)
    public void testAddActorToEpisodeException1() throws Exception {
        when(daoMock.getEpisode(any(long.class))).thenReturn(null);
        service.addActorToEpisode(1, 1);
    }
    
    @Test(expected = MyValidationException.class)
    public void testAddActorToEpisodeException2() throws Exception {
        when(actorDaoMock.getActor(any(long.class))).thenReturn(null);
        service.addActorToEpisode(1, 1);
    }
    
    @Test(expected = MyValidationException.class)
    public void testAddActorToEpisodeException3() throws Exception {
        Episode tmp = getEpisode();
        Actor a = new Actor();
        a.setId(1L);
        tmp.getActors().add(a);
        when(daoMock.getEpisode(any(long.class))).thenReturn(tmp);
        service.addActorToEpisode(1, 1);
    }

    @Test
    public void testRemoveActorFromEpisode() throws Exception {
        Episode tmp = getEpisode();
        Actor a = new Actor();
        a.setId(1L);
        tmp.getActors().add(a);
        when(daoMock.getEpisode(any(long.class))).thenReturn(tmp);
        Actor x = service.removeActorFromEpisode(1, 1);
        assertEquals((long)x.getId(), 1L);
        verify(daoMock).modifyEpisode(any(Episode.class));
        verify(actorDaoMock).modifyActor(any(Actor.class));
    }
    
    @Test(expected = MyValidationException.class)
    public void testRemoveActorFromEpisodeException1() throws Exception {
        when(actorDaoMock.getActor(any(long.class))).thenReturn(null);
        Episode tmp = getEpisode();
        Actor a = new Actor();
        a.setId(1L);
        tmp.getActors().add(a);
        when(daoMock.getEpisode(any(long.class))).thenReturn(tmp);
        service.removeActorFromEpisode(1, 1);
    }
    
    @Test(expected = MyValidationException.class)
    public void testRemoveActorFromEpisodeException2() throws Exception {
        when(daoMock.getEpisode(any(long.class))).thenReturn(null);
        service.removeActorFromEpisode(1, 1);
    }

    @Test
    public void testDeleteEpisode() throws Exception {
        Comment c = new Comment();
        c.setId(10L);
        List<Comment> cs = new ArrayList<>();
        cs.add(c);
        when(commentDaoMock.getAllEpisodeComments(any(Episode.class))).thenReturn(cs);
        service.deleteEpisode(1, 1);
        verify(commentDaoMock).deleteComment(10);
        verify(daoMock).deleteEpisode(any(long.class));
    }
    
    @Test(expected = MyValidationException.class)
    public void testDeleteEpisodeException1() throws Exception {
        when(daoMock.getEpisode(any(long.class))).thenReturn(null);
        service.deleteEpisode(1, 1);
    }
    
    @Test(expected = MyValidationException.class)
    public void testDeleteEpisodeException2() throws Exception {
        Episode tmp = getEpisode();
        Actor a = new Actor();
        a.setId(1L);
        tmp.getActors().add(a);
        when(daoMock.getEpisode(any(long.class))).thenReturn(tmp);
        service.deleteEpisode(1, 1);
    }
    
    @Test(expected = MyValidationException.class)
    public void testDeleteEpisodeException3() throws Exception {
        when(seasonDaoMock.getSeason(any(long.class))).thenReturn(null);
        service.deleteEpisode(1, 1);
    }

    @Test
    public void testModifyEpisode1() throws Exception {
        Episode tmp = getEpisode();
        tmp.setId(1L);
        service.modifyEpisode(1, tmp);
        verify(daoMock).modifyEpisode(any(Episode.class));
    }
    
    @Test
    public void testModifyEpisode2() throws Exception {
        Episode tmp = getEpisode();
        tmp.setId(1L);
        tmp.getSeason().setId(2L);
        when(daoMock.getEpisode(any(long.class))).thenReturn(tmp);
        service.modifyEpisode(1, tmp);
        verify(daoMock).modifyEpisode(any(Episode.class));
        verify(seasonDaoMock, Mockito.times(2)).modifySeason(any(Season.class));
    }
    
    @Test(expected = MyValidationException.class)
    public void testModifyEpisodeException1() throws Exception {
        Episode tmp = getEpisode();
        service.modifyEpisode(1, tmp);
    }
    
    @Test(expected = MyValidationException.class)
    public void testModifyEpisodeException2() throws Exception {
        Episode tmp = getEpisode();
        tmp.setId(1L);
        tmp.setTitle(" a");
        service.modifyEpisode(1, tmp);
    }
    
    @Test(expected = MyValidationException.class)
    public void testModifyEpisodeException3() throws Exception {
        when(daoMock.getEpisode(any(long.class))).thenReturn(null);
        Episode tmp = getEpisode();
        tmp.setId(1L);
        service.modifyEpisode(1, tmp);
    }
    
    @Test(expected = MyValidationException.class)
    public void testModifyEpisodeException4() throws Exception {
        when(seasonDaoMock.getSeason(any(long.class))).thenReturn(null);
        Episode tmp = getEpisode();
        tmp.setId(1L);
        service.modifyEpisode(1, tmp);
    }

    @Test
    public void testChangeRating() throws Exception {
        service.changeRating(1, (byte)8, "valaki");
        verify(daoMock).modifyEpisode(argE.capture());
        verify(ratingDaoMock).addRating(argR.capture());
        assertEquals((int)argE.getValue().getRating(), 8);
        assertEquals(argR.getValue().getUsername(), "valaki");
    }
    
    @Test(expected = MyValidationException.class)
    public void testChangeRatingException1() throws Exception {
        when(daoMock.getEpisode(any(long.class))).thenReturn(null);
        service.changeRating(1, (byte)8, "valaki");
    }
    
    @Test(expected = MyValidationException.class)
    public void testChangeRatingException2() throws Exception {
        service.changeRating(1, (byte)0, "valaki");
    }
    
    @Test(expected = MyValidationException.class)
    public void testChangeRatingException3() throws Exception {
        service.changeRating(1, (byte)8, null);
    }

    @Test
    public void testCanBeDeleted1() throws Exception {
        assertEquals(service.canBeDeleted(1), true);
    }
    
    @Test
    public void testCanBeDeleted2() throws Exception {
        Episode tmp = getEpisode();
        Actor a = new Actor();
        a.setId(1L);
        tmp.getActors().add(a);
        when(daoMock.getEpisode(any(long.class))).thenReturn(tmp);
        assertEquals(service.canBeDeleted(1), false);
    }
    
    @Test(expected = MyValidationException.class)
    public void testCanBeDeletedException() throws Exception {
        when(daoMock.getEpisode(any(long.class))).thenReturn(null);
        service.canBeDeleted(1);
    }

    @Test
    public void testValidateEpisode() throws Exception {
        Episode tmp = getEpisode();
        assertEquals(service.validateEpisode(tmp), true);
        tmp.setTitle(" cím");
        assertEquals(service.validateEpisode(tmp), false);
        tmp.setTitle("cím");
        tmp.setReleaseDate("2010. 01. 50.");
        assertEquals(service.validateEpisode(tmp), false);
        tmp.setReleaseDate("2010. 01. 01.");
        tmp.seteLength("61 perc");
        assertEquals(service.validateEpisode(tmp), false);
    }
}
