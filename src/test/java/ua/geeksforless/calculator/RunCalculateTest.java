package ua.geeksforless.calculator;

import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class RunCalculateTest {

/*
Test class with ready Example of Formula
With Correct versions of Formula
And versions with  Mistakes
*/

    private final static Logger log4j =
            Logger.getLogger(RunCalculateTest.class);

    @Test
    public void caseOfFormulaTrueTest() {
        log4j.info("Or see ready versions Correct Test ");
        ArrayList<String> caseOfFormulaTrue = new ArrayList<>();

        // defolt operation
        caseOfFormulaTrue.add("2+2");
        // operation with not 0-9 Numbers
        caseOfFormulaTrue.add("22+222+222+2222");
        // operation with fractional numbers
        caseOfFormulaTrue.add("2.2+2.22+2.22+2.222");
        //  operation with to operand "+,/,*", "-"
        caseOfFormulaTrue.add("2+-2");
        caseOfFormulaTrue.add("2*-2");
        caseOfFormulaTrue.add("2/-2");
        //  operation with all operand and "-"
        caseOfFormulaTrue.add("2+-2*-2/-2");
        // just minus digit
        caseOfFormulaTrue.add("-2");
        //  minus digit in bracket
        caseOfFormulaTrue.add("(-2)");
        // multi bracket
        caseOfFormulaTrue.add("(((-20+2)+2))+2");
        //  minus digit at first position
        caseOfFormulaTrue.add("-2+2");
        // minus digit at first position and bracket
        caseOfFormulaTrue.add("(-20+2)+2");
        // two brackets
        caseOfFormulaTrue.add("(-20+2)+(2*(2+-2))+2");
        // multi brackets
        caseOfFormulaTrue.add("(-20+2)+2+-2+2*(2+2-(2/2))");

        int count = 1;
        for (String userText : caseOfFormulaTrue) {
            double invalidCase = -1234567890;
            Calculator calculatorTest = new Calculator(userText);
            double result = calculatorTest.calculate();
            assertNotEquals(invalidCase, result);

            Calculator calc = new Calculator(userText);
            UserTextFormatter userTF = new UserTextFormatter();
            Negative neg = new Negative();

            log4j.info((count++) + " Correct version : " + userText + " = " + calc.calculate() +
                    "\n// count of User numbers: " +
                    neg.amountOfNumbers(userTF.userTextFormatter(userText)));
        }
    }

    @Test
    public void caseOfFormulaFalseTest() {
        log4j.info("Or see ready versions Mistakes test ");
        ArrayList<String> caseOfFormulaFalse = new ArrayList<>();
        // !!! cases with Mistakes should show warning description
        caseOfFormulaFalse.add("2+Q+2"); // symbol
        caseOfFormulaFalse.add("2(+2"); // incorrect bracket
        caseOfFormulaFalse.add("2()+2"); // incorrect brackets
        caseOfFormulaFalse.add("2(+)2"); // incorrect brackets
        caseOfFormulaFalse.add("2+2("); // incorrect bracket
        caseOfFormulaFalse.add("2+2(()"); // incorrect brackets
        caseOfFormulaFalse.add(")2+2("); // incorrect brackets
        caseOfFormulaFalse.add(".01+.01"); // incorrect value
        caseOfFormulaFalse.add("1++1"); // incorrect operator
        caseOfFormulaFalse.add("1//1"); // incorrect operator
        caseOfFormulaFalse.add("1-/1"); // incorrect operator
        caseOfFormulaFalse.add("1-+1"); // incorrect operator
        // !!!

        int count = 1;
        for (String userText : caseOfFormulaFalse) {
            double invalidCase = -1234567890;
            Calculator calculatorTest = new Calculator(userText);
            double result = calculatorTest.calculate();
            assertEquals(invalidCase, result, 0);
            log4j.info((count++) + "_Case with Mistakes : " + userText + " = "
                    + result);
        }
    }
}