package myapp.SzakdolgozatBE.rest.user;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

@RequestScoped
public class UserDAO {
    
    EntityManager em = Persistence.createEntityManagerFactory("SzakdolgozatPU").createEntityManager();
    
    public User add(User user) throws NullPointerException {
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        return user;
    }
    
    public List<String> getUsernames() {
        return em.createNamedQuery("getUsernames").getResultList();
    }
    
    public boolean isNotTaken(String username) {
        return em.createNamedQuery("getUsername").setParameter("username", username).getResultList().isEmpty();
    }
}
