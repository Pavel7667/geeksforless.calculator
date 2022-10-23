package ua.geeksforless.calculator;

import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class NegativeTest {

    private final static Logger log4j =
            Logger.getLogger(NegativeTest.class);

    @Test
    public void isOperatorTest() {
        Negative negative = new Negative();
        boolean isPlus = negative.isOperator("+");
        log4j.info(isPlus);
        assertTrue(isPlus);
    }

    @Test
    public void isBracketTest() {
        Negative negative = new Negative();
        boolean isBracket = negative.isBracket("(");
        log4j.info(isBracket);
        assertTrue(isBracket);
    }

    @Test
    public void isNumberTest() {
        boolean isNumber = Negative.isNumber("22.22");
        log4j.info(isNumber);
        assertTrue(isNumber);
    }

    @Test
    public void getArrayOfFormulaWithNegativeDigitsTest() {
        Negative negative = new Negative();
        UserTextFormatter userTextFormatter = new UserTextFormatter();

        String test = "-2+-2+(-2+2)";
        String[] splitArray = userTextFormatter.splitter(test);
        String[] testArray =
                negative.getArrayOfFormulaWithNegativeDigits(splitArray);

        log4j.info(Arrays.toString(testArray));
        boolean isNegativeTrue = false;

        for (int i = 0; i < testArray.length; i++) {
            if (testArray[2].equals("-2") &&
                    testArray[5].equals("-2") &&
                    testArray[9].equals("-2")
            ) {
                isNegativeTrue = true;
            }
        }
        assertTrue(isNegativeTrue);
    }

    @Test
    public void getReadyArrayTest() {
        Negative negative = new Negative();
        UserTextFormatter userTextFormatter = new UserTextFormatter();

        String test = "-2+-2+(-2+2)";
        String[] splitArray = userTextFormatter.splitter(test);
        String[] testArray =
                negative.getArrayOfFormulaWithNegativeDigits(splitArray);
        ArrayList<String> testArrayWithNoMinus =
                negative.getReadyArray(testArray);

        log4j.info(testArrayWithNoMinus);

        boolean isNegativeTrue = false;

        for (int i = 0; i < testArray.length; i++) {
            if (testArray[2].equals("-2") &&
                    testArray[5].equals("-2") &&
                    testArray[9].equals("-2")
            ) {
                isNegativeTrue = true;
            }
        }
        assertTrue(isNegativeTrue);
        log4j.info(testArray.length);
        log4j.info(testArrayWithNoMinus.size());
        assertNotEquals(testArray.length, testArrayWithNoMinus.size());
    }

    @Test
    public void amountOfNumbers() {
        Negative negative = new Negative();
        UserTextFormatter userTextFormatter = new UserTextFormatter();
        String test = "-2+-2.2+(-2+22222)";
        String[] splitArray = userTextFormatter.splitter(test);
        int count = negative.amountOfNumbers(splitArray);
        log4j.info(count);
        assertEquals(4,count);
    }
}