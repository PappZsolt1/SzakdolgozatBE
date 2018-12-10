package myapp.SzakdolgozatBE.gender;

import java.util.ArrayList;
import myapp.SzakdolgozatBE.MyValidationException;
import myapp.SzakdolgozatBE.actor.ActorDAO;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

public class GenderServiceTest {
    
    @Mock
    GenderDAO daoMock;
    
    @Mock
    ActorDAO actorDaoMock;
    
    @InjectMocks
    GenderService service;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        
        Gender tmp = new Gender();
        tmp.setId(1L);
        tmp.setName("Férfi");
        when(daoMock.getGender(any(long.class))).thenReturn(tmp);
        when(actorDaoMock.genreNotUsed(any(Gender.class))).thenReturn(true);
    }

    @Test
    public void testGetGender() throws Exception {
        Gender tmp = service.getGender(1L);
        assertEquals((long)tmp.getId(), 1L);
        assertEquals(tmp.getName(), "Férfi");
    }
    
    @Test(expected = MyValidationException.class)
    public void testGetGenderException() throws Exception {
        when(daoMock.getGender(any(long.class))).thenReturn(null);
        service.getGender(1L);
    }

    @Test
    public void testGetAllGenders() throws Exception {
        when(daoMock.getAllGenders()).thenReturn(new ArrayList<>());
        service.getAllGenders();
        verify(daoMock).getAllGenders();
    }

    @Test
    public void testAddGender() throws Exception {
        when(daoMock.addGender(any(Gender.class))).thenAnswer(i -> i.getArguments()[0]);
        Gender tmp = service.addGender("Nő");
        assertEquals(tmp.getName(), "Nő");
    }
    
    @Test(expected = MyValidationException.class)
    public void testAddGenderException() throws Exception {
        service.addGender("   Nő");
    }

    @Test
    public void testDeleteGender() throws Exception {
        service.deleteGender(1);
        verify(daoMock).deleteGender(1);
    }
    
    @Test(expected = MyValidationException.class)
    public void testDeleteGenderException1() throws Exception {
        when(daoMock.getGender(any(long.class))).thenReturn(null);
        service.deleteGender(1);
    }
    
    @Test(expected = MyValidationException.class)
    public void testDeleteGenderException2() throws Exception {
        when(actorDaoMock.genreNotUsed(any(Gender.class))).thenReturn(false);
        service.deleteGender(1);
    }

    @Test
    public void testModifyGender() throws Exception {
        when(daoMock.modifyGender(any(Gender.class))).thenAnswer(i -> i.getArguments()[0]);
        Gender tmp = service.modifyGender(1, "asd");
        assertEquals(tmp.getName(), "asd");
    }
    
    @Test(expected = MyValidationException.class)
    public void testModifyGenderException1() throws Exception {
        when(daoMock.getGender(any(long.class))).thenReturn(null);
        service.modifyGender(1, "asd");
    }
    
    @Test(expected = MyValidationException.class)
    public void testModifyGenderException2() throws Exception {
        service.modifyGender(1, " asd");
    }

    @Test
    public void testCanBeDeleted1() throws Exception {
        boolean tmp = service.canBeDeleted(1);
        assertEquals(tmp, true);
    }
    
    @Test
    public void testCanBeDeleted2() throws Exception {
        when(actorDaoMock.genreNotUsed(any(Gender.class))).thenReturn(false);
        boolean tmp = service.canBeDeleted(1);
        assertEquals(tmp, false);
    }
    
    @Test(expected = MyValidationException.class)
    public void testCanBeDeletedException() throws Exception {
        when(daoMock.getGender(any(long.class))).thenReturn(null);
        service.canBeDeleted(1);
    }
}
