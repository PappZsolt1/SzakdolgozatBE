package myapp.SzakdolgozatBE.comment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import myapp.SzakdolgozatBE.MyValidationException;
import myapp.SzakdolgozatBE.MyValidator;
import myapp.SzakdolgozatBE.actor.ActorDAO;
import myapp.SzakdolgozatBE.article.ArticleDAO;
import myapp.SzakdolgozatBE.episode.EpisodeDAO;
import myapp.SzakdolgozatBE.movie.MovieDAO;
import myapp.SzakdolgozatBE.myUser.MyUser;
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

    public Comment addComment(Comment comment) throws MyValidationException {
        if (comment.getId() != null
                || comment.getPostDate() != null
                || val.validateText(comment.getContent(), 60000) == false
                || validateCommentObjects(new Object[] {comment.getActor(), comment.getArticle(),
                comment.getEpisode(), comment.getMovie(), comment.getTopic()}) == false) {
            throw new MyValidationException();
        } else {
            comment.setPostDate(sdf.format(new Date()));
            //tmp.setMyUser(myUser);
            return dao.addComment(comment);
        }
    }
    
    public List<Comment> getMovieComments (long movieId) {
        return dao.getMovieComments(movieDao.getMovie(movieId));
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
            return dao.moderateComment(tmp);
        }        
    }
    
    public boolean validateCommentObjects(Object[] o) {
        int count = 0;
        for (int i = 0; i < o.length; i++) {
            if (o[i] != null) {
                count++;
            }
        }
        return (count == 1);
    }
}
