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

import java.util.ArrayList;

/**
 * The UserTextFormatter class take userText and format it
 *
 * @author Pavel Hnelytsia
 * @version 1.0
 * @since 20.10.2022
 */
public class UserTextFormatter {

    /**
     * Method splitter takes user Text and prepare it for split
     * <p>Helps to create not single Number from Text, for example:</p>
     * <p>UserText 123+123 -> [1,2,3,+,1,2,3] , after method work =[123,+,
     * 123] </p>
     *
     * @param userString user input Text
     *
     * @return sb <p>String ready for split</p>
     */
    public String[] splitter(String userString) {
        String[] tokens = userString.split("");
        StringBuilder sb = new StringBuilder();

        for (String e : tokens) {
            if (e.equals("(")) {
                sb.append("_");
                sb.append(e);
                sb.append("_");
            }
            if (e.equals(")")) {
                sb.append("_");
                sb.append(e);
                sb.append("_");
            }
            if (UserTextFormatter.isDigit(e)) {
                sb.append(e);
            }
            if (e.equals("+")) {
                sb.append("_");
                sb.append(e);
                sb.append("_");
            }
            if (e.equals("-")) {
                sb.append("_");
                sb.append(e);
                sb.append("_");
            }
            if (e.equals("/")) {
                sb.append("_");
                sb.append(e);
                sb.append("_");
            }
            if (e.equals("*")) {
                sb.append("_");
                sb.append(e);
                sb.append("_");
            }
        }
        String fromSBtoStr = sb.toString();
        return fromSBtoStr.split("_{1,2}");
    }

    /**
     * Method isDigit helps build String ready for split, using in loop when
     * need check is element is Digit
     * <p>Helps to create Fractional number, for example:</p>
     * <p>UserText 1.3+1.3 -> [1,.,3,+,1,.,3] , after method work =[1.3,+,1.3]
     *
     * @param isElementDigit element of loop
     *
     * @return boolean
     */
    public static boolean isDigit(String isElementDigit) {
        return switch (isElementDigit) {
            case "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "." -> true;
            default -> false;
        };
    }

    /**
     * Method userTextFormatter take user Text, make formatting and give
     * back array of String.
     * <p>Method collects the result of all formatting methods</p>
     *
     * @param userText user Text
     *
     * @return array of elements ready to calculate
     */
    public String[] userTextFormatter(String userText) {
        String[] arrays = splitter(userText);
        Negative negative = new Negative();
        arrays = negative.getArrayOfFormulaWithNegativeDigits(arrays);
        ArrayList<String> arrayList = negative.getReadyArray(arrays);
        return arrayList.toArray(new String[0]);
    }

    /**
     * Method isBracketCorrect take user Text, make formatting and give
     * back array of String
     *
     * @param userText user Text with possibly bracket
     *
     * @return boolean of user Text have correct brackets input
     */
    public boolean isBracketCorrect(String userText) {
        Negative obj = new Negative();
        String[] userTextSplit = userText.split("");
        int openBracket = 0;
        int closeBracket = 0;
        boolean isOpenBracketEqualsCloseBracket = false;
        boolean isCorrect = true; // value to checking contract case
        // loop to find contract case
        for (int i = 0; i < userTextSplit.length; i++) {
            try {
                if (userTextSplit[0].equals(")") &
                        userTextSplit[userTextSplit.length - 1].equals("(")
                ) {
                    isCorrect = false;
                }
                if (isDigit(userTextSplit[i]) &
                        userTextSplit[++i].equals("(") &
                        obj.isOperator(userTextSplit[++i])
                ) {
                    isCorrect = false;
                }
                --i;
                --i;
                if (userTextSplit[i].equals("(") &
                        userTextSplit[++i].equals(")")
                ) {
                    isCorrect = false;
                }
                --i;
            } catch (RuntimeException ignored) {
                --i;
            }
        }
        // loop to found is count of openBracket equals count of closeBracket
        for (String s : userTextSplit) {
            if (s.equals("(")) {
                openBracket++;
            }
            if (s.equals(")")) {
                closeBracket++;
            }
        }
        if (openBracket == closeBracket) {
            isOpenBracketEqualsCloseBracket = true;
        }
        // if to conditions are true, user text is Correct
        return isOpenBracketEqualsCloseBracket && isCorrect;
    }
}
