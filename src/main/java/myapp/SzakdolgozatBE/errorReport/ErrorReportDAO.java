package myapp.SzakdolgozatBE.errorReport;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RequestScoped
public class ErrorReportDAO {

    @PersistenceContext(unitName = "SzakdolgozatPU")
    EntityManager em;

    public ErrorReport addErrorReport(ErrorReport errorReport) {
        em.getTransaction().begin();
        em.persist(errorReport);
        em.getTransaction().commit();
        return errorReport;
    }

    public List<ErrorReport> getAllErrorReports(int offset, int limit) {
        return em.createNamedQuery("getAllErrorReports")
                .setFirstResult(offset).setMaxResults(limit).getResultList();
    }
    
    public long getNumberOfAllErrorReports() {
        return (long)em.createNamedQuery("getNumberOfAllErrorReports").getSingleResult();
    }

    public List<ErrorReport> getResolvedErrorReports(int offset, int limit) {
        return em.createNamedQuery("getResolvedErrorReports")
                .setFirstResult(offset).setMaxResults(limit).getResultList();
    }
    
    public long getNumberOfResolvedErrorReports() {
        return (long)em.createNamedQuery("getNumberOfResolvedErrorReports").getSingleResult();
    }

    public List<ErrorReport> getNotResolvedErrorReports(int offset, int limit) {
        return em.createNamedQuery("getNotResolvedErrorReports")
                .setFirstResult(offset).setMaxResults(limit).getResultList();
    }
    
    public long getNumberOfNotResolvedErrorReports() {
        return (long)em.createNamedQuery("getNumberOfNotResolvedErrorReports").getSingleResult();
    }

    public ErrorReport getErrorReport(long id) {
        return em.find(ErrorReport.class, id);
    }

    public void makeResolved(ErrorReport errorReport) {
        em.getTransaction().begin();
        em.merge(errorReport);
        em.getTransaction().commit();
    }
}
