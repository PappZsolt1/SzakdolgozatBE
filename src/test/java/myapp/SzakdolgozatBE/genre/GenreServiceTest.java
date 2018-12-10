package myapp.SzakdolgozatBE.genre;

import java.util.ArrayList;
import myapp.SzakdolgozatBE.MyValidationException;
import myapp.SzakdolgozatBE.movie.MovieDAO;
import myapp.SzakdolgozatBE.series.SeriesDAO;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

public class GenreServiceTest {
    
    @Mock
    GenreDAO daoMock;
    
    @Mock
    MovieDAO movieDaoMock;
    
    @Mock
    SeriesDAO seriesDaoMock;
    
    @InjectMocks
    GenreService service;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        
        Genre tmp = new Genre();
        tmp.setId(1L);
        tmp.setName("Akció");
        when(daoMock.getGenre(any(long.class))).thenReturn(tmp);
        when(movieDaoMock.genreNotUsed(any(Genre.class))).thenReturn(true);
        when(seriesDaoMock.genreNotUsed(any(Genre.class))).thenReturn(true);
    }

    @Test
    public void testGetGenre() throws Exception {
        Genre tmp = service.getGenre(1);
        assertEquals((long)tmp.getId(), 1L);
        assertEquals(tmp.getName(), "Akció");
    }
    
    @Test(expected = MyValidationException.class)
    public void testGetGenreExneption() throws Exception {
        when(daoMock.getGenre(any(long.class))).thenReturn(null);
        service.getGenre(1);
    }

    @Test
    public void testGetAllGenres() throws Exception {
        when(daoMock.getAllGenres()).thenReturn(new ArrayList<>());
        service.getAllGenres();
        verify(daoMock).getAllGenres();
    }

    @Test
    public void testAddGenre() throws Exception {
        when(daoMock.addGenre(any(Genre.class))).thenAnswer(i -> i.getArguments()[0]);
        Genre tmp = service.addGenre("Vígjáték");
        assertEquals(tmp.getName(), "Vígjáték");
    }
    
    @Test(expected = MyValidationException.class)
    public void testAddGenreException() throws Exception {
        service.addGenre("Vígjáték ");
    }

    @Test
    public void testDeleteGenre() throws Exception {
        service.deleteGenre(1);
        verify(daoMock).deleteGenre(1);
    }
    
    @Test(expected = MyValidationException.class)
    public void testDeleteGenreException1() throws Exception {
        when(daoMock.getGenre(any(long.class))).thenReturn(null);
        service.deleteGenre(1);
    }
    
    @Test(expected = MyValidationException.class)
    public void testDeleteGenreException2() throws Exception {
        when(seriesDaoMock.genreNotUsed(any(Genre.class))).thenReturn(false);
        service.deleteGenre(1);
    }

    @Test
    public void testModifyGenre() throws Exception {
        when(daoMock.modifyGenre(any(Genre.class))).thenAnswer(i -> i.getArguments()[0]);
        Genre tmp = service.modifyGenre(1, "Horror");
        assertEquals(tmp.getName(), "Horror");
    }
    
    @Test(expected = MyValidationException.class)
    public void testModifyGenreException1() throws Exception {
        when(daoMock.getGenre(any(long.class))).thenReturn(null);
        service.modifyGenre(1, "Horror");
    }
    
    @Test(expected = MyValidationException.class)
    public void testModifyGenreException2() throws Exception {
        service.modifyGenre(1, "  Horror");
    }

    @Test
    public void testCanBeDeleted1() throws Exception {
        boolean tmp = service.canBeDeleted(1);
        assertEquals(tmp, true);
    }
    
    @Test
    public void testCanBeDeleted2() throws Exception {
        when(movieDaoMock.genreNotUsed(any(Genre.class))).thenReturn(false);
        boolean tmp = service.canBeDeleted(1);
        assertEquals(tmp, false);
    }
    
    @Test(expected = MyValidationException.class)
    public void testCanBeDeletedException() throws Exception {
        when(daoMock.getGenre(any(long.class))).thenReturn(null);
        service.canBeDeleted(1);
    }
}
