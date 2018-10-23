package myapp.SzakdolgozatBE.ageClassification;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RequestScoped
public class AgeClassificationDAO {

    @PersistenceContext(unitName = "SzakdolgozatPU")
    EntityManager em;

    public AgeClassification getAgeClassification(long id) {
        return em.find(AgeClassification.class, id);
    }

    public List<AgeClassification> getAllAgeClassifications() {
        return em.createNamedQuery("getAllAgeClassifications").getResultList();
    }

    public AgeClassification addAgeClassification(AgeClassification ageClassification) {
        em.getTransaction().begin();
        em.persist(ageClassification);
        em.getTransaction().commit();
        return ageClassification;
    }

    public void deleteAgeClassification(long id) {
        em.getTransaction().begin();
        em.remove(this.getAgeClassification(id));
        em.getTransaction().commit();
    }

    public AgeClassification modifyAgeClassification(AgeClassification ageClassification) {
        em.getTransaction().begin();
        em.merge(ageClassification);
        em.getTransaction().commit();
        return ageClassification;
    }
}
