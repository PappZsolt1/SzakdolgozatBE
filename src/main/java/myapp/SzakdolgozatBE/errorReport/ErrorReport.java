package myapp.SzakdolgozatBE.errorReport;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ErrorReport")
@NamedQueries({
    @NamedQuery(name = "getAllErrorReports", query = "SELECT e FROM ErrorReport e ORDER BY e.sendingDate ASC"),
    @NamedQuery(name = "getResolvedErrorReports", query = "SELECT e FROM ErrorReport e WHERE e.resolved = TRUE ORDER BY e.sendingDate ASC"),
    @NamedQuery(name = "getNotResolvedErrorReports", query = "SELECT e FROM ErrorReport e WHERE e.resolved = FALSE ORDER BY e.sendingDate ASC"),
    @NamedQuery(name = "getNumberOfAllErrorReports", query = "SELECT COUNT(e.id) FROM ErrorReport e"),
    @NamedQuery(name = "getNumberOfResolvedErrorReports", query = "SELECT COUNT(e.id) FROM ErrorReport e WHERE e.resolved = TRUE"),
    @NamedQuery(name = "getNumberOfNotResolvedErrorReports", query = "SELECT COUNT(e.id) FROM ErrorReport e WHERE e.resolved = FALSE")
})
public class ErrorReport implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @SequenceGenerator(name = "gen")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    private Long id;
    
    @Column(columnDefinition = "TEXT")
    private String content;
        
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