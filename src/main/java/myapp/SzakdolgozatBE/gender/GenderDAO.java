package myapp.SzakdolgozatBE.gender;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RequestScoped
public class GenderDAO {

    @PersistenceContext(unitName = "SzakdolgozatPU")
    EntityManager em;

    public Gender getGender(long id) throws NullPointerException {
        return em.find(Gender.class, id);
    }

    public List<Gender> getAllGenders() {
        return em.createNamedQuery("getAllGenders").getResultList();
    }

    public Gender addGender(Gender gender) {
        em.getTransaction().begin();
        em.persist(gender);
        em.getTransaction().commit();
        return gender;
    }

    public void deleteGender(long id) {
        em.getTransaction().begin();
        em.remove(this.getGender(id));
        em.getTransaction().commit();
    }

    public Gender modifyGender(Gender gender) {
        em.getTransaction().begin();
        em.merge(gender);
        em.getTransaction().commit();
        return gender;
    }
}
