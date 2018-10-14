package myapp.SzakdolgozatBE.myUser;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import myapp.SzakdolgozatBE.article.Article;
import myapp.SzakdolgozatBE.comment.Comment;
import myapp.SzakdolgozatBE.conversation.Conversation;
import myapp.SzakdolgozatBE.errorReport.ErrorReport;
import myapp.SzakdolgozatBE.privateMessage.PrivateMessage;
import myapp.SzakdolgozatBE.rating.Rating;
import myapp.SzakdolgozatBE.topic.Topic;

@Entity
@Table(name = "MyUser")
@NamedQueries({
    @NamedQuery(name = "getUsernames", query = "SELECT u.username FROM MyUser u ORDER BY u.username ASC"),
    @NamedQuery(name = "getUsername", query = "SELECT u.username FROM MyUser u WHERE u.username = :username")
})
public class MyUser implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String username;
    
    private String password;
    
    /*@OneToMany(mappedBy = "myUser")
    @JsonBackReference(value = "article-myUser")
    private List<Article> articles;*/
    
    @OneToMany(mappedBy = "myUser")
    @JsonBackReference(value = "comment-myUser")
    private List<Comment> comments;
    
    @OneToMany(mappedBy = "myUser")
    @JsonBackReference(value = "topic-myUser")
    private List<Topic> topics;
    
    @OneToMany(mappedBy = "myUser")
    //@JsonManagedReference(value = "myUser-rating")
    private List<Rating> ratings;
    
    @OneToMany(mappedBy = "myUser")
    @JsonBackReference(value = "errorReport-myUser")
    private List<ErrorReport> errorReports;
    
    @OneToMany(mappedBy = "sender")
    //@JsonManagedReference(value = "myUser-privateMessagesSent")
    private List<PrivateMessage> privateMessagesSent;
    
    @OneToMany(mappedBy = "addressee")
    //@JsonManagedReference(value = "myUser-privateMessagesReceived")
    private List<PrivateMessage> privateMessagesReceived;
    
    @OneToMany(mappedBy = "userA")
    //@JsonManagedReference(value = "myUserA-conversation")
    private List<Conversation> conversationsA;

    @OneToMany(mappedBy = "userB")
    //@JsonManagedReference(value = "myUserB-conversation")
    private List<Conversation> conversationsB;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /*public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }*/

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public List<ErrorReport> getErrorReports() {
        return errorReports;
    }

    public void setErrorReports(List<ErrorReport> errorReports) {
        this.errorReports = errorReports;
    }

    public List<PrivateMessage> getPrivateMessagesSent() {
        return privateMessagesSent;
    }

    public void setPrivateMessagesSent(List<PrivateMessage> privateMessagesSent) {
        this.privateMessagesSent = privateMessagesSent;
    }

    public List<PrivateMessage> getPrivateMessagesReceived() {
        return privateMessagesReceived;
    }

    public void setPrivateMessagesReceived(List<PrivateMessage> privateMessagesReceived) {
        this.privateMessagesReceived = privateMessagesReceived;
    }

    public List<Conversation> getConversationsA() {
        return conversationsA;
    }

    public void setConversationsA(List<Conversation> conversationsA) {
        this.conversationsA = conversationsA;
    }

    public List<Conversation> getConversationsB() {
        return conversationsB;
    }

    public void setConversationsB(List<Conversation> conversationsB) {
        this.conversationsB = conversationsB;
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
        if (!(object instanceof MyUser)) {
            return false;
        }
        MyUser other = (MyUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "myapp.SzakdolgozatBE.rest.user.User[ id=" + id + " ]";
    }
    
}
