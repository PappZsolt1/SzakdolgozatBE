package myapp.SzakdolgozatBE.errorReport;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import myapp.SzakdolgozatBE.myUser.MyUser;

@Stateless
public class ErrorReportService {
    
    @Inject ErrorReportDAO dao;
 
    public ErrorReport add(MyUser myUser, String content) {
        ErrorReport tmp = new ErrorReport();
        tmp.setMyUser(myUser);
        tmp.setContent(content);
        tmp.setSendingDate(new Date());
        return dao.add(tmp);
    }
    
    public List<ErrorReport> getAll() {
        return dao.getAll();
    }
    
    public void makeResolved(long id) {
        dao.makeResolved(id);
    }
}
