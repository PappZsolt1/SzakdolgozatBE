package myapp.SzakdolgozatBE.series;

import java.util.ArrayList;
import myapp.SzakdolgozatBE.MyValidationException;
import myapp.SzakdolgozatBE.Wrapper;
import myapp.SzakdolgozatBE.ageClassification.AgeClassification;
import myapp.SzakdolgozatBE.ageClassification.AgeClassificationDAO;
import myapp.SzakdolgozatBE.genre.Genre;
import myapp.SzakdolgozatBE.genre.GenreDAO;
import myapp.SzakdolgozatBE.season.Season;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

public class SeriesServiceTest {
    
    @Mock
    SeriesDAO daoMock;
    
    @Mock
    AgeClassificationDAO ageClassificationDaoMock;
    
    @Mock
    GenreDAO genreDaoMock;
    
    @InjectMocks
    SeriesService service;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        
        when(daoMock.getSeries(any(long.class))).thenReturn(getSeries());
        when(daoMock.addSeries(any(Series.class))).thenAnswer(i -> i.getArguments()[0]);
        
        when(daoMock.getResultSeries(any(int.class), any(int.class), any(String.class)))
                .thenReturn(new ArrayList<>());
        when(daoMock.getNumberOfResultSeries(any(String.class))).thenReturn(30L);
        
        AgeClassification a = new AgeClassification();
        a.setId(1L);
        when(ageClassificationDaoMock.getAgeClassification(any(long.class))).thenReturn(a);
        
        Genre g = new Genre();
        g.setId(1L);
        when(genreDaoMock.getGenre(any(long.class))).thenReturn(g);
    }
    
    public Series getSeries() {
        AgeClassification a = new AgeClassification();
        a.setId(1L);
        
        Genre g = new Genre();
        g.setId(1L);
        
        Series tmp = new Series();
        tmp.setTitle("cím");
        tmp.setReleaseYear(2000);
        tmp.setImageUrl("a");
        tmp.setAgeClassification(a);
        tmp.setGenre(g);
        tmp.setSeasons(new ArrayList<>());
        return tmp;
    }

    @Test
    public void testAddSeries() throws Exception {
        Series tmp = getSeries();
        Series a = service.addSeries(tmp);
        assertEquals(a.getTitle(), tmp.getTitle());
    }
    
    @Test(expected = MyValidationException.class)
    public void testAddSeriesException1() throws Exception {
        Series tmp = getSeries();
        tmp.setId(1L);
        Series a = service.addSeries(tmp);
    }
    
    @Test(expected = MyValidationException.class)
    public void testAddSeriesException2() throws Exception {
        Series tmp = getSeries();
        tmp.setTitle(" cím");
        Series a = service.addSeries(tmp);
    }

    @Test
    public void testGetSeries() throws Exception {
        Series tmp = service.getSeries(1);
        assertEquals(tmp.getTitle(), "cím");
    }
    
    @Test(expected = MyValidationException.class)
    public void testGetSeriesException() throws Exception {
        when(daoMock.getSeries(any(long.class))).thenReturn(null);
        service.getSeries(1);
    }

    @Test
    public void testCheckIfExists1() throws Exception {
        assertEquals(service.checkIfExists(1), true);
    }
    
    @Test
    public void testCheckIfExists2() throws Exception {
        when(daoMock.getSeries(any(long.class))).thenReturn(null);
        assertEquals(service.checkIfExists(1), false);
    }

    @Test
    public void testGetResultSeries() throws Exception {
        Wrapper tmp = service.getResultSeries(1, 10, "a");
        assertEquals(tmp.getTotal(), 30);
        assertNotNull(tmp.getResults());
    }
    
    @Test(expected = MyValidationException.class)
    public void testGetResultSeriesException1() throws Exception {
        service.getResultSeries(0, 10, "a");
    }
    
    @Test(expected = MyValidationException.class)
    public void testGetResultSeriesException2() throws Exception {
        service.getResultSeries(1, 10, " a");
    }
    
    @Test(expected = MyValidationException.class)
    public void testGetResultSeriesException3() throws Exception {
        service.getResultSeries(1, 1, "a");
    }
    
    @Test(expected = MyValidationException.class)
    public void testGetResultSeriesException4() throws Exception {
        service.getResultSeries(3, 20, "a");
    }

    @Test
    public void testDeleteSeries() throws Exception {
        service.deleteSeries(1);
        verify(daoMock).deleteSeries(any(long.class));
    }
    
    @Test(expected = MyValidationException.class)
    public void testDeleteSeriesException1() throws Exception {
        when(daoMock.getSeries(any(long.class))).thenReturn(null);
        service.deleteSeries(1);
    }
    
    @Test(expected = MyValidationException.class)
    public void testDeleteSeriesException2() throws Exception {
        Series tmp = getSeries();
        Season s = new Season();
        s.setId(1L);
        tmp.getSeasons().add(s);
        when(daoMock.getSeries(any(long.class))).thenReturn(tmp);
        service.deleteSeries(1);
    }

    @Test
    public void testModifySeries() throws Exception {
        when(daoMock.modifySeries(any(Series.class))).thenAnswer(i -> i.getArguments()[0]);
        Series tmp = getSeries();
        tmp.setId(1L);
        Series a = service.modifySeries(tmp);
        assertEquals(a.getTitle(), tmp.getTitle());
    }
    
    @Test(expected = MyValidationException.class)
    public void testModifySeriesException1() throws Exception {
        service.modifySeries(getSeries());
    }
    
    @Test(expected = MyValidationException.class)
    public void testModifySeriesException2() throws Exception {
        when(daoMock.getSeries(any(long.class))).thenReturn(null);
        Series tmp = getSeries();
        tmp.setId(1L);
        service.modifySeries(tmp);
    }
    
    @Test(expected = MyValidationException.class)
    public void testModifySeriesException3() throws Exception {
        Series tmp = getSeries();
        tmp.setId(1L);
        tmp.setTitle(" cím");
        service.modifySeries(tmp);
    }

    @Test
    public void testCanBeDeleted1() throws Exception {
        assertEquals(service.canBeDeleted(1), true);
    }
    
    @Test
    public void testCanBeDeleted2() throws Exception {
        Series tmp = getSeries();
        Season s = new Season();
        s.setId(1L);
        tmp.getSeasons().add(s);
        when(daoMock.getSeries(any(long.class))).thenReturn(tmp);
        assertEquals(service.canBeDeleted(1), false);
    }
    
    @Test(expected = MyValidationException.class)
    public void testCanBeDeletedException() throws Exception {
        when(daoMock.getSeries(any(long.class))).thenReturn(null);
        service.canBeDeleted(1);
    }

    @Test
    public void testValidateSeries() throws Exception {
        Series tmp = getSeries();
        assertEquals(service.validateSeries(tmp), true);
        tmp.setTitle(" cím");
        assertEquals(service.validateSeries(tmp), false);
        tmp.setTitle("cím");
        tmp.setReleaseYear(1000);
        assertEquals(service.validateSeries(tmp), false);
        tmp.setReleaseYear(2000);
        tmp.setImageUrl(" a");
        assertEquals(service.validateSeries(tmp), false);
        tmp.setImageUrl("a");
        when(genreDaoMock.getGenre(any(long.class))).thenReturn(null);
        assertEquals(service.validateSeries(tmp), false);
        Genre g = new Genre();
        g.setId(1L);
        when(genreDaoMock.getGenre(any(long.class))).thenReturn(g);
        when(ageClassificationDaoMock.getAgeClassification(any(long.class))).thenReturn(null);
        assertEquals(service.validateSeries(tmp), false);
    }
    
}
