package myapp.SzakdolgozatBE.rules;

import myapp.SzakdolgozatBE.MyValidationException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

public class RulesServiceTest {
    
    @Mock
    private RulesDAO daoMock;
    
    @InjectMocks
    private RulesService service;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        
        Rules tmp = new Rules();
        tmp.setId(1L);
        tmp.setContent("Szabályzat.");
        
        when(daoMock.getRules()).thenReturn(tmp);
        when(daoMock.modifyRules(any(Rules.class))).thenAnswer(i -> i.getArguments()[0]);
    }
    
    @Test
    public void testGetRules() {
        Rules tmp = service.getRules();
        assertEquals((long)tmp.getId(), 1L);
        assertEquals(tmp.getContent(), "Szabályzat.");
    }

    @Test
    public void testModifyRules() throws Exception {
        Rules tmp = service.modifyRules("Új szabályzat.");
        assertEquals((long)tmp.getId(), 1L);
        assertEquals(tmp.getContent(), "Új szabályzat.");
    }
    
    @Test(expected = MyValidationException.class)
    public void testModifyRulesException() throws Exception {
        when(daoMock.getRules()).thenReturn(null);
        service.modifyRules("Új szabályzat.");
    }
}
