package myapp.SzakdolgozatBE.rules;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RequestScoped
public class RulesDAO {
    
    @PersistenceContext(unitName = "SzakdolgozatPU")
    EntityManager em;
    
    public Rules getRules() {
        return (Rules)em.createNamedQuery("getRules").getSingleResult();
    }
    
    public Rules modifyRules(Rules rules) {
        em.getTransaction().begin();
        em.merge(rules);
        em.getTransaction().commit();
        return rules;
    }
}
