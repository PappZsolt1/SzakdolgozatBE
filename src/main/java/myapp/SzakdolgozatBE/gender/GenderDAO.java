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
        Gender tmp = em.find(Gender.class, id);
        if(tmp != null)    
                return tmp;
        else
            throw new NullPointerException();
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
    
    public Gender modifyGender(long id, Gender gender) throws NullPointerException {
        Gender tmp = this.getGender(id);
        if (tmp != null) {
            tmp.setName(gender.getName());
            em.getTransaction().begin();
            em.merge(tmp);
            em.getTransaction().commit();
            return tmp;
        } else {
            throw new NullPointerException();
        }
    }
}
