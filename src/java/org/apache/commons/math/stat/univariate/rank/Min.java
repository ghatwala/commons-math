/*
 * Copyright 2003-2004 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.math.stat.univariate.rank;

import java.io.Serializable;

import org.apache.commons.math.stat.univariate.AbstractStorelessUnivariateStatistic;

/**
 * Returns the minimum of the available values.
 * <p>
 * <ul>
 * <li>The result is <code>NaN</code> iff all values are <code>NaN</code> 
 * (i.e. <code>NaN</code> values have no impact on the value of the statistic).</li>
 * <li>If any of the values equals <code>Double.NEGATIVE_INFINITY</code>, 
 * the result is <code>Double.NEGATIVE_INFINITY.</code></li>
 * </ul> 
 *
 * @version $Revision: 1.19 $ $Date: 2004/06/23 16:26:17 $
 */
public class Min extends AbstractStorelessUnivariateStatistic implements Serializable {

    /** Serializable version identifier */
    static final long serialVersionUID = -2941995784909003131L;  
      
    /**Number of values that have been added */
    private long n = 0;
    
    /**Current value of the statistic */
    private double value = Double.NaN;

    /**
     * @see org.apache.commons.math.stat.univariate.StorelessUnivariateStatistic#increment(double)
     */
    public void increment(final double d) {
        if (d < value || Double.isNaN(value)) {
            value = d;
        }
        n++;
    }

    /**
     * @see org.apache.commons.math.stat.univariate.StorelessUnivariateStatistic#clear()
     */
    public void clear() {
        value = Double.NaN;
        n = 0;
    }

    /**
     * @see org.apache.commons.math.stat.univariate.StorelessUnivariateStatistic#getResult()
     */
    public double getResult() {
        return value;
    }

    /**
     * @see org.apache.commons.math.stat.univariate.StorelessUnivariateStatistic#getN()
     */
    public double getN() {
        return n;
    }
    
    /**
     * Returns the minimum of the available values.
     * 
     * @see org.apache.commons.math.stat.univariate.UnivariateStatistic#evaluate(double[], int, int)
     */
    public double evaluate(final double[] values,final int begin, final int length) {
        double min = Double.NaN;
        if (test(values, begin, length)) {
            min = values[begin];
            for (int i = begin; i < begin + length; i++) {
                min = (min < values[i]) ? min : values[i];
            }
        }
        return min;
    }
}