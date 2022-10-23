/**
 * %W% %E% Pavel Hnelytsia
 * <p>
 * Copyright (c) 1996-2022 Sun Microsystems, Inc. All Rights Reserved.
 * <p>
 * This software is the confidential and proprietary information of Sun
 * Microsystems, Inc. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Sun.
 * <p>
 * SUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
 * THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 * TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. SUN SHALL NOT BE LIABLE FOR
 * ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
 * DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 */

package ua.geeksforless.calculator;

import org.apache.log4j.Logger;

/**
 * The Calculator class performs the calculation considering brackets and
 * nested brackets.
 * <p>Operations that can be performed</p>
 * <p>"+" "-" "/" "*"</p>
 * <p>And also represent result of calculation</p>
 *
 * @author Pavel Hnelytsia
 * @version 1.0
 * @since 20.10.2022
 */
public class Calculator {

    private final static Logger log4j =
            Logger.getLogger(Calculator.class);

    // Aggregating an object to use a methods to help format user Text
    UserTextFormatter userTextFormatter = new UserTextFormatter();
    private String[] tokens;
    private int position;

    private boolean isUserBracketCorrect;

    /**
     * Constructor which take user Text and format it into want form
     * <p> Use method from UserTextFormatter class to format Text </p>
     * <p> And set start position as 0 </p>
     * <p> And set is user bracket input correct </p>
     *
     * @param userText user input Text
     */
    public Calculator(String userText) {
        this.isUserBracketCorrect =
                userTextFormatter.isBracketCorrect(userText);
        this.tokens = userTextFormatter.userTextFormatter(userText);
        this.position = 0;
    }

    //The following methods are using together to recursively descend

    /**
     * Method calculate
     * <p>  1). Fall into method multiple.</p>
     * <p>  2). After method multiple back and read formatted user Text until it`s
     * end.
     * <p>  3). If position in tokens array  equals "+" or "-" do same
     * operation</p>
     * <p>  4). In case any mistake, like: uncorrected bracket or symbol send
     * warning description and set result as -1234567890</p>
     * <p>Number -1234567890 will help in future, avoid inserting this result
     * in DB. It will be possible to create a method that checks if the
     * result is -1234567890 then do not insert in DB</p>
     *
     * @return first <p>Sum or subtracting numbers</p>
     */
    public double calculate() {
        if (!isUserBracketCorrect) {
            log4j.info(new WrongFormulaException());
            return -1234567890;
        }
        try {
            double first = multiply();

            while (position < tokens.length) {
                String operator = tokens[position];
                if (!operator.equals("+") && !operator.equals("-")) {
                    break;
                } else {
                    position++;
                }

                double second = multiply();
                if (operator.equals("+")) {
                    first += second;
                } else {
                    first -= second;
                }
            }
            return first;
        } catch (Exception ignored) {
            log4j.info(new WrongFormulaException());
            return -1234567890;
        }
    }

    /**
     * Method multiply
     * <p>  1). Fall into method factor.</p>
     * <p>  2). After method factor back and read format user Text until it`s
     * end.</p>
     * <p>3). If position in tokens array  equals "*" or "/" do same
     * operation</p>
     *
     * @return first <p>Multiply or divide numbers</p>
     */
    public double multiply() {
        double first = factor();

        while (position < tokens.length) {
            String operator = tokens[position];
            if (!operator.equals("*") && !operator.equals("/")) {
                break;
            } else {
                position++;
            }

            double second = factor();
            if (operator.equals("*")) {
                first *= second;
            } else {
                first /= second;
            }
        }
        return first;
    }

    /**
     * Method factor
     * <p>  1). Call recursively descend method: calculate -> multiply -> factor
     * </p>
     * <p>  2). After methods worked, back and check is there any bracket </p>
     * <p>  3). If position in tokens array equals "(" step into and call
     * recursion</p>
     * <p>  4). If position in tokens array equals ")" step out and give away
     * result</p>
     *
     * @return next <p>Result of bracket operation
     * If there were no operations, return the digit </p>
     */
    public double factor() {
        String next = tokens[position];
        double result;
        int count = 0;

        // in case multi brackets, like: 2+((2+2)+2)
        while (tokens[position].equals("(")){
            ++count;
            ++position;
        }
            position-=count;
        if (next.equals("(")) {
            position++;
            result = calculate();
            String closingBracket = null;
            if (position < tokens.length) {
                closingBracket = tokens[position];
            }
            if (closingBracket.equals(")")) {
                position++;
                return result;
            }
        }
        position++;
        return Double.parseDouble(next);
    }
}