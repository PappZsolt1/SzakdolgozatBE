package myapp.SzakdolgozatBE.comment;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import myapp.SzakdolgozatBE.actor.Actor;
import myapp.SzakdolgozatBE.article.Article;
import myapp.SzakdolgozatBE.episode.Episode;
import myapp.SzakdolgozatBE.movie.Movie;
import myapp.SzakdolgozatBE.myUser.MyUser;
import myapp.SzakdolgozatBE.topic.Topic;

@Entity
@Table(name = "Comment")
@NamedQueries({
    @NamedQuery(name = "getMovieComments", query = "SELECT c FROM Comment c WHERE c.movie = :movie ORDER BY c.postDate ASC"),
    @NamedQuery(name = "getEpisodeComments", query = "SELECT c FROM Comment c WHERE c.episode = :episode ORDER BY c.postDate ASC"),
    @NamedQuery(name = "getActorComments", query = "SELECT c FROM Comment c WHERE c.actor = :actor ORDER BY c.postDate ASC"),
    @NamedQuery(name = "getArticleComments", query = "SELECT c FROM Comment c WHERE c.article = :article ORDER BY c.postDate ASC"),
    @NamedQuery(name = "getTopicComments", query = "SELECT c FROM Comment c WHERE c.topic = :topic ORDER BY c.postDate ASC"),
    @NamedQuery(name = "getNumberOfMovieComments", query = "SELECT COUNT(c.id) FROM Comment c WHERE c.movie = :movie"),
    @NamedQuery(name = "getNumberOfEpisodeComments", query = "SELECT COUNT(c.id) FROM Comment c WHERE c.episode = :episode"),
    @NamedQuery(name = "getNumberOfActorComments", query = "SELECT COUNT(c.id) FROM Comment c WHERE c.actor = :actor"),
    @NamedQuery(name = "getNumberOfArticleComments", query = "SELECT COUNT(c.id) FROM Comment c WHERE c.article = :article"),
    @NamedQuery(name = "getNumberOfTopicComments", query = "SELECT COUNT(c.id) FROM Comment c WHERE c.topic = :topic")
})
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @SequenceGenerator(name = "gen")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    private Long id;
    
    @Column(columnDefinition = "TEXT")
    private String content;
    
    private String username;
    
    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "myUser_id")
    //@JsonManagedReference(value = "comment-myUser")
    private MyUser myUser;*/
    
    private String postDate;
    
    private boolean moderated;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    //@JsonManagedReference(value = "movie-comment")
    private Movie movie;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "episode_id")
    //@JsonManagedReference(value = "episode-comment")
    private Episode episode;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "actor_id")
    //@JsonManagedReference(value = "actor-comment")
    private Actor actor;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    //@JsonManagedReference(value = "article-comment")
    private Article article;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id")
    //@JsonManagedReference(value = "topic-comment")
    private Topic topic;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /*public MyUser getMyUser() {
        return myUser;
    }

    public void setMyUser(MyUser myUser) {
        this.myUser = myUser;
    }*/

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public boolean isModerated() {
        return moderated;
    }

    public void setModerated(boolean moderated) {
        this.moderated = moderated;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Episode getEpisode() {
        return episode;
    }

    public void setEpisode(Episode episode) {
        this.episode = episode;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
        
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comment)) {
            return false;
        }
        Comment other = (Comment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "myapp.SzakdolgozatBE.comment.Comment[ id=" + id + " ]";
    }
    
}
