package myapp.SzakdolgozatBE.rules;

import javax.ejb.Stateless;
import javax.inject.Inject;
import myapp.SzakdolgozatBE.MyValidationException;
import myapp.SzakdolgozatBE.MyValidator;

@Stateless
public class RulesService {

    @Inject
    RulesDAO dao;
    
    MyValidator val = new MyValidator();
    
    public Rules getRules() {
        return dao.getRules();
    }
    
    public Rules modifyRules(String content) throws MyValidationException {
        if (val.validateText(content, 60000) == false) {
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
