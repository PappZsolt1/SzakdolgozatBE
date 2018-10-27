package myapp.SzakdolgozatBE.comment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
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

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy. MM. dd. HH:mm:ss");

    public Comment addComment(Comment comment) {
        comment.setPostDate(sdf.format(new Date()));
        //tmp.setMyUser(myUser);
        return dao.addComment(comment);
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
    
    public Comment moderateComment(long commentId) throws NullPointerException {
        Comment tmp = dao.getComment(commentId);
        if (tmp != null) {
            tmp.setContent("Moderálva!");
            return dao.moderateComment(tmp);
        } else {
            throw new NullPointerException();
        }        
    }
}
