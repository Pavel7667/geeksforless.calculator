package ua.geeksforless.calculator;

import org.apache.log4j.Logger;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WrongFormulaExceptionTest {

    private final static Logger log4j =
            Logger.getLogger(WrongFormulaExceptionTest.class);

    @Test
    public void testToString() {
        Calculator test = new Calculator("2+test");
        double testInvalidFormula = test.calculate();
        log4j.info(testInvalidFormula);
        assertEquals(testInvalidFormula, -1.23456789E9, 0);
    }
}