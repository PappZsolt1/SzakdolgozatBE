package myapp.SzakdolgozatBE.privateMessage;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
import myapp.SzakdolgozatBE.conversation.Conversation;
import myapp.SzakdolgozatBE.myUser.MyUser;

@Entity
@Table(name = "PrivateMessage")
@NamedQueries({
    @NamedQuery(name = "getConversationPrivateMessages", query = "SELECT p FROM PrivateMessage p WHERE p.conversation = :conversation")
})
public class PrivateMessage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "gen")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    private Long id;
    
    @Column(columnDefinition = "TEXT")
    private String content;
    
    private boolean unread;
    
    private String sendingDate;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value = "myUser-privateMessagesSent")
    @JoinColumn(name = "sender_id")
    private MyUser sender;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value = "myUser-privateMessagesReceived")
    @JoinColumn(name = "addressee_id")
    private MyUser addressee;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value = "conversation-privateMessage")
    @JoinColumn(name = "conversation_id")
    private Conversation conversation;

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

    public boolean isUnread() {
        return unread;
    }

    public void setUnread(boolean unread) {
        this.unread = unread;
    }

    public String getSendingDate() {
        return sendingDate;
    }

    public void setSendingDate(String sendingDate) {
        this.sendingDate = sendingDate;
    }

    public MyUser getSender() {
        return sender;
    }

    public void setSender(MyUser sender) {
        this.sender = sender;
    }

    public MyUser getAddressee() {
        return addressee;
    }

    public void setAddressee(MyUser addressee) {
        this.addressee = addressee;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
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
        if (!(object instanceof PrivateMessage)) {
            return false;
        }
        PrivateMessage other = (PrivateMessage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "myapp.SzakdolgozatBE.privateMessage.PrivateMessage[ id=" + id + " ]";
    }
    
}
