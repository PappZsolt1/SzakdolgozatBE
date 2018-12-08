package myapp.SzakdolgozatBE.ageClassification;

import java.util.ArrayList;
import myapp.SzakdolgozatBE.MyValidationException;
import myapp.SzakdolgozatBE.movie.MovieDAO;
import myapp.SzakdolgozatBE.series.SeriesDAO;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

public class AgeClassificationServiceTest {
    
    @Mock
    AgeClassificationDAO daoMock;
    
    @Mock
    MovieDAO movieDaoMock;
    
    @Mock
    SeriesDAO seriesDaoMock;

    @InjectMocks
    AgeClassificationService service;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        
        AgeClassification tmp = new AgeClassification();
        tmp.setId(1L);
        tmp.setName("12+");
        when(daoMock.getAgeClassification(any(long.class))).thenReturn(tmp);
        when(movieDaoMock.ageClassificationNotUsed(any(AgeClassification.class))).thenReturn(true);
        when(seriesDaoMock.ageClassificationNotUsed(any(AgeClassification.class))).thenReturn(true);
    }
    
    @Test
    public void testGetAgeClassification() throws Exception {
        AgeClassification tmp = service.getAgeClassification(1);
        assertEquals((long)tmp.getId(), 1L);
        assertEquals(tmp.getName(), "12+");
    }
    
    @Test(expected = MyValidationException.class)
    public void testGetAgeClassificationException() throws Exception {
        when(daoMock.getAgeClassification(any(long.class))).thenReturn(null);
        service.getAgeClassification(1);
    }

    @Test
    public void testGetAllAgeClassifications() throws Exception {
        when(daoMock.getAllAgeClassifications()).thenReturn(new ArrayList<>());
        service.getAllAgeClassifications();
        verify(daoMock).getAllAgeClassifications();
    }

    @Test
    public void testAddAgeClassification() throws Exception {
        when(daoMock.addAgeClassification(any(AgeClassification.class))).thenAnswer(i -> i.getArguments()[0]);
        AgeClassification tmp = service.addAgeClassification("18+");
        assertEquals(tmp.getName(), "18+");
    }

    @Test
    public void testDeleteAgeClassification() throws Exception {
        service.deleteAgeClassification(1);
        verify(daoMock).deleteAgeClassification(1);
    }
    
    @Test(expected = MyValidationException.class)
    public void testDeleteAgeClassificationException1() throws Exception {
        when(daoMock.getAgeClassification(any(long.class))).thenReturn(null);
        service.deleteAgeClassification(1);
    }
    
    @Test(expected = MyValidationException.class)
    public void testDeleteAgeClassificationException2() throws Exception {
        when(seriesDaoMock.ageClassificationNotUsed(any(AgeClassification.class))).thenReturn(false);
        service.deleteAgeClassification(1);
    }

    @Test
    public void testModifyAgeClassification() throws Exception {
        when(daoMock.modifyAgeClassification(any(AgeClassification.class))).thenAnswer(i -> i.getArguments()[0]);
        AgeClassification tmp = service.modifyAgeClassification(1, "16+");
        assertEquals(tmp.getName(), "16+");
    }
    
    @Test(expected = MyValidationException.class)
    public void testModifyAgeClassificationException() throws Exception {
        when(daoMock.getAgeClassification(any(long.class))).thenReturn(null);
        service.modifyAgeClassification(1, "16+");
    }

    @Test
    public void testCanBeDeleted1() throws Exception {
        boolean tmp = service.canBeDeleted(1);
        assertEquals(tmp, true);
    }
    
    @Test
    public void testCanBeDeleted2() throws Exception {
        when(seriesDaoMock.ageClassificationNotUsed(any(AgeClassification.class))).thenReturn(false);
        boolean tmp = service.canBeDeleted(1);
        assertEquals(tmp, false);
    }
    
    @Test(expected = MyValidationException.class)
    public void testCanBeDeletedException() throws Exception {
        when(daoMock.getAgeClassification(any(long.class))).thenReturn(null);
        service.canBeDeleted(1);
    }
}
