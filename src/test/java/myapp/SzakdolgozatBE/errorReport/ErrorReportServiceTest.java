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
        
        when(daoMock.getResolvedErrorReports(any(int.class), any(int.class))).thenReturn(new ArrayList<>());
        when(daoMock.getNumberOfResolvedErrorReports()).thenReturn(35L);
        
        when(daoMock.getNotResolvedErrorReports(any(int.class), any(int.class))).thenReturn(new ArrayList<>());
        when(daoMock.getNumberOfNotResolvedErrorReports()).thenReturn(53L);
    }
    
    @Test
    public void testAddErrorReport() throws Exception {
        ErrorReport tmp = service.addErrorReport("Hiba.");
        assertEquals(tmp.getId(), null);
        assertEquals(tmp.getContent(), "Hiba.");
    }
    
    @Test(expected = MyValidationException.class)
    public void testAddErrorReportException() throws Exception {
        service.addErrorReport(" Hiba.");
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
    
    @Test(expected = MyValidationException.class)
    public void testGetAllErrorReportsException3() throws Exception {
        service.getAllErrorReports(1, 11);
    }

    @Test
    public void testGetResolvedErrorReports() throws Exception {
        Wrapper tmp = service.getResolvedErrorReports(2, 20);
        assertEquals(tmp.getTotal(), 35);
        assertNotNull(tmp.getResults());
    }
    
    @Test(expected = MyValidationException.class)
    public void testGetResolvedErrorReportsException1() throws Exception {
        service.getResolvedErrorReports(0, 20);
    }
    
    @Test(expected = MyValidationException.class)
    public void testGetResolvedErrorReportsException2() throws Exception {
        service.getResolvedErrorReports(1, 21);
    }
    
    @Test(expected = MyValidationException.class)
    public void testGetResolvedErrorReportsException3() throws Exception {
        service.getResolvedErrorReports(3, 20);
    }

    @Test
    public void testGetNotResolvedErrorReports() throws Exception {
        Wrapper tmp = service.getNotResolvedErrorReports(2, 30);
        assertEquals(tmp.getTotal(), 53);
        assertNotNull(tmp.getResults());
    }
    
    @Test(expected = MyValidationException.class)
    public void testGetNotResolvedErrorReportsException1() throws Exception {
        service.getNotResolvedErrorReports(0, 30);
    }
    
    @Test(expected = MyValidationException.class)
    public void testGetNotResolvedErrorReportsException2() throws Exception {
        service.getNotResolvedErrorReports(1, 40);
    }
    
    @Test(expected = MyValidationException.class)
    public void testGetNotResolvedErrorReportsException3() throws Exception {
        service.getNotResolvedErrorReports(3, 30);
    }

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
