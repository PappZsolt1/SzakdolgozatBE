package myapp.SzakdolgozatBE.rules;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class RulesService {

    @Inject
    RulesDAO dao;
    
    public Rules getRules() {
        return dao.getRules();
    }
    
    public Rules modifyRules(String content) throws NullPointerException {
        Rules tmp = dao.getRules();
        if (tmp != null) {
            tmp.setContent(content);
            return dao.modifyRules(tmp);
        } else {
            throw new NullPointerException();
        }
    }
}
