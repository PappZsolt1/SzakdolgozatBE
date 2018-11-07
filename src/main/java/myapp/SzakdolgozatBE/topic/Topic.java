package myapp.SzakdolgozatBE.topic;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import myapp.SzakdolgozatBE.comment.Comment;
import myapp.SzakdolgozatBE.myUser.MyUser;


@Entity
@Table(name = "Topic")
@NamedQueries({
    @NamedQuery(name = "getAllTopics", query = "SELECT t FROM Topic t")//todo
})
public class Topic implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @SequenceGenerator(name = "gen", initialValue = 1000, allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    private Long id;
    
    private String title;
    
    @Column(columnDefinition = "TEXT")
    private String description;  
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "myUser_id")
    //@JsonManagedReference(value = "topic-myUser")
    private MyUser myUser;    
    
    private String createDate;
    
    @OneToMany(mappedBy = "topic")
    @JsonBackReference(value = "topic-comment")
    private List<Comment> comments;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MyUser getMyUser() {
        return myUser;
    }

    public void setMyUser(MyUser myUser) {
        this.myUser = myUser;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
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
        if (!(object instanceof Topic)) {
            return false;
        }
        Topic other = (Topic) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "myapp.SzakdolgozatBE.topic.Topic[ id=" + id + " ]";
    }
    
}
