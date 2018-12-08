package myapp.SzakdolgozatBE.errorReport;

import java.util.ArrayList;
import myapp.SzakdolgozatBE.MyValidationException;
import myapp.SzakdolgozatBE.Wrapper;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.ArgumentCaptor;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

public class ErrorReportServiceTest {
    
    @Mock
    ErrorReportDAO daoMock;
    
    @InjectMocks
    ErrorReportService service;
    
    @Captor
    private ArgumentCaptor<ErrorReport> arg;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        
        when(daoMock.addErrorReport(any(ErrorReport.class))).thenAnswer(i -> i.getArguments()[0]);
        when(daoMock.getAllErrorReports(any(int.class), any(int.class))).thenReturn(new ArrayList<>());
        when(daoMock.getNumberOfAllErrorReports()).thenReturn(20L);
        when(daoMock.getErrorReport(any(long.class))).thenReturn(new ErrorReport());
    }
    
    @Test
    public void testAddErrorReport() throws Exception {
        ErrorReport tmp = service.addErrorReport("Hiba.");
        assertEquals(tmp.getId(), null);
        assertEquals(tmp.getContent(), "Hiba.");
    }

    @Test
    public void testGetAllErrorReports() throws Exception {
        Wrapper tmp = service.getAllErrorReports(1, 10);
        assertEquals(tmp.getTotal(), 20);
        assertNotNull(tmp.getResults());
    }
    
    @Test(expected = MyValidationException.class)
    public void testGetAllErrorReportsException1() throws Exception {
        service.getAllErrorReports(0, 10);
    }
    
    @Test(expected = MyValidationException.class)
    public void testGetAllErrorReportsException2() throws Exception {
        service.getAllErrorReports(3, 10);
    }

//    @Test
//    public void testGetResolvedErrorReports() throws Exception {
//        
//    }

//    @Test
//    public void testGetNotResolvedErrorReports() throws Exception {
//        
//    }

    @Test
    public void testMakeResolved() throws Exception {
        service.makeResolved(1);
        verify(daoMock).makeResolved(arg.capture());
        assertEquals(arg.getValue().isResolved(), true);
    }
    
    @Test(expected = MyValidationException.class)
    public void testMakeResolvedException() throws Exception {
        when(daoMock.getErrorReport(any(long.class))).thenReturn(null);
        service.makeResolved(1);
    }
}
