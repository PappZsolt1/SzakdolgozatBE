package myapp.SzakdolgozatBE.conversation;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.List;
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
import myapp.SzakdolgozatBE.myUser.MyUser;
import myapp.SzakdolgozatBE.privateMessage.PrivateMessage;

@Entity
@Table(name = "Conversation")
@NamedQueries({
    //@NamedQuery(name = "getAllConversations", query = "")
})
public class Conversation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "gen")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value = "myUserA-conversation")
    @JoinColumn(name = "userA_id")
    private MyUser userA;
    
    @ManyToOne(fetch = FetchType.LAZY)
    //@JsonBackReference(value = "myUserB-conversation")
    @JoinColumn(name = "userB_id")
    private MyUser userB;
    
    @OneToMany(mappedBy = "conversation")
    //@JsonManagedReference(value = "conversation-privateMessage")
    private List<PrivateMessage> privateMessages;
    
    private int unreadMessages;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MyUser getUserA() {
        return userA;
    }

    public void setUserA(MyUser userA) {
        this.userA = userA;
    }

    public MyUser getUserB() {
        return userB;
    }

    public void setUserB(MyUser userB) {
        this.userB = userB;
    }

    public List<PrivateMessage> getPrivateMessages() {
        return privateMessages;
    }

    public void setPrivateMessages(List<PrivateMessage> privateMessages) {
        this.privateMessages = privateMessages;
    }

    public int getUnreadMessages() {
        return unreadMessages;
    }

    public void setUnreadMessages(int unreadMessages) {
        this.unreadMessages = unreadMessages;
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
        if (!(object instanceof Conversation)) {
            return false;
        }
        Conversation other = (Conversation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "myapp.SzakdolgozatBE.conversation.Conversation[ id=" + id + " ]";
    }
    
}
