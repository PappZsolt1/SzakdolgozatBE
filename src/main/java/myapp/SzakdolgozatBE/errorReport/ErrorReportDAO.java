package myapp.SzakdolgozatBE.errorReport;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RequestScoped
public class ErrorReportDAO {
    
    @PersistenceContext(unitName = "SzakdolgozatPU")
    EntityManager em;// = Persistence.createEntityManagerFactory("SzakdolgozatPU").createEntityManager();
    
    public ErrorReport add(ErrorReport errorReport) {
        em.getTransaction().begin();
        em.persist(errorReport);
        em.getTransaction().commit();
        return errorReport;
    }
    
    public List<ErrorReport> getAllErrorReports() {
        return em.createNamedQuery("getAllErrorReports").getResultList();
    }
    
    public List<ErrorReport> getResolvedErrorReports() {
        return em.createNamedQuery("getResolvedErrorReports").getResultList();
    }
    
    public List<ErrorReport> getNotResolvedErrorReports() {
        return em.createNamedQuery("getNotResolvedErrorReports").getResultList();
    }
    
    public ErrorReport getErrorReport(long id) {
        ErrorReport tmp = em.find(ErrorReport.class, id);
        return tmp;        
    }
    
    public void makeResolved(long id) {
        ErrorReport tmp = this.getErrorReport(id);
        tmp.setResolved(true);
        em.getTransaction().begin();
        em.merge(tmp);
        em.getTransaction().commit();        
    }
}
