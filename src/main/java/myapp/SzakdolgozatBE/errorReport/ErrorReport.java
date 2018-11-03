package myapp.SzakdolgozatBE.errorReport;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "ErrorReport")
@NamedQueries({
    @NamedQuery(name = "getAllErrorReports", query = "SELECT e FROM ErrorReport e ORDER BY e.sendingDate ASC"),
    @NamedQuery(name = "getResolvedErrorReports", query = "SELECT e FROM ErrorReport e WHERE e.resolved = TRUE ORDER BY e.sendingDate ASC"),
    @NamedQuery(name = "getNotResolvedErrorReports", query = "SELECT e FROM ErrorReport e WHERE e.resolved = FALSE ORDER BY e.sendingDate ASC")
})
public class ErrorReport implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String content;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MYUSER_ID")
    //@JsonManagedReference(value = "errorReport-myUser")
    private MyUser myUser;
    
    private boolean resolved;
    
    private String sendingDate;

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

    public MyUser getMyUser() {
        return myUser;
    }

    public void setMyUser(MyUser myUser) {
        this.myUser = myUser;
    }

    public boolean isResolved() {
        return resolved;
    }

    public void setResolved(boolean resolved) {
        this.resolved = resolved;
    }

    public String getSendingDate() {
        return sendingDate;
    }

    public void setSendingDate(String sendingDate) {
        this.sendingDate = sendingDate;
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
        if (!(object instanceof ErrorReport)) {
            return false;
        }
        ErrorReport other = (ErrorReport) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "myapp.SzakdolgozatBE.errorReport.ErrorReport[ id=" + id + " ]";
    }
    
}