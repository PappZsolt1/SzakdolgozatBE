package myapp.SzakdolgozatBE.privateMessage;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import myapp.SzakdolgozatBE.myUser.MyUser;

@Entity
@Table(name = "PrivateMessage")
@NamedQueries({
    @NamedQuery(name = "getSentPrivateMessages", query = "SELECT p FROM PrivateMessage p WHERE p.sender = :sender"),
    @NamedQuery(name = "getReceivedPrivateMessages", query = "SELECT p FROM PrivateMessage p WHERE p.addressee = :addressee")
})
public class PrivateMessage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
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
