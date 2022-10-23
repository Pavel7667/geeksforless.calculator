package ua.geeksforless.calculator;

import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class UserTextFormatterTest {

    private final static Logger log4j =
            Logger.getLogger(UserTextFormatterTest.class);

    @Test
    public void splitterTest() {
        UserTextFormatter userTextFormatter = new UserTextFormatter();
        String[] test = userTextFormatter.splitter("-2.2+2+(-22/2)");
        log4j.info(Arrays.toString(test));
        assertEquals(test.length, 12);
    }

    @Test
    public void isDigitTest() {
        boolean one = UserTextFormatter.isDigit("1");
        log4j.info((one));
        assertTrue(one);
    }

    @Test
    public void userTextFormatterTest() {
        UserTextFormatter userTextFormatter = new UserTextFormatter();
        String[] test = userTextFormatter.userTextFormatter("-2.2+2+(-22/2)");
        log4j.info(Arrays.toString(test));
        assertNotEquals(test.length, 12);
        assertEquals(test.length, 9);
    }

    @Test
    public void isBracketCorrectTest() {
        UserTextFormatter userTextFormatter = new UserTextFormatter();
        ArrayList<String> testArrayFalse = new ArrayList<>();
        ArrayList<String> testArrayTrue = new ArrayList<>();

        //  All should be incorrect
        testArrayFalse.add(")2+2("); // in different

        testArrayFalse.add("3+5()+2");// () at middle
        testArrayFalse.add("(1+5()"); // () at end
        testArrayFalse.add("()2+5"); // () at start

        testArrayFalse.add(")4+5)"); // not correct start
        testArrayFalse.add("(5+5("); // not correct end
        testArrayFalse.add("6(+5+2)");// not correct middle after digit

        testArrayFalse.add("(8+5)+5)))))))"); // not between

        // All should be correct
        testArrayTrue.add("7+(5+5+5)+2");//  correct middle
        testArrayTrue.add("(10+5)"); //  single bracket
        testArrayTrue.add("(10+(5+(5+5)))"); //  multi  bracket in end
        testArrayTrue.add("(10+(5+(5+5)+(5+5)+5))"); //   multi  bracket
        testArrayTrue.add("11+5"); //  NO bracken

        int count = 1;
        // loop of should be incorrect
        for (String s : testArrayFalse) {
            boolean resultArrayFalse = userTextFormatter.isBracketCorrect(s);
            log4j.info((count++) + "_" + resultArrayFalse);
            assertFalse(resultArrayFalse);
        }
        // loop of should be correct
        for (String s : testArrayTrue) {
            boolean resultArrayTrue = userTextFormatter.isBracketCorrect(s);
            log4j.info((count++) + "_" + resultArrayTrue);
            assertTrue(resultArrayTrue);
        }
    }
}