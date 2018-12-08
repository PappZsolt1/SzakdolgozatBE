package myapp.SzakdolgozatBE.rating;

import myapp.SzakdolgozatBE.MyValidationException;
import myapp.SzakdolgozatBE.episode.Episode;
import myapp.SzakdolgozatBE.episode.EpisodeDAO;
import myapp.SzakdolgozatBE.movie.Movie;
import myapp.SzakdolgozatBE.movie.MovieDAO;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

public class RatingServiceTest {
    
    @Mock
    private RatingDAO daoMock;
    
    @Mock
    private MovieDAO movieDaoMock;
    
    @Mock
    private EpisodeDAO episodeDaoMock;
    
    @InjectMocks
    private RatingService service;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        
        when(daoMock.canRateThisMovie(any(String.class), any(Movie.class))).thenReturn(true);
        when(daoMock.canRateThisEpisode(any(String.class), any(Episode.class))).thenReturn(true);
        when(movieDaoMock.getMovie(any(long.class))).thenReturn(new Movie());
        when(episodeDaoMock.getEpisode(any(long.class))).thenReturn(new Episode());
    }

    @Test
    public void testCanRateThisMovie() throws Exception {
        boolean tmp = service.canRateThisMovie(1, "Valaki");
        assertEquals(tmp, true);
    }
    
    @Test(expected = MyValidationException.class)
    public void testCanRateThisMovieException1() throws Exception {
        service.canRateThisMovie(1, null);
    }
    
    @Test(expected = MyValidationException.class)
    public void testCanRateThisMovieException2() throws Exception {
        when(movieDaoMock.getMovie(any(long.class))).thenReturn(null);
        service.canRateThisMovie(1, "Valaki");
    }

    @Test
    public void testCanRateThisEpisode() throws Exception {
        boolean tmp = service.canRateThisEpisode(1, "Valaki");
        assertEquals(tmp, true);
    }
    
    @Test(expected = MyValidationException.class)
    public void testCanRateThisEpisodeException1() throws Exception {
        service.canRateThisEpisode(1, null);
    }
    
    @Test(expected = MyValidationException.class)
    public void testCanRateThisEpisodeException2() throws Exception {
        when(episodeDaoMock.getEpisode(any(long.class))).thenReturn(null);
        service.canRateThisEpisode(1, "Valaki");
    }
}
