package myapp.SzakdolgozatBE.movie;

import java.util.ArrayList;
import java.util.List;
import myapp.SzakdolgozatBE.MyValidationException;
import myapp.SzakdolgozatBE.Wrapper;
import myapp.SzakdolgozatBE.actor.Actor;
import myapp.SzakdolgozatBE.actor.ActorDAO;
import myapp.SzakdolgozatBE.ageClassification.AgeClassification;
import myapp.SzakdolgozatBE.ageClassification.AgeClassificationDAO;
import myapp.SzakdolgozatBE.comment.Comment;
import myapp.SzakdolgozatBE.comment.CommentDAO;
import myapp.SzakdolgozatBE.genre.Genre;
import myapp.SzakdolgozatBE.genre.GenreDAO;
import myapp.SzakdolgozatBE.rating.Rating;
import myapp.SzakdolgozatBE.rating.RatingDAO;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.ArgumentCaptor;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

public class MovieServiceTest {
    
    @Mock
    MovieDAO daoMock;
    
    @Mock
    AgeClassificationDAO ageClassificationDaoMock;
    
    @Mock
    GenreDAO genreDaoMock;
    
    @Mock
    ActorDAO actorDaoMock;
    
    @Mock
    RatingDAO ratingDaoMock;
    
    @Mock
    CommentDAO commentDaoMock;
    
    @InjectMocks
    MovieService service;
    
    @Captor
    private ArgumentCaptor<Rating> argR;
    
    @Captor
    private ArgumentCaptor<Movie> argM;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        
        when(daoMock.getMovie(any(long.class))).thenReturn(getMovie());
        when(daoMock.addMovie(any(Movie.class))).thenAnswer(i -> i.getArguments()[0]);
        
        Actor a = new Actor();
        a.setId(1L);
        a.setMovies(new ArrayList<>());
        when(actorDaoMock.getActor(any(long.class))).thenReturn(a);
        
        AgeClassification ag = new AgeClassification();
        ag.setId(1L);
        when(ageClassificationDaoMock.getAgeClassification(any(long.class))).thenReturn(ag);
        
        Genre g = new Genre();
        g.setId(1L);
        when(genreDaoMock.getGenre(any(long.class))).thenReturn(g);
        
