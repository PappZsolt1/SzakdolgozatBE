package myapp.SzakdolgozatBE.rules;

import javax.ejb.Stateless;
import javax.inject.Inject;
import myapp.SzakdolgozatBE.MyValidationException;

@Stateless
public class RulesService {

    @Inject
    RulesDAO dao;
    
    public Rules getRules() {
        return dao.getRules();
    }
    
    public Rules modifyRules(String content) throws MyValidationException {
        if (content.matches("^\\S(.|\\s)*\\S$|^\\S$") == false ||
                content.length() > 60000) {
            throw new MyValidationException();
        }
        Rules tmp = dao.getRules();
        if (tmp != null) {
            tmp.setContent(content);
            return dao.modifyRules(tmp);
        } else {
            throw new MyValidationException();
        }
    }
}
