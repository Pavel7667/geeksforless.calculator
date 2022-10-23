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

import java.util.ArrayList;
import java.util.List;

/**
 * The Negative class take formatted userText and continue formatting it,
 * taking into account the minus conditions
 *
 * @author Pavel Hnelytsia
 * @version 1.0
 * @since 20.10.2022
 */

public class Negative {

    private final static Logger log4j =
            Logger.getLogger(Negative.class);


    /**
     * Method isOperator checking is element of loop is mathematical operator
     *
     * @param element of loop
     *
     * @return boolean
     */
    public boolean isOperator(String element) {
        return switch (element) {
            case "+", "-", "/", "*" -> true;
            default -> false;
        };
    }

    /**
     * Method isBracket checking is element of loop is bracket
     *
     * @param element of loop
     *
     * @return boolean
     */
    public boolean isBracket(String element) {
        return switch (element) {
            case "(", ")" -> true;
            default -> false;
        };
    }


    /**
     * Method isNumber checking is element of loop is Number
     *
     * @param element of loop
     *
     * @return boolean
     */
    public static boolean isNumber(String element) {
        try {
            Double.parseDouble(element);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Method getArrayOfFormulaWithNegativeDigits take array of element from
     * user Text and if condition is true, makes element Negative
     * <p>Also check is element of position 1 is ")" and if true set special
     * result </p>
     *
     * @param afterSplit user input Text after Split
     *
     * @return array with Negative element
     */
    public String[] getArrayOfFormulaWithNegativeDigits(String[] afterSplit) {
        // check invalid situation starts with "("
        if (afterSplit.length > 1 && afterSplit[1].equals(")")) {
            String[] strings = new String[1];
            strings[0] = "-1234567890";
            return strings;
        }
        // check invalid situation when Number starts with dote =  .1 or .2
        for (String elem : afterSplit) {
            String[] invalidDoteNumber = elem.split("");
            if (invalidDoteNumber[0].equals(".")) {
                String[] strings = new String[1];
                strings[0] = "-1234567890";
                log4j.info(new WrongFormulaException());
                return strings;
            }
        }
        // format in case "+-" "*-" "/-" "--"
        for (int i = 0; i < afterSplit.length; i++) {
            if (afterSplit[i].indexOf(0) == '.') {
                String[] strings = new String[1];
                strings[0] = "-1234567890";
                return strings;
            }
            try {
                ++i;
                if (isOperator(afterSplit[--i]) &&
                        afterSplit[++i].equals("-") &&
                        isNumber(afterSplit[++i])

                ) {
                    afterSplit[i] = ("-" + afterSplit[i]);
                }
            } catch (RuntimeException ignored) {

            }
        }
        // format in case  "(-element+element)"
        for (int i = 0; i < afterSplit.length; i++) {
            try {
                if (isBracket(afterSplit[i]) &&
                        afterSplit[++i].equals("-") &&
                        isNumber(afterSplit[++i])
                ) {
                    afterSplit[i] = ("-" + afterSplit[i]);
                }
            } catch (RuntimeException ignored) {

            }
        }
        // format in case first "-"
        for (int i = 0; i < afterSplit.length; i++) {
            //break if first element "("
            if (afterSplit[i].equals("(")) {
                break;
            }
            // zero counter to find first number in case: ((
            // (-element+element)))
            int zero = 0;
            if (isNumber(afterSplit[i])) {
                ++zero;
            }
            //break if first element "Number"
            if (isNumber(afterSplit[0])) {
                break;
            }
            // finally case when first element should be Negative
            if (zero == 1 && afterSplit[--i].equals("-")
            ) {
                afterSplit[++i] = ("-" + afterSplit[i]);
                break;
            }
        }
        return afterSplit;
    }

    /**
     * Method getReadyArray take array of element after
     * getArrayOfFormulaWithNegativeDigits
     * and delete unnecessary minus
     *
     * @param afterNegativeFormat text after all element with condition set
     *                            into Negative
     *
     * @return array without  no longer needed element "-"
     */
    public ArrayList<String> getReadyArray(String[] afterNegativeFormat) {
        ArrayList<String> arrayList =
                new ArrayList<>(List.of(afterNegativeFormat));
        // format in case "+-" "*-" "/-" "--"
        for (int i = 0; i < arrayList.size(); i++) {
            try {
                ++i;
                if (isOperator(arrayList.get(--i)) &&
                        arrayList.get(++i).equals("-") &&
                        Negative.isNumber(arrayList.get(++i))
                ) {
                    arrayList.remove(--i);
                }
            } catch (RuntimeException ignored) {}
        }
        // format in case  "(-33+1)"
        for (int i = 0; i < arrayList.size(); i++) {
            try {
                if (isBracket(arrayList.get(i)) &&
                        arrayList.get(++i).equals("-") &&
                        Negative.isNumber(arrayList.get(++i))) {
                    arrayList.remove(--i);
                }
            } catch (RuntimeException ignored) {}
        }
        // format in case  first "-"
        for (int i = 0; i < arrayList.size(); i++) {
            //break if first element "(" and remove empty element
            if (arrayList.get(i).equals("(")) {
                arrayList.remove(--i);
                break;
            }
            // zero counter to find first number in case: ((
            // (-element+element)))
            int zero = 0;
            if (Negative.isNumber(arrayList.get(i))) {
                ++zero;
            }
            //break if first element "Number"
            if (isNumber(afterNegativeFormat[0])) {
                break;
            }
            // in case when first element should be Negative delete previous "-"
            if (zero == 1 && arrayList.get(--i).equals("-")) {
                arrayList.remove(--i);
                arrayList.remove(i);
                break;
            }
        }
        return arrayList;
    }


    /**
     * Method amountOfNumbers additional task if items with DB are not completed
     *
     * @param arrayOfFormula formatted user Text
     *
     * @return count of User input Numbers
     */
    public int amountOfNumbers(String[] arrayOfFormula) {
        int count = 0;
        for (String s : arrayOfFormula) {
            if (Negative.isNumber(s)) {
                count++;
            }
        }
        return count;
    }
}
