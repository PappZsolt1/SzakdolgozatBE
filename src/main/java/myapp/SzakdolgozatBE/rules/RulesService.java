package myapp.SzakdolgozatBE.rules;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class RulesService {

    @Inject
    RulesDAO dao;
    
    public Rules getRules(long id) throws NullPointerException {
        Rules tmp = dao.getRules(id);
        if (tmp == null) {
            throw new NullPointerException();
        } else {
            return tmp;
        }
    }
    
    public Rules modifyRules(long id, String content) throws NullPointerException {
        Rules tmp = dao.getRules(id);
        if (tmp != null) {
            tmp.setContent(content);
            return dao.modifyRules(tmp);
        } else {
            throw new NullPointerException();
        }
    }
}
