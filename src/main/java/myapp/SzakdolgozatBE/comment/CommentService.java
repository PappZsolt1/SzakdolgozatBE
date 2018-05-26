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

    public Comment addMovieComment(String content, long movieId) {
        Comment tmp = new Comment();
        tmp.setContent(content);
        tmp.setPostDate(sdf.format(new Date()));
        tmp.setMovie(movieDao.getMovie(movieId));
        //tmp.setMyUser(myUser);
        return dao.addComment(tmp);
    }
    
    public Comment addEpisodeComment(String content, long episodeId) {
        Comment tmp = new Comment();
        tmp.setContent(content);
        tmp.setPostDate(sdf.format(new Date()));
        tmp.setEpisode(episodeDao.getEpisode(episodeId));
        //tmp.setMyUser(myUser);
        return dao.addComment(tmp);
    }
    
    public Comment addActorComment(String content, long actorId) {
        Comment tmp = new Comment();
        tmp.setContent(content);
        tmp.setPostDate(sdf.format(new Date()));
        tmp.setActor(actorDao.getActor(actorId));
        //tmp.setMyUser(myUser);
        return dao.addComment(tmp);
    }
    
    public Comment addArticleComment(String content, long articleId) {
        Comment tmp = new Comment();
        tmp.setContent(content);
        tmp.setPostDate(sdf.format(new Date()));
        tmp.setArticle(articleDao.getArticle(articleId));
        //tmp.setMyUser(myUser);
        return dao.addComment(tmp);
    }
    
    public Comment addTopicComment(String content, long topicId) {
        Comment tmp = new Comment();
        tmp.setContent(content);
        tmp.setPostDate(sdf.format(new Date()));
        tmp.setTopic(TopicDao.getTopic(topicId));
        //tmp.setMyUser(myUser);
        return dao.addComment(tmp);
    }

    /*public List<Comment> getxxxComments () {
        //todo
    }*/
}
