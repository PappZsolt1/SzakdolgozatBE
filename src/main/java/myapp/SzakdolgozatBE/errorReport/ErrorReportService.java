package myapp.SzakdolgozatBE.errorReport;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import myapp.SzakdolgozatBE.myUser.MyUser;

@Stateless
public class ErrorReportService {
    
    @Inject ErrorReportDAO dao;
 
    public ErrorReport add(String content) {
        ErrorReport tmp = new ErrorReport();
        tmp.setContent(content);
        return dao.add(tmp);
    }
    
    public List<ErrorReport> getAllErrorReports() {
        return dao.getAllErrorReports();
    }
    
    public List<ErrorReport> getResolvedErrorReports() {
        return dao.getResolvedErrorReports();
    }
    
    public List<ErrorReport> getNotResolvedErrorReports() {
        return dao.getNotResolvedErrorReports();
    }
    
    public void makeResolved(long id) {
        dao.makeResolved(id);
    }
}
