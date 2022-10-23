package ua.geeksforless.calculator;

import org.apache.log4j.Logger;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CalculatorTest {

    private final static Logger log4j =
            Logger.getLogger(CalculatorTest.class);

    @Test
    public void calculateTest() {
        Calculator calculatorPlus = new Calculator("2+2");
        Calculator calculatorMinus = new Calculator("2-2");
        Calculator calculatorMultiply = new Calculator("3*3");
        Calculator calculatorDivide = new Calculator("3/3");

        double testPlus = calculatorPlus.calculate();
        double testMinus = calculatorMinus.calculate();
        double testMultiply = calculatorMultiply.calculate();
        double testDivide = calculatorDivide.calculate();

        log4j.info(testPlus);
        assertEquals(4.0, testPlus, 0);
        log4j.info(testMinus);
        assertEquals(0, testMinus, 0);
        log4j.info(testMultiply);
        assertEquals(9, testMultiply, 0);
        log4j.info(testDivide);
        assertEquals(1, testDivide, 0);
    }

    @Test
    public void multiplyTest() {
        Calculator calculatorPlus = new Calculator("2+2");
        Calculator calculatorMinus = new Calculator("2-2");
        Calculator calculatorMultiply = new Calculator("3*3");
        Calculator calculatorDivide = new Calculator("3/3");

        double testPlus = calculatorPlus.multiply(); // shouldn't work
        double testMinus = calculatorMinus.multiply(); // shouldn't work
        double testMultiply = calculatorMultiply.multiply();
        double testDivide = calculatorDivide.multiply();

        log4j.info(testPlus);
        assertNotEquals(4.0, testPlus, 0);
        log4j.info(testMinus);
        assertNotEquals(0, testMinus, 0);
        log4j.info(testMultiply);
        assertEquals(9, testMultiply, 0);
        log4j.info(testDivide);
        assertEquals(1, testDivide, 0);
    }

    @Test
    public void factorTest() {
        Calculator calculatorWithOutBrackets = new Calculator("2+2");
        Calculator calculatorWithBrackets = new Calculator("(2+2)");
        Calculator calculatorWithOneBrackets = new Calculator(")2+2"); //
        // incorrect bracket
        Calculator calculatorMultiBrackets = new Calculator("(2+(2+(2+2)))");

        double testWithOutBrackets = calculatorWithOutBrackets.factor(); //
        // shouldn't work
        double testWithBrackets = calculatorWithBrackets.factor();
        double testWithOneBrackets = calculatorWithOneBrackets.factor();
        double testWithMultiBrackets = calculatorMultiBrackets.factor();

        log4j.info(testWithOutBrackets);
        assertNotEquals(4.0, testWithOutBrackets, 0);
        log4j.info(testWithBrackets);
        assertEquals(4, testWithBrackets, 0);
        log4j.info(testWithOneBrackets);
        assertEquals(-1234567890, testWithOneBrackets, 0);  // incorrect bracket
        log4j.info(testWithMultiBrackets);
        assertEquals(8, testWithMultiBrackets, 0);
    }
}