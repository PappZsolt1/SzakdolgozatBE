package myapp.SzakdolgozatBE.errorReport;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

@RequestScoped
public class ErrorReportDAO {
    
    EntityManager em = Persistence.createEntityManagerFactory("SzakdolgozatPU").createEntityManager();
    
    public ErrorReport add(ErrorReport errorReport) {
        em.getTransaction().begin();
        em.persist(errorReport);
        em.getTransaction().commit();
        return errorReport;
    }
    
    public List<ErrorReport> getAll() {
        return em.createNamedQuery("getAllErrorReport").getResultList();
    }
    
    public ErrorReport getErrorReport(long id) {
        ErrorReport tmp = em.find(ErrorReport.class, id);
        return tmp;        
    }
    
    public void makeResolved(long id) {
        ErrorReport tmp = this.getErrorReport(id);
        tmp.setIsResolved(true);
        em.getTransaction().begin();
        em.merge(tmp);
        em.getTransaction().commit();        
    }
}
