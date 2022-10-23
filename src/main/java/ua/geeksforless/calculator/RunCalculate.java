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

import java.util.Scanner;

/**
 * The RunCalculate for demonstration of work Calculator
 *
 * @author Pavel Hnelytsia
 * @version 1.0
 * @since 20.10.2022
 */
public class RunCalculate {
    private final static Logger log4j = Logger.getLogger(Calculator.class);

    /**
     * Method main entry point into a program
     *
     * @param args defolt main
     */
    public static void main(String[] args) {
        log4j.info("Hi, you can write some formula for calculate.");
        Scanner input = new Scanner(System.in);
        String userText = input.nextLine();
        Calculator calc = new Calculator(userText);
        UserTextFormatter userTF = new UserTextFormatter();
        Negative neg = new Negative();

        log4j.info(userText + " = " + calc.calculate() +
                "\n// count of User numbers: " +
                neg.amountOfNumbers(userTF.userTextFormatter(userText)));

        /*
        Recommend go to the Test class with ready Example of Formula
        With Correct versions of Formula and versions with  Mistakes
        */
    }
}

