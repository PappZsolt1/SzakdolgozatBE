package myapp.SzakdolgozatBE.myUser;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

@RequestScoped
public class MyUserDAO {
    
    EntityManager em = Persistence.createEntityManagerFactory("SzakdolgozatPU").createEntityManager();
    
    public MyUser addMyUser(MyUser myUser) throws NullPointerException {
        em.getTransaction().begin();
        em.persist(myUser);
        em.getTransaction().commit();
        return myUser;
    }
    
    public List<String> getUsernames() {
        return em.createNamedQuery("getUsernames").getResultList();
    }
    
    public boolean isNotTaken(String username) {
        return em.createNamedQuery("getUsername").setParameter("username", username).getResultList().isEmpty();
    }
}
