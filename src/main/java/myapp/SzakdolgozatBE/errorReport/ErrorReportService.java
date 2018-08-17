package myapp.SzakdolgozatBE.errorReport;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ErrorReportService {
    
    @Inject
    ErrorReportDAO dao;
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy. MM. dd. HH:mm:ss");
    
    public ErrorReport add(String content) {
        ErrorReport tmp = new ErrorReport();
        tmp.setContent(content);
        tmp.setSendingDate(sdf.format(new Date()));
        tmp.setResolved(false);
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
    
    public void makeResolved(long id) throws NullPointerException {
        ErrorReport tmp = dao.getErrorReport(id);
        if (tmp != null) {
            tmp.setResolved(true);
            dao.makeResolved(tmp);
        } else {
            throw new NullPointerException();
        }
    }
}