        when(daoMock.getResultMovies(any(int.class), any(int.class), any(String.class)))
                .thenReturn(new ArrayList<>());
        when(daoMock.getNumberOfResultMovies(any(String.class))).thenReturn(90L);
    }
    
    public Movie getMovie() {
        AgeClassification a = new AgeClassification();
        a.setId(1L);
        
        Genre g = new Genre();
        g.setId(1L);
        
        Movie tmp = new Movie();
        tmp.setTitle("cím");
        tmp.setBudget(1);
        tmp.setReleaseYear(2000);
        tmp.setmLength("2 óra 10 perc");
        tmp.setAgeClassification(a);
        tmp.setGenre(g);
        tmp.setImageUrl("a");
        tmp.setActors(new ArrayList<>());
        tmp.setComments(new ArrayList<>());
        return tmp;
    }

    @Test
    public void testAddMovie() throws Exception {
        Movie tmp = getMovie();
        Movie a = service.addMovie(tmp);
        assertEquals(a.getTitle(), "cím");
    }
    
    @Test(expected = MyValidationException.class)
    public void testAddMovieException1() throws Exception {
        Movie tmp = getMovie();
        tmp.setId(1L);
        service.addMovie(tmp);
    }
    
    @Test(expected = MyValidationException.class)
    public void testAddMovieException2() throws Exception {
        Movie tmp = getMovie();
        tmp.setTitle(" cím");
        service.addMovie(tmp);
    }

    @Test
    public void testGetMovie() throws Exception {
        Movie tmp = service.getMovie(1);
        assertEquals(tmp.getTitle(), "cím");
    }
    
    @Test(expected = MyValidationException.class)
    public void testGetMovieException() throws Exception {
        when(daoMock.getMovie(any(long.class))).thenReturn(null);
        service.getMovie(1);
    }

    @Test
    public void testCheckIfExists1() throws Exception {
        assertEquals(service.checkIfExists(1), true);
    }
    
    @Test
    public void testCheckIfExists2() throws Exception {
        when(daoMock.getMovie(any(long.class))).thenReturn(null);
        assertEquals(service.checkIfExists(1), false);
    }

    @Test
    public void testGetMovieActors() throws Exception {
        Movie tmp = getMovie();
        Actor a = new Actor();
        a.setId(2L);
        tmp.getActors().add(a);
        when(daoMock.getMovie(any(long.class))).thenReturn(tmp);
        List<Actor> x = service.getMovieActors(1);
        assertEquals((long)x.get(0).getId(), 2L);
    }
    
    @Test(expected = MyValidationException.class)
    public void testGetMovieActorsException() throws Exception {
        when(daoMock.getMovie(any(long.class))).thenReturn(null);
        service.getMovieActors(1);
    }

    @Test
    public void testAddActorToMovie() throws Exception {
        Actor tmp = service.addActorToMovie(1, 1);
        assertEquals((long)tmp.getId(), 1L);
        verify(daoMock).modifyMovie(any(Movie.class));
        verify(actorDaoMock).modifyActor(any(Actor.class));
    }
    
    @Test(expected = MyValidationException.class)
    public void testAddActorToMovieException1() throws Exception {
        when(daoMock.getMovie(any(long.class))).thenReturn(null);
        service.addActorToMovie(1, 1);
    }
    
    @Test(expected = MyValidationException.class)
    public void testAddActorToMovieException2() throws Exception {
        when(actorDaoMock.getActor(any(long.class))).thenReturn(null);
        service.addActorToMovie(1, 1);
    }
    
    @Test(expected = MyValidationException.class)
    public void testAddActorToMovieException3() throws Exception {
        Movie tmp = getMovie();
        Actor a = new Actor();
        a.setId(1L);
        tmp.getActors().add(a);
        when(daoMock.getMovie(any(long.class))).thenReturn(tmp);
        service.addActorToMovie(1, 1);
    }

    @Test
    public void testRemoveActorFromMovie() throws Exception {
        Movie tmp = getMovie();
        Actor a = new Actor();
        a.setId(1L);
        tmp.getActors().add(a);
        when(daoMock.getMovie(any(long.class))).thenReturn(tmp);
        Actor x = service.removeActorFromMovie(1, 1);
        assertEquals((long)x.getId(), 1L);
        verify(daoMock).modifyMovie(any(Movie.class));
        verify(actorDaoMock).modifyActor(any(Actor.class));
    }
    
    @Test(expected = MyValidationException.class)
    public void testRemoveActorFromMovieException1() throws Exception {
        when(actorDaoMock.getActor(any(long.class))).thenReturn(null);
        Movie tmp = getMovie();
        Actor a = new Actor();
        a.setId(1L);
        tmp.getActors().add(a);
        when(daoMock.getMovie(any(long.class))).thenReturn(tmp);
        service.removeActorFromMovie(1, 1);
    }
    
    @Test(expected = MyValidationException.class)
    public void testRemoveActorFromMovieException2() throws Exception {
        when(daoMock.getMovie(any(long.class))).thenReturn(null);
        service.removeActorFromMovie(1, 1);
    }

    @Test
    public void testGetResultMovies() throws Exception {
        Wrapper tmp = service.getResultMovies(1, 10, "a");
        assertEquals(tmp.getTotal(), 90);
        assertNotNull(tmp.getResults());
    }
    
    @Test(expected = MyValidationException.class)
    public void testGetResultMoviesException1() throws Exception {
        service.getResultMovies(0, 10, "a");
    }
    
    @Test(expected = MyValidationException.class)
    public void testGetResultMoviesException2() throws Exception {
        service.getResultMovies(1, 5, "a");
    }
    
    @Test(expected = MyValidationException.class)
    public void testGetResultMoviesException3() throws Exception {
        service.getResultMovies(1, 10, " a");
    }
    
    @Test(expected = MyValidationException.class)
    public void testGetResultMoviesException4() throws Exception {
        service.getResultMovies(6, 20, "a");
    }

    @Test
    public void testDeleteMovie() throws Exception {
        Comment c = new Comment();
        c.setId(10L);
        List<Comment> cs = new ArrayList<>();
        cs.add(c);
        when(commentDaoMock.getAllMovieComments(any(Movie.class))).thenReturn(cs);
        service.deleteMovie(1);
        verify(commentDaoMock).deleteComment(10);
        verify(daoMock).deleteMovie(any(long.class));
    }
    
    @Test(expected = MyValidationException.class)
    public void testDeleteMovieException1() throws Exception {
        when(daoMock.getMovie(any(long.class))).thenReturn(null);
        service.deleteMovie(1);
    }
    
    @Test(expected = MyValidationException.class)
    public void testDeleteMovieException2() throws Exception {
        Movie tmp = getMovie();
        Actor a = new Actor();
        a.setId(1L);
        tmp.getActors().add(a);
        when(daoMock.getMovie(any(long.class))).thenReturn(tmp);
        service.deleteMovie(1);
    }

    @Test
    public void testModifyMovie() throws Exception {
        when(daoMock.modifyMovie(any(Movie.class))).thenAnswer(i -> i.getArguments()[0]);
        Movie tmp = getMovie();
        tmp.setId(1L);
        Movie a = service.modifyMovie(tmp);
        assertEquals(a.getTitle(), tmp.getTitle());
    }
    
    @Test(expected = MyValidationException.class)
    public void testModifyMovieException1() throws Exception {
        service.modifyMovie(getMovie());
    }
    
    @Test(expected = MyValidationException.class)
    public void testModifyMovieException2() throws Exception {
        Movie tmp = getMovie();
        tmp.setId(1L);
        tmp.setTitle(" a");
        service.modifyMovie(tmp);
    }
    
    @Test(expected = MyValidationException.class)
    public void testModifyMovieException3() throws Exception {
        when(daoMock.getMovie(any(long.class))).thenReturn(null);
        Movie tmp = getMovie();
        tmp.setId(1L);
        service.modifyMovie(tmp);
    }

    @Test
    public void testChangeRating() throws Exception {
        service.changeRating(1, (byte)8, "valaki");
        verify(daoMock).modifyMovie(argM.capture());
        verify(ratingDaoMock).addRating(argR.capture());
        assertEquals((int)argM.getValue().getRating(), 8);
        assertEquals(argR.getValue().getUsername(), "valaki");
    }
    
    @Test(expected = MyValidationException.class)
    public void testChangeRatingException1() throws Exception {
        when(daoMock.getMovie(any(long.class))).thenReturn(null);
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
        Movie tmp = getMovie();
        Actor a = new Actor();
        a.setId(1L);
        tmp.getActors().add(a);
        when(daoMock.getMovie(any(long.class))).thenReturn(tmp);
        assertEquals(service.canBeDeleted(1), false);
    }
    
    @Test(expected = MyValidationException.class)
    public void testCanBeDeletedException() throws Exception {
        when(daoMock.getMovie(any(long.class))).thenReturn(null);
        service.canBeDeleted(1);
    }

    @Test
    public void testValidateMovie() throws Exception {
        Movie tmp = getMovie();
        assertEquals(service.validateMovie(tmp), true);
        tmp.setTitle(" cím");
        assertEquals(service.validateMovie(tmp), false);
        tmp.setTitle("cím");
        tmp.setBudget(-1);
        assertEquals(service.validateMovie(tmp), false);
        tmp.setBudget(1);
        tmp.setReleaseYear(1000);
        assertEquals(service.validateMovie(tmp), false);
        tmp.setReleaseYear(2000);
        tmp.setmLength("2 óra 100 perc");
        assertEquals(service.validateMovie(tmp), false);
        tmp.setmLength("2 óra 10 perc");
        tmp.setImageUrl(" a");
        assertEquals(service.validateMovie(tmp), false);
        tmp.setImageUrl("a");
        when(ageClassificationDaoMock.getAgeClassification(any(long.class))).thenReturn(null);
        assertEquals(service.validateMovie(tmp), false);
        AgeClassification a = new AgeClassification();
        a.setId(1L);
        when(ageClassificationDaoMock.getAgeClassification(any(long.class))).thenReturn(a);
        when(genreDaoMock.getGenre(any(long.class))).thenReturn(null);
        assertEquals(service.validateMovie(tmp), false);
    }
    
}
