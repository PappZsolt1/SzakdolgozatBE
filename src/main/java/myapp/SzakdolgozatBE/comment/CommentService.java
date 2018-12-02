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
                || comment.getUsername() == null
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
                || comment.getUsername() == null
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
                || comment.getUsername() == null
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
                || comment.getUsername() == null
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
                || comment.getUsername() == null
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
            Topic tmp = TopicDao.getTopic(topicId);
            if (tmp == null) {
                throw new MyValidationException();
            }
            comment.setTopic(tmp);
            return dao.addComment(comment);
        }
    }
    
    public Wrapper getMovieComments (int page, int size, long movieId) throws MyValidationException {
        if (page < 1 || val.validateSize(size) == false) {
            throw new MyValidationException();
        }
        Movie tmp = movieDao.getMovie(movieId);
        if (tmp == null) {
            throw new MyValidationException();
        }
        int offset = (page - 1) * size;
        List<Comment> results = dao.getMovieComments(offset, size, tmp);
        long total = dao.getNumberOfMovieComments(tmp);
        if (total > 0 && offset >= total) {
            throw new MyValidationException();
        }
        return new Wrapper(results, total);
    }
    
    public Wrapper getEpisodeComments (int page, int size, long episodeId) throws MyValidationException {
        if (page < 1 || val.validateSize(size) == false) {
            throw new MyValidationException();
        }
        Episode tmp = episodeDao.getEpisode(episodeId);
        if (tmp == null) {
            throw new MyValidationException();
        }
        int offset = (page - 1) * size;
        List<Comment> results = dao.getEpisodeComments(offset, size, tmp);
        long total = dao.getNumberOfEpisodeComments(tmp);
        if (total > 0 && offset >= total) {
            throw new MyValidationException();
        }
        return new Wrapper(results, total);
    }
    
    public Wrapper getActorComments (int page, int size, long actorId) throws MyValidationException {
        if (page < 1 || val.validateSize(size) == false) {
            throw new MyValidationException();
        }
        Actor tmp = actorDao.getActor(actorId);
        if (tmp == null) {
            throw new MyValidationException();
        }
        int offset = (page - 1) * size;
        List<Comment> results = dao.getActorComments(offset, size, tmp);
        long total = dao.getNumberOfActorComments(tmp);
        if (total > 0 && offset >= total) {
            throw new MyValidationException();
        }
        return new Wrapper(results, total);
    }
    
    public Wrapper getArticleComments (int page, int size, long articleId) throws MyValidationException {
        if (page < 1 || val.validateSize(size) == false) {
            throw new MyValidationException();
        }
        Article tmp = articleDao.getArticle(articleId);
        if (tmp == null) {
            throw new MyValidationException();
        }
        int offset = (page - 1) * size;
        List<Comment> results = dao.getArticleComments(offset, size, tmp);
        long total = dao.getNumberOfArticleComments(tmp);
        if (total > 0 && offset >= total) {
            throw new MyValidationException();
        }
        return new Wrapper(results, total);
    }
    
    public Wrapper getTopicComments (int page, int size, long topicId) throws MyValidationException {
        if (page < 1 || val.validateSize(size) == false) {
            throw new MyValidationException();
        }
        Topic tmp = TopicDao.getTopic(topicId);
        if (tmp == null) {
            throw new MyValidationException();
        }
        int offset = (page - 1) * size;
        List<Comment> results = dao.getTopicComments(offset, size, tmp);
        long total = dao.getNumberOfTopicComments(tmp);
        if (total > 0 && offset >= total) {
            throw new MyValidationException();
        }
        return new Wrapper(results, total);
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
