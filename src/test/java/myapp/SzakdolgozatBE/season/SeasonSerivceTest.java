package myapp.SzakdolgozatBE.season;

import java.util.ArrayList;
import myapp.SzakdolgozatBE.MyValidationException;
import myapp.SzakdolgozatBE.episode.Episode;
import myapp.SzakdolgozatBE.series.Series;
import myapp.SzakdolgozatBE.series.SeriesDAO;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

public class SeasonSerivceTest {
    
    @Mock
    SeasonDAO daoMock;
    
    @Mock
    SeriesDAO seriesDaoMock;
    
    @InjectMocks
    SeasonSerivce service;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        
        when(daoMock.getSeason(any(long.class))).thenReturn(getSeason());
        
        Series s = new Series();
        s.setId(1L);
        s.setSeasons(new ArrayList<>());
        when(seriesDaoMock.getSeries(any(long.class))).thenReturn(s);
    }
    
    public Season getSeason() {
        Season tmp = new Season();
        tmp.setNumber(1);
        tmp.setEpisodes(new ArrayList<>());
        Series s = new Series();
        s.setSeasons(new ArrayList<>());
        tmp.setSeries(s);
        return tmp;
    }

    @Test
    public void testAddSeason() throws Exception {
        Season tmp = service.addSeason(1, getSeason());
        assertEquals((long)tmp.getSeries().getId(), 1L);
        verify(seriesDaoMock).modifySeries(any(Series.class));
    }
    
    @Test(expected = MyValidationException.class)
    public void testAddSeasonException1() throws Exception {
        Season tmp = getSeason();
        tmp.setId(1L);
        service.addSeason(1, tmp);
    }
    
    @Test(expected = MyValidationException.class)
    public void testAddSeasonException2() throws Exception {
        Season tmp = getSeason();
        tmp.setNumber(0);
        service.addSeason(1, tmp);
    }
    
    @Test(expected = MyValidationException.class)
    public void testAddSeasonException3() throws Exception {
        when(seriesDaoMock.getSeries(any(long.class))).thenReturn(null);
        service.addSeason(1, getSeason());
    }

    @Test
    public void testGetSeason() throws Exception {
        Season tmp = service.getSeason(1);
        assertEquals(tmp.getNumber(), 1);
    }
    
    @Test(expected = MyValidationException.class)
    public void testGetSeasonException() throws Exception {
        when(daoMock.getSeason(any(long.class))).thenReturn(null);
        service.getSeason(1);
    }

    @Test
    public void testCheckIfExists1() throws Exception {
        assertEquals(service.checkIfExists(1), true);
    }
    
    @Test
    public void testCheckIfExists2() throws Exception {
        when(daoMock.getSeason(any(long.class))).thenReturn(null);
        assertEquals(service.checkIfExists(1), false);
    }

    @Test
    public void testDeleteSeason() throws Exception {
        service.deleteSeason(1, 1);
        verify(seriesDaoMock).modifySeries(any(Series.class));
    }
    
    @Test(expected = MyValidationException.class)
    public void testDeleteSeasonException1() throws Exception {
        when(daoMock.getSeason(any(long.class))).thenReturn(null);
        service.deleteSeason(1, 1);
    }
    
    @Test(expected = MyValidationException.class)
    public void testDeleteSeasonException2() throws Exception {
        Episode e = new Episode();
        e.setId(1L);
        Season tmp = getSeason();
        tmp.getEpisodes().add(e);
        when(daoMock.getSeason(any(long.class))).thenReturn(tmp);
        service.deleteSeason(1, 1);
    }
    
    @Test(expected = MyValidationException.class)
    public void testDeleteSeasonException3() throws Exception {
        when(seriesDaoMock.getSeries(any(long.class))).thenReturn(null);
        service.deleteSeason(1, 1);
    }

    @Test
    public void testModifySeason1() throws Exception {
        Season tmp = getSeason();
        tmp.setId(1L);
        Series s = new Series();
        s.setId(1L);
        when(daoMock.getSeason(any(long.class))).thenReturn(tmp);
        service.modifySeason(1, tmp);
        verify(daoMock).modifySeason(any(Season.class));
    }
    
    @Test
    public void testModifySeason2() throws Exception {
        Season tmp = getSeason();
        tmp.setId(1L);
        service.modifySeason(1, tmp);
        verify(daoMock).modifySeason(any(Season.class));
        verify(seriesDaoMock, Mockito.times(2)).modifySeries(any(Series.class));
    }
    
    @Test(expected = MyValidationException.class)
    public void testModifySeasonException1() throws Exception {
        service.modifySeason(1, getSeason());
    }
    
    @Test(expected = MyValidationException.class)
    public void testModifySeasonException2() throws Exception {
        Season tmp = getSeason();
        tmp.setId(1L);
        tmp.setNumber(0);
        service.modifySeason(1, tmp);
    }
    
    @Test(expected = MyValidationException.class)
    public void testModifySeasonException3() throws Exception {
        when(daoMock.getSeason(any(long.class))).thenReturn(null);
        Season tmp = getSeason();
        tmp.setId(1L);
        service.modifySeason(1, tmp);
    }
    
    @Test(expected = MyValidationException.class)
    public void testModifySeasonException4() throws Exception {
        when(seriesDaoMock.getSeries(any(long.class))).thenReturn(null);
        Season tmp = getSeason();
        tmp.setId(1L);
        service.modifySeason(1, tmp);
    }

    @Test
    public void testCanBeDeleted1() throws Exception {
        assertEquals(service.canBeDeleted(1), true);
    }
    
    @Test
    public void testCanBeDeleted2() throws Exception {
        Season tmp = getSeason();
        Episode e = new Episode();
        e.setId(1L);
        tmp.getEpisodes().add(e);
        when(daoMock.getSeason(any(long.class))).thenReturn(tmp);
        assertEquals(service.canBeDeleted(1), false);
    }
}
