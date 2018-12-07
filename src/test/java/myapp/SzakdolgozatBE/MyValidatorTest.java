/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myapp.SzakdolgozatBE;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Gep
 */
public class MyValidatorTest {
    
    public MyValidatorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of validateText method, of class MyValidator.
     */
    @Test
    public void testValidateText() {
        System.out.println("validateText");
        String text = "";
        int max = 0;
        MyValidator instance = new MyValidator();
        boolean expResult = false;
        boolean result = instance.validateText(text, max);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validateNumber method, of class MyValidator.
     */
    @Test
    public void testValidateNumber() {
        System.out.println("validateNumber");
        int number = 0;
        int min = 0;
        int max = 0;
        MyValidator instance = new MyValidator();
        boolean expResult = false;
        boolean result = instance.validateNumber(number, min, max);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validateDate method, of class MyValidator.
     */
    @Test
    public void testValidateDate() {
        System.out.println("validateDate");
        String date = "";
        int min = 0;
        int max = 0;
        MyValidator instance = new MyValidator();
        boolean expResult = false;
        boolean result = instance.validateDate(date, min, max);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validateLength method, of class MyValidator.
     */
    @Test
    public void testValidateLength() {
        System.out.println("validateLength");
        String length = "";
        MyValidator instance = new MyValidator();
        boolean expResult = false;
        boolean result = instance.validateLength(length);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validateSize method, of class MyValidator.
     */
    @Test
    public void testValidateSize() {
        System.out.println("validateSize");
        int size = 0;
        MyValidator instance = new MyValidator();
        boolean expResult = false;
        boolean result = instance.validateSize(size);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
