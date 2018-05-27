package myapp.SzakdolgozatBE.myUser;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RequestScoped
public class MyUserDAO {
    
    @PersistenceContext(unitName = "SzakdolgozatPU")
    EntityManager em;
    
    public MyUser addMyUser(MyUser myUser) throws NullPointerException {
        em.getTransaction().begin();
        em.persist(myUser);
        em.getTransaction().commit();
        return myUser;
    }
    
    public MyUser getMyUser(long id) {
        return em.find(MyUser.class, id);
    }
    
    public List<String> getUsernames() {
        return em.createNamedQuery("getUsernames").getResultList();
    }
    
    public boolean isNotTaken(String username) {
        return em.createNamedQuery("getUsername").setParameter("username", username).getResultList().isEmpty();
    }
}
