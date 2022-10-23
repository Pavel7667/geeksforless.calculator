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

/**
 * The class WrongFormulaException if User wrote wrong formula throw this
 * Exception with description
 *
 * @author Pavel Hnelytsia
 * @version 1.0
 * @since 20.10.2022
 */

public class WrongFormulaException extends Throwable {

    /**
     * Method toString
     *
     * @return String warning description
     */
    @Override
    public String toString() {
        return """
                *******************************************************\s
                You entered the wrong form. Please check availability:\s
                1). Misplaced mathematical operators\s
                2). Symbol or letter\s
                3). Wrong bracket\s
                4). If entered form wrong, calculate show result like:\s
                -1.23456789E9\s
                5). In this case your form will not put into DB\s
                *******************************************************\s
                """;
    }
}






