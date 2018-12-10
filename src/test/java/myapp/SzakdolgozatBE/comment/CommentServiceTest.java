package myapp.SzakdolgozatBE.comment;

import java.util.ArrayList;
import myapp.SzakdolgozatBE.MyValidationException;
import myapp.SzakdolgozatBE.Wrapper;
import myapp.SzakdolgozatBE.actor.Actor;
import myapp.SzakdolgozatBE.actor.ActorDAO;
import myapp.SzakdolgozatBE.article.Article;
import myapp.SzakdolgozatBE.article.ArticleDAO;
import myapp.SzakdolgozatBE.episode.Episode;
import myapp.SzakdolgozatBE.episode.EpisodeDAO;
import myapp.SzakdolgozatBE.movie.Movie;
import myapp.SzakdolgozatBE.movie.MovieDAO;
import myapp.SzakdolgozatBE.topic.Topic;
import myapp.SzakdolgozatBE.topic.TopicDAO;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

public class CommentServiceTest {
    
    @Mock
    CommentDAO daoMock;
    
    @Mock
    MovieDAO movieDaoMock;
    
    @Mock
    EpisodeDAO episodeDaoMock;
    
    @Mock
    ActorDAO actorDaoMock;
    
    @Mock
    ArticleDAO articleDaoMock;
    
    @Mock
    TopicDAO TopicDaoMock;
    
    @InjectMocks
    CommentService service;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        
        Movie movie = new Movie();
        movie.setId(1L);
        when(movieDaoMock.getMovie(any(long.class))).thenReturn(movie);
        
        Actor actor = new Actor();
        actor.setId(2L);
        when(actorDaoMock.getActor(any(long.class))).thenReturn(actor);
        
        Article article = new Article();
        article.setId(3L);
        when(articleDaoMock.getArticle(any(long.class))).thenReturn(article);
        
        Episode episode = new Episode();
        episode.setId(4L);
        when(episodeDaoMock.getEpisode(any(long.class))).thenReturn(episode);
        
        Topic topic = new Topic();
        topic.setId(5L);
        when(TopicDaoMock.getTopic(any(long.class))).thenReturn(topic);
        
        when(daoMock.addComment(any(Comment.class))).thenAnswer(i -> i.getArguments()[0]);
        when(daoMock.moderateComment(any(Comment.class))).thenAnswer(i -> i.getArguments()[0]);
        
