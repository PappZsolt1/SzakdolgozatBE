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
        return em.createNamedQuery("getAll").getResultList();
    }
}
