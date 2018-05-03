package myapp.SzakdolgozatBE.errorReport;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import myapp.SzakdolgozatBE.user.User;

@Stateless
public class ErrorReportService {
    
    @Inject ErrorReportDAO dao;
 
    public ErrorReport add(User user, String content) {
        ErrorReport tmp = new ErrorReport();
        tmp.setUser(user);
        tmp.setContent(content);
        return dao.add(tmp);
    }
    
    public List<ErrorReport> getAll() {
        return dao.getAll();
    }
}
