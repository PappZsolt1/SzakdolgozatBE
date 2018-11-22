package myapp.SzakdolgozatBE.comment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import myapp.SzakdolgozatBE.MyValidationException;
import myapp.SzakdolgozatBE.MyValidator;
import myapp.SzakdolgozatBE.Wrapper;
import myapp.SzakdolgozatBE.actor.Actor;
import myapp.SzakdolgozatBE.actor.ActorDAO;
import myapp.SzakdolgozatBE.article.Article;
import myapp.SzakdolgozatBE.article.ArticleDAO;
import myapp.SzakdolgozatBE.episode.Episode;
import myapp.SzakdolgozatBE.episode.EpisodeDAO;
import myapp.SzakdolgozatBE.movie.Movie;
import myapp.SzakdolgozatBE.movie.MovieDAO;
import myapp.SzakdolgozatBE.myUser.MyUser;
import myapp.SzakdolgozatBE.topic.Topic;
import myapp.SzakdolgozatBE.topic.TopicDAO;

@Stateless
public class CommentService {

    @Inject
    CommentDAO dao;
    
    @Inject
    MovieDAO movieDao;
    
    @Inject
    EpisodeDAO episodeDao;
    
    @Inject
    ActorDAO actorDao;
    
    @Inject
    ArticleDAO articleDao;
    
    @Inject
    TopicDAO TopicDao;
    
    MyValidator val = new MyValidator();

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy. MM. dd. HH:mm:ss");

    public Comment addMovieComment(long movieId, Comment comment) throws MyValidationException {
        if (comment.getId() != null
                || comment.getPostDate() != null
                || comment.getActor() != null
                || comment.getArticle()!= null 
                || comment.getMovie()!= null
                || comment.getEpisode()!= null
                || comment.getTopic()!= null
                || comment.isModerated() == true
                || val.validateText(comment.getContent(), 60000) == false) {
            throw new MyValidationException();
        } else {
            comment.setPostDate(sdf.format(new Date()));
            //tmp.setMyUser(myUser);
            Movie tmp = movieDao.getMovie(movieId);
            if (tmp == null) {
                throw new MyValidationException();
            }
            comment.setMovie(tmp);
            return dao.addComment(comment);
        }
    }
    
    public Comment addActorComment(long actorId, Comment comment) throws MyValidationException {
        if (comment.getId() != null
                || comment.getPostDate() != null
                || comment.getActor() != null
                || comment.getArticle()!= null 
                || comment.getMovie()!= null
                || comment.getEpisode()!= null
                || comment.getTopic()!= null
                || comment.isModerated() == true
                || val.validateText(comment.getContent(), 60000) == false) {
            throw new MyValidationException();
        } else {
            comment.setPostDate(sdf.format(new Date()));
            //tmp.setMyUser(myUser);
            Actor tmp = actorDao.getActor(actorId);
            if (tmp == null) {
                throw new MyValidationException();
            }
            comment.setActor(tmp);
            return dao.addComment(comment);
        }
    }
    
    public Comment addArticleComment(long articleId, Comment comment) throws MyValidationException {
        if (comment.getId() != null
                || comment.getPostDate() != null
                || comment.getActor() != null
                || comment.getArticle()!= null 
                || comment.getMovie()!= null
                || comment.getEpisode()!= null
                || comment.getTopic()!= null
                || comment.isModerated() == true
                || val.validateText(comment.getContent(), 60000) == false) {
            throw new MyValidationException();
        } else {
            comment.setPostDate(sdf.format(new Date()));
            //tmp.setMyUser(myUser);
            Article tmp = articleDao.getArticle(articleId);
            if (tmp == null) {
                throw new MyValidationException();
            }
            comment.setArticle(tmp);
            return dao.addComment(comment);
        }
    }
    
    public Comment addEpisodeComment(long episodeId, Comment comment) throws MyValidationException {
        if (comment.getId() != null
                || comment.getPostDate() != null
                || comment.getActor() != null
                || comment.getArticle()!= null 
                || comment.getMovie()!= null
                || comment.getEpisode()!= null
                || comment.getTopic()!= null
                || comment.isModerated() == true
                || val.validateText(comment.getContent(), 60000) == false) {
            throw new MyValidationException();
        } else {
            comment.setPostDate(sdf.format(new Date()));
            //tmp.setMyUser(myUser);
            Episode tmp = episodeDao.getEpisode(episodeId);
            if (tmp == null) {
                throw new MyValidationException();
            }
            comment.setEpisode(tmp);
            return dao.addComment(comment);
        }
    }
    
    public Comment addTopicComment(long topicId, Comment comment) throws MyValidationException {
        if (comment.getId() != null
                || comment.getPostDate() != null
                || comment.getActor() != null
                || comment.getArticle()!= null 
                || comment.getMovie()!= null
                || comment.getEpisode()!= null
                || comment.getTopic()!= null
                || comment.isModerated() == true
                || val.validateText(comment.getContent(), 60000) == false) {
            throw new MyValidationException();
        } else {
            comment.setPostDate(sdf.format(new Date()));
            //tmp.setMyUser(myUser);
            Topic tmp = TopicDao.getTopic(topicId);
            if (tmp == null) {
                throw new MyValidationException();
            }
            comment.setTopic(tmp);
            return dao.addComment(comment);
        }
    }
    
    public Wrapper getMovieComments (int page, int size, long movieId) throws MyValidationException {
        if (page < 1 || size < 1) {
            throw new MyValidationException();
        }
        Movie tmp = movieDao.getMovie(movieId);
        if (tmp == null) {
            throw new MyValidationException();
        }
        int offset = (page - 1) * size;
        List<Comment> results = dao.getMovieComments(offset, size, tmp);
        long total = dao.getNumberOfMovieComments(tmp);
        if ((offset) >= total) {
            throw new MyValidationException();
        }
        return new Wrapper(results, total);
    }
    
    public List<Comment> getEpisodeComments (long episodeId) {
        return dao.getEpisodeComments(episodeDao.getEpisode(episodeId));
    }
    
    public List<Comment> getActorComments (long actorId) {
        return dao.getActorComments(actorDao.getActor(actorId));
    }
    
    public List<Comment> getArticleComments (long articleId) {
        return dao.getArticleComments(articleDao.getArticle(articleId));
    }
    
    public List<Comment> getTopicComments (long topicId) {
        return dao.getTopicComments(TopicDao.getTopic(topicId));
    }
    
    public Comment moderateComment(long id) throws MyValidationException {
        Comment tmp = dao.getComment(id);
        if (tmp == null) {
            throw new MyValidationException();
        } else {
            tmp.setContent("Moderálva!");
            tmp.setModerated(true);
            return dao.moderateComment(tmp);
        }        
    }
}
