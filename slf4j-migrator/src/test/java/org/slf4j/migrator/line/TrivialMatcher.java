/**
 * Copyright (c) 2004-2011 QOS.ch
 * All rights reserved.
 *
 * Permission is hereby granted, free  of charge, to any person obtaining
 * a  copy  of this  software  and  associated  documentation files  (the
 * "Software"), to  deal in  the Software without  restriction, including
 * without limitation  the rights to  use, copy, modify,  merge, publish,
 * distribute,  sublicense, and/or sell  copies of  the Software,  and to
 * permit persons to whom the Software  is furnished to do so, subject to
 * the following conditions:
 *
 * The  above  copyright  notice  and  this permission  notice  shall  be
 * included in all copies or substantial portions of the Software.
 *
 * THE  SOFTWARE IS  PROVIDED  "AS  IS", WITHOUT  WARRANTY  OF ANY  KIND,
 * EXPRESS OR  IMPLIED, INCLUDING  BUT NOT LIMITED  TO THE  WARRANTIES OF
 * MERCHANTABILITY,    FITNESS    FOR    A   PARTICULAR    PURPOSE    AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE,  ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.slf4j.migrator.line;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;

import org.slf4j.migrator.line.ConversionRule;
import org.slf4j.migrator.line.MultiGroupConversionRule;
import org.slf4j.migrator.line.RuleSet;
import org.slf4j.migrator.line.SingleConversionRule;

class TrivialMatcher implements RuleSet {

    private final ArrayList<ConversionRule> conversionRuleList;

    public TrivialMatcher() {
        // simple rule no capturing group is defined, we use default capturing group which is group zero
        SingleConversionRule cr = new SingleConversionRule(Pattern.compile("import org.slf4j.converter"), "simple replacement with an unique capturing group");

        // we define 4 different capturing groups
        MultiGroupConversionRule cr1 = new MultiGroupConversionRule(Pattern.compile("(first group)( second group)( third group)( 4th group)"));
        // group zero is ignored during treatment
        // replacement for the first
        cr1.addReplacement(1, "1st group");
        // no replacement for the second group it will remain the same
        // empty string for the third group it will be deleted
        cr1.addReplacement(3, "");
        // no replacement for the third group it will remain the same

        conversionRuleList = new ArrayList<>();
        conversionRuleList.add(cr);
        conversionRuleList.add(cr1);
    }

    public Iterator<ConversionRule> iterator() {
        return conversionRuleList.iterator();
    }

}
