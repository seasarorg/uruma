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
package org.seasar.uruma.binding.method;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.seasar.framework.util.MethodUtil;
import org.seasar.uruma.core.UrumaConstants;
import org.seasar.uruma.core.UrumaMessageCodes;
import org.seasar.uruma.log.UrumaLogger;
import org.seasar.uruma.util.AssertionUtil;

/**
 * オブジェクトに対するメソッドコールを実現するためのクラスです。<br />
 * 
 * @author y-komori
 */
public class MethodBinding {
    private static final UrumaLogger logger = UrumaLogger
            .getLogger(MethodBinding.class);

    protected Object target;

    protected Method method;

    protected List<ArgumentsFilter> argumentsFilterList = new ArrayList<ArgumentsFilter>();

    private String description;

    /**
     * {@link MethodBinding} を構築します。<br />
     * 
     * @param target
     *            ターゲットオブジェクト
     * @param method
     *            ターゲットメソッド
     */
    MethodBinding(final Object target, final Method method) {
        AssertionUtil.assertNotNull("target", target);
        AssertionUtil.assertNotNull("method", method);

        this.target = target;
        this.method = method;
        this.description = UrumaLogger.getObjectDescription(target)
                + UrumaConstants.HASH_MARK + method.getName();
    }

    /**
     * 引数なしでメソッドを実行します。<br />
     * メソッド実行前に、
     * {@link MethodBinding#addArgumentsFilter(ArgumentsFilter) addArgumentsFilter()}
     * メソッドで追加された {@link ArgumentsFilter} が適用されます。
     * 
     * @return 戻り値オブジェクト
     */
    public Object invoke() {
        return invoke(null);
    }

    /**
     * 引数を指定してメソッドを実行します。<br />
     * メソッド実行前に、
     * {@link MethodBinding#addArgumentsFilter(ArgumentsFilter) addArgumentsFilter()}
     * メソッドで追加された {@link ArgumentsFilter} が適用されます。
     * 
     * @param args
     *            引数オブジェクトの配列
     * @return 戻り値オブジェクト
     */
    public Object invoke(final Object[] args) {
        if (logger.isInfoEnabled()) {
            logger.log(UrumaMessageCodes.START_METHOD_CALL, UrumaLogger
                    .getObjectDescription(target), method.getName(), args);
        }

        Object[] filteredArgs = args;
        for (ArgumentsFilter filter : argumentsFilterList) {
            filteredArgs = filter.filter(filteredArgs);
        }
        Object result = MethodUtil.invoke(method, target, filteredArgs);

        if (logger.isInfoEnabled()) {
            logger.log(UrumaMessageCodes.END_METHOD_CALL, UrumaLogger
                    .getObjectDescription(target), method.getName(), result);
        }

        return result;
    }

    /**
     * {@link ArgumentsFilter} を追加します。<br />
     * 本メソッドで追加された {@link ArgumentsFilter} は
     * {@link MethodBinding#invoke() invoke()} メソッド呼び出し時に、追加された順に適用されます。
     * 
     * @param argumentsFilter
     *            {@link ArgumentsFilter} オブジェクト
     */
    public void addArgumentsFilter(final ArgumentsFilter argumentsFilter) {
        this.argumentsFilterList.add(argumentsFilter);
    }

    /**
     * {@link Method} オブジェクトを取得します。<br />
     * 
     * @return {@link Method} オブジェクト
     */
    public Method getMethod() {
        return this.method;
    }

    /**
     * ターゲットオブジェクトを取得します。<br />
     * 
     * @return ターゲットオブジェクト
     */
    public Object getTarget() {
        return this.target;
    }

    /*
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return this.description;
    }
}
