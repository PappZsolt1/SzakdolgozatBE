package myapp.SzakdolgozatBE.errorReport;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import myapp.SzakdolgozatBE.MyValidationException;
import myapp.SzakdolgozatBE.MyValidator;
import myapp.SzakdolgozatBE.Wrapper;

@Stateless
public class ErrorReportService {
    
    @Inject
    ErrorReportDAO dao;
    
    MyValidator val = new MyValidator();
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy. MM. dd. HH:mm:ss");
    
    public ErrorReport addErrorReport(String content) throws MyValidationException {
        if (val.validateText(content, 60000) == false) {
            throw new MyValidationException();
        } else {
            ErrorReport tmp = new ErrorReport();
            tmp.setContent(content);
            tmp.setSendingDate(sdf.format(new Date()));
            tmp.setResolved(false);
            return dao.addErrorReport(tmp);
        }
    }
    
    public Wrapper getAllErrorReports(int page, int size) throws MyValidationException {
        if (page < 1 || val.validateSize(size) == false) {
            throw new MyValidationException();
        }
        int offset = (page - 1) * size;
        List<ErrorReport> results = dao.getAllErrorReports(offset, size);
        long total = dao.getNumberOfAllErrorReports();
        if (total > 0 && offset >= total) {
            throw new MyValidationException();
        }
        return new Wrapper(results, total);
    }
    
    public Wrapper getResolvedErrorReports(int page, int size) throws MyValidationException {
        if (page < 1 || val.validateSize(size) == false) {
            throw new MyValidationException();
        }
        int offset = (page - 1) * size;
        List<ErrorReport> results = dao.getResolvedErrorReports(offset, size);
        long total = dao.getNumberOfResolvedErrorReports();
        if (total > 0 && offset >= total) {
            throw new MyValidationException();
        }
        return new Wrapper(results, total);
    }
    
    public Wrapper getNotResolvedErrorReports(int page, int size) throws MyValidationException {
        if (page < 1 || val.validateSize(size) == false) {
            throw new MyValidationException();
        }
        int offset = (page - 1) * size;
        List<ErrorReport> results = dao.getNotResolvedErrorReports(offset, size);
        long total = dao.getNumberOfNotResolvedErrorReports();
        if (total > 0 && offset >= total) {
            throw new MyValidationException();
        }
        return new Wrapper(results, total);
    }
    
    public void makeResolved(long id) throws MyValidationException {
        ErrorReport tmp = dao.getErrorReport(id);
        if (tmp != null) {
            tmp.setResolved(true);
            dao.makeResolved(tmp);
        } else {
            throw new MyValidationException();
        }
    }
}