        when(daoMock.getMovieComments(any(int.class), any(int.class), any(Movie.class)))
                .thenReturn(new ArrayList<>());
        when(daoMock.getNumberOfMovieComments(any(Movie.class))).thenReturn(8L);
        when(daoMock.getActorComments(any(int.class), any(int.class), any(Actor.class)))
                .thenReturn(new ArrayList<>());
        when(daoMock.getNumberOfActorComments(any(Actor.class))).thenReturn(8L);
        when(daoMock.getArticleComments(any(int.class), any(int.class), any(Article.class)))
                .thenReturn(new ArrayList<>());
        when(daoMock.getNumberOfArticleComments(any(Article.class))).thenReturn(8L);
        when(daoMock.getEpisodeComments(any(int.class), any(int.class), any(Episode.class)))
                .thenReturn(new ArrayList<>());
        when(daoMock.getNumberOfEpisodeComments(any(Episode.class))).thenReturn(8L);
        when(daoMock.getTopicComments(any(int.class), any(int.class), any(Topic.class)))
                .thenReturn(new ArrayList<>());
        when(daoMock.getNumberOfTopicComments(any(Topic.class))).thenReturn(8L);
    }
    
    public Comment getComment() {
        Comment tmp = new Comment();
        tmp.setContent("tartalom");
        tmp.setUsername("valaki");
        return tmp;
    }

    @Test
    public void testAddMovieComment() throws Exception {
        Comment tmp = getComment();
        Comment a = service.addMovieComment(1, tmp);
        assertEquals(a.getContent(), tmp.getContent());
        assertNotNull(a.getPostDate());
        assertEquals((long)a.getMovie().getId(), 1L);
    }
    
    @Test(expected = MyValidationException.class)
    public void testAddMovieCommentException1() throws Exception {
        Comment tmp = getComment();
        tmp.setModerated(true);
        service.addMovieComment(1, tmp);
    }
    
    @Test(expected = MyValidationException.class)
    public void testAddMovieCommentException2() throws Exception {
        when(movieDaoMock.getMovie(any(long.class))).thenReturn(null);
        Comment tmp = getComment();
        service.addMovieComment(1, tmp);
    }

    @Test
    public void testAddActorComment() throws Exception {
        Comment tmp = getComment();
        Comment a = service.addActorComment(2, tmp);
        assertEquals(a.getContent(), tmp.getContent());
        assertNotNull(a.getPostDate());
        assertEquals((long)a.getActor().getId(), 2L);
    }
    
    @Test(expected = MyValidationException.class)
    public void testAddActorCommentException1() throws Exception {
        Comment tmp = getComment();
        tmp.setModerated(true);
        service.addActorComment(2, tmp);
    }
    
    @Test(expected = MyValidationException.class)
    public void testAddActorCommentException2() throws Exception {
        when(actorDaoMock.getActor(any(long.class))).thenReturn(null);
        Comment tmp = getComment();
        service.addActorComment(2, tmp);
    }

    @Test
    public void testAddArticleComment() throws Exception {
        Comment tmp = getComment();
        Comment a = service.addArticleComment(3, tmp);
        assertEquals(a.getContent(), tmp.getContent());
        assertNotNull(a.getPostDate());
        assertEquals((long)a.getArticle().getId(), 3L);
    }
    
    @Test(expected = MyValidationException.class)
    public void testAddArticleCommentException1() throws Exception {
        Comment tmp = getComment();
        tmp.setModerated(true);
        service.addArticleComment(3, tmp);
    }
    
    @Test(expected = MyValidationException.class)
    public void testAddArticleCommentException2() throws Exception {
        when(articleDaoMock.getArticle(any(long.class))).thenReturn(null);
        Comment tmp = getComment();
        service.addArticleComment(3, tmp);
    }

    @Test
    public void testAddEpisodeComment() throws Exception {
        Comment tmp = getComment();
        Comment a = service.addEpisodeComment(4, tmp);
        assertEquals(a.getContent(), tmp.getContent());
        assertNotNull(a.getPostDate());
        assertEquals((long)a.getEpisode().getId(), 4L);
    }
    
    @Test(expected = MyValidationException.class)
    public void testAddEpisodeCommentException1() throws Exception {
        Comment tmp = getComment();
        tmp.setModerated(true);
        service.addEpisodeComment(4, tmp);
    }
    
    @Test(expected = MyValidationException.class)
    public void testAddEpisodeCommentException2() throws Exception {
        when(episodeDaoMock.getEpisode(any(long.class))).thenReturn(null);
        Comment tmp = getComment();
        service.addEpisodeComment(4, tmp);
    }

    @Test
    public void testAddTopicComment() throws Exception {
        Comment tmp = getComment();
        Comment a = service.addTopicComment(5, tmp);
        assertEquals(a.getContent(), tmp.getContent());
        assertNotNull(a.getPostDate());
        assertEquals((long)a.getTopic().getId(), 5L);
    }
    
    @Test(expected = MyValidationException.class)
    public void testAddTopicCommentException1() throws Exception {
        Comment tmp = getComment();
        tmp.setModerated(true);
        service.addTopicComment(5, tmp);
    }
    
    @Test(expected = MyValidationException.class)
    public void testAddTopicCommentException2() throws Exception {
        when(TopicDaoMock.getTopic(any(long.class))).thenReturn(null);
        Comment tmp = getComment();
        service.addTopicComment(5, tmp);
    }

    @Test
    public void testGetMovieComments() throws Exception {
        Wrapper tmp = service.getMovieComments(1, 10, 1);
        assertEquals(tmp.getTotal(), 8);
        assertNotNull(tmp.getResults());
    }
    
    @Test(expected = MyValidationException.class)
    public void testGetMovieCommentsException1() throws Exception {
        service.getMovieComments(0, 10, 1);
    }
    
    @Test(expected = MyValidationException.class)
    public void testGetMovieCommentsException2() throws Exception {
        service.getMovieComments(1, 11, 1);
    }
    
    @Test(expected = MyValidationException.class)
    public void testGetMovieCommentsException3() throws Exception {
        service.getMovieComments(2, 10, 1);
    }
    
    @Test(expected = MyValidationException.class)
    public void testGetMovieCommentsException4() throws Exception {
        when(movieDaoMock.getMovie(any(long.class))).thenReturn(null);
        service.getMovieComments(1, 10, 1);
    }

    @Test
    public void testGetEpisodeComments() throws Exception {
        Wrapper tmp = service.getEpisodeComments(1, 10, 1);
        assertEquals(tmp.getTotal(), 8);
        assertNotNull(tmp.getResults());
    }
    
    @Test(expected = MyValidationException.class)
    public void testGetEpisodeCommentsException1() throws Exception {
        service.getEpisodeComments(0, 10, 1);
    }
    
    @Test(expected = MyValidationException.class)
    public void testGetEpisodeCommentsException2() throws Exception {
        service.getEpisodeComments(1, 11, 1);
    }
    
    @Test(expected = MyValidationException.class)
    public void testGetEpisodeCommentsException3() throws Exception {
        service.getEpisodeComments(2, 10, 1);
    }
    
    @Test(expected = MyValidationException.class)
    public void testGetEpisodeCommentsException4() throws Exception {
        when(episodeDaoMock.getEpisode(any(long.class))).thenReturn(null);
        service.getEpisodeComments(1, 10, 1);
    }

    @Test
    public void testGetActorComments() throws Exception {
        Wrapper tmp = service.getActorComments(1, 10, 1);
        assertEquals(tmp.getTotal(), 8);
        assertNotNull(tmp.getResults());
    }
    
    @Test(expected = MyValidationException.class)
    public void testGetActorCommentsException1() throws Exception {
        service.getActorComments(0, 10, 1);
    }
    
    @Test(expected = MyValidationException.class)
    public void testGetActorCommentsException2() throws Exception {
        service.getActorComments(1, 11, 1);
    }
    
    @Test(expected = MyValidationException.class)
    public void testGetActorCommentsException3() throws Exception {
        service.getActorComments(2, 10, 1);
    }
    
    @Test(expected = MyValidationException.class)
    public void testGetActorCommentsException4() throws Exception {
        when(actorDaoMock.getActor(any(long.class))).thenReturn(null);
        service.getActorComments(1, 10, 1);
    }

    @Test
    public void testGetArticleComments() throws Exception {
        Wrapper tmp = service.getArticleComments(1, 10, 1);
        assertEquals(tmp.getTotal(), 8);
        assertNotNull(tmp.getResults());
    }
    
    @Test(expected = MyValidationException.class)
    public void testGetArticleCommentsExcepiton1() throws Exception {
        service.getArticleComments(0, 10, 1);
    }
    
    @Test(expected = MyValidationException.class)
    public void testGetArticleCommentsExcepiton2() throws Exception {
        service.getArticleComments(1, 11, 1);
    }
    
    @Test(expected = MyValidationException.class)
    public void testGetArticleCommentsExcepiton3() throws Exception {
        service.getArticleComments(2, 10, 1);
    }
    
    @Test(expected = MyValidationException.class)
    public void testGetArticleCommentsExcepiton4() throws Exception {
        when(articleDaoMock.getArticle(any(long.class))).thenReturn(null);
        service.getArticleComments(1, 10, 1);
    }

    @Test
    public void testGetTopicComments() throws Exception {
        Wrapper tmp = service.getTopicComments(1, 10, 1);
        assertEquals(tmp.getTotal(), 8);
        assertNotNull(tmp.getResults());
    }
    
    @Test(expected = MyValidationException.class)
    public void testGetTopicCommentsException1() throws Exception {
        service.getTopicComments(0, 10, 1);
    }
    
    @Test(expected = MyValidationException.class)
    public void testGetTopicCommentsException2() throws Exception {
        service.getTopicComments(1, 11, 1);
    }
    
    @Test(expected = MyValidationException.class)
    public void testGetTopicCommentsException3() throws Exception {
        service.getTopicComments(2, 10, 1);
    }
    
    @Test(expected = MyValidationException.class)
    public void testGetTopicCommentsException4() throws Exception {
        when(TopicDaoMock.getTopic(any(long.class))).thenReturn(null);
        service.getTopicComments(1, 10, 1);
    }

    @Test
    public void testModerateComment() throws Exception {
        when(daoMock.getComment(any(long.class))).thenReturn(getComment());
        Comment tmp = service.moderateComment(1);
        assertEquals(tmp.getContent(), "Moderálva!");
        assertEquals(tmp.isModerated(), true);
    }
    
    @Test(expected = MyValidationException.class)
    public void testModerateCommentException() throws Exception {
        when(daoMock.getComment(any(long.class))).thenReturn(null);
        service.moderateComment(1);
    }

    @Test
    public void testValidateComment() throws Exception {
        Comment tmp = getComment();
        assertEquals(service.validateComment(tmp), true);
        tmp.setContent(" tartalom");
        assertEquals(service.validateComment(tmp), false);
        tmp.setContent("tartalom");
        tmp.setUsername(null);
        assertEquals(service.validateComment(tmp), false);
        tmp.setUsername("valaki");
        tmp.setId(1L);
        assertEquals(service.validateComment(tmp), false);
        tmp.setId(null);
        tmp.setModerated(true);
        assertEquals(service.validateComment(tmp), false);
        tmp.setModerated(false);
        tmp.setPostDate("2010. 02. 17. 15:03:40");
        assertEquals(service.validateComment(tmp), false);
        tmp.setPostDate(null);
        tmp.setActor(new Actor());
        assertEquals(service.validateComment(tmp), false);
        tmp.setActor(null);
        tmp.setArticle(new Article());
        assertEquals(service.validateComment(tmp), false);
        tmp.setArticle(null);
        tmp.setMovie(new Movie());
        assertEquals(service.validateComment(tmp), false);
        tmp.setMovie(null);
        tmp.setTopic(new Topic());
        assertEquals(service.validateComment(tmp), false);
        tmp.setTopic(null);
        tmp.setEpisode(new Episode());
        assertEquals(service.validateComment(tmp), false);
    }
    
}
