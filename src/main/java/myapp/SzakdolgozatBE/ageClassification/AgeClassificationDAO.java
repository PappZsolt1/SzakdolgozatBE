package myapp.SzakdolgozatBE.ageClassification;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RequestScoped
public class AgeClassificationDAO {

    @PersistenceContext(unitName = "SzakdolgozatPU")
    EntityManager em;

    public AgeClassification getAgeClassification(long id) throws NullPointerException {
        AgeClassification tmp = em.find(AgeClassification.class, id);
        if (tmp != null) {
            return tmp;
        } else {
            throw new NullPointerException();
        }
    }

    public List<AgeClassification> getAllAgeClassifications() {
        return em.createNamedQuery("getAllAgeClassification").getResultList();
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

    public AgeClassification modifyAgeClassification(long id, AgeClassification ageClassification) throws NullPointerException {
        AgeClassification tmp = this.getAgeClassification(id);
        if (tmp != null) {
            tmp.setName(ageClassification.getName());
            em.getTransaction().begin();
            em.merge(tmp);
            em.getTransaction().commit();
            return tmp;
        } else {
            throw new NullPointerException();
        }
    }
}
