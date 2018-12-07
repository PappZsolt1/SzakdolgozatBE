package myapp.SzakdolgozatBE;

import org.junit.Test;
import static org.junit.Assert.*;

public class MyValidatorTest {

    MyValidator val = new MyValidator();
    
    @Test
    public void testValidateText() {
        assertEquals(val.validateText("", 100), false);
        assertEquals(val.validateText("a", 100), true);
        assertEquals(val.validateText(" aaaa aa", 100), false);
        assertEquals(val.validateText("sds s ds ", 100), false);
        assertEquals(val.validateText("asdas sada\n   asd", 100), true);
        assertEquals(val.validateText("\na asd", 100), false);
        assertEquals(val.validateText("\ra a ds", 100), false);
        assertEquals(val.validateText("\taa as", 100), false);
        assertEquals(val.validateText("asdas\n", 100), false);
        assertEquals(val.validateText("as sa s\t", 100), false);
        assertEquals(val.validateText("asda\r", 100), false);
        assertEquals(val.validateText("asda asdasdasd", 5), false);
    }

    @Test
    public void testValidateNumber() {
        assertEquals(val.validateNumber(10, 10, 10), true);
        assertEquals(val.validateNumber(10, 1, 10), true);
        assertEquals(val.validateNumber(10, 10, 100), true);
        assertEquals(val.validateNumber(5, 6, 10), false);
        assertEquals(val.validateNumber(213, 1, 210), false);
    }

    @Test
    public void testValidateDate() {
        assertEquals(val.validateDate("2000. 01. 01.", 1750, 2100), true);
        assertEquals(val.validateDate("2100. 12. 30.", 1750, 2100), true);
        assertEquals(val.validateDate("2101. 02. 05.", 1750, 2100), false);
        assertEquals(val.validateDate("1750. 03. 20.", 1750, 2100), true);
        assertEquals(val.validateDate("1749. 07. 03.", 1750, 2100), false);
        assertEquals(val.validateDate("2200. 01. 01.", 1750, 2100), false);
        assertEquals(val.validateDate("1700. 01. 01.", 1750, 2100), false);
        assertEquals(val.validateDate("2000.01. 01.", 1750, 2100), false);
        assertEquals(val.validateDate("2000. 13. 01.", 1750, 2100), false);
        assertEquals(val.validateDate("2000. 02. 30.", 1750, 2100), false);
    }

    @Test
    public void testValidateLength() {
        assertEquals(val.validateLength("2 óra 21 perc"), true);
        assertEquals(val.validateLength("2 óra 5 perc"), true);
        assertEquals(val.validateLength("11 óra 0 perc"), true);
        assertEquals(val.validateLength("89 óra 3 perc"), true);
        assertEquals(val.validateLength("8 perc"), true);
        assertEquals(val.validateLength("54 perc"), true);
        assertEquals(val.validateLength("100 óra 21 perc"), true);
        assertEquals(val.validateLength("101 óra 21 perc"), false);
        assertEquals(val.validateLength("500 óra 21 perc"), false);
        assertEquals(val.validateLength("2 óra 61 perc"), false);
        assertEquals(val.validateLength("1 óra -2 perc"), false);
        assertEquals(val.validateLength("2óra 35 perc"), false);
        assertEquals(val.validateLength(" 45 perc"), false);
        assertEquals(val.validateLength("3perc"), false);
        assertEquals(val.validateLength("2 óra 21 per"), false);
    }

    @Test
    public void testValidateSize() {
        assertEquals(val.validateSize(10), true);
        assertEquals(val.validateSize(20), true);
        assertEquals(val.validateSize(30), true);
        assertEquals(val.validateSize(0), false);
        assertEquals(val.validateSize(12), false);
        assertEquals(val.validateSize(52), false);
        assertEquals(val.validateSize(-10), false);
        assertEquals(val.validateSize(110), false);
    }
}
