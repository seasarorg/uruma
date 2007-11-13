/*
 * Copyright 2004-2007 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.uruma.viewer;

import java.io.Serializable;
import java.util.Comparator;

import org.seasar.framework.util.StringUtil;

/**
 * 文字列と数値による比較を行う {@link Comparator} です。<br />
 * 
 * @author y-komori
 */
public class StringAndNumberComparator implements Comparator<Object>,
        Serializable {

    private static final long serialVersionUID = 12087223297593031L;

    /*
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    public int compare(final Object o1, final Object o2) {
        if ((o1 instanceof String) && (o2 instanceof String)) {
            String s1 = (String) o1;
            String s2 = (String) o2;
            if (StringUtil.isNumber(s1) && StringUtil.isNumber(s2)) {
                return Long.valueOf(s1).compareTo(Long.valueOf(s2));
            } else {
                return s1.compareTo(s2);
            }
        } else {
            return 0;
        }
    }
}
