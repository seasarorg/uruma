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
package org.seasar.uruma.binding.value.command;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.util.StringUtil;
import org.seasar.uruma.binding.value.BindingCommand;
import org.seasar.uruma.binding.value.ValueBinder;
import org.seasar.uruma.binding.value.ValueBinderFactory;
import org.seasar.uruma.exception.BindingException;

/**
 * {@link BindingCommand} の基底クラスです。<br />
 * 
 * @param <ANNOTATION_CLASS>
 *            対応するアノテーション
 * @author y-komori
 */
public abstract class AbstractBindingCommand<ANNOTATION_CLASS extends Annotation>
        implements BindingCommand {

    /*
     * @see org.seasar.uruma.binding.value.BindingCommand#doBind(java.lang.Object,
     *      java.lang.Object, org.seasar.framework.beans.PropertyDesc)
     */
    public void doBind(final Object widget, final Object formObj,
            final PropertyDesc propDesc) {
        doBind(getValueBinder(widget), widget, formObj, propDesc);
    }

    /*
     * @see org.seasar.uruma.binding.value.BindingCommand#getId(java.lang.reflect.Field)
     */
    public String getId(final Field field) {
        ANNOTATION_CLASS anno = getAnnotation(field);
        String id = getId(anno);
        return StringUtil.isEmpty(id) ? field.getName() : id;
    }

    private ValueBinder getValueBinder(final Object widget) {
        ValueBinder valueBinder = ValueBinderFactory.getValueBinder(widget
                .getClass());
        if (valueBinder != null) {
            return valueBinder;
        } else {
            throw new BindingException(BindingException.WIDGET_NOT_SUPPORTED,
                    null, null, null);
        }
    }

    /**
     * バインド処理を実行します。<br />
     * 本メソッドは、サブクラスで実装してください。<br />
     * 引数で渡された {@link ValueBinder} オブジェクトの適切なメソッドを呼び出して、バインド処理を実行してください。<br />
     * 
     * @param binder
     *            {@link ValueBinder} オブジェクト
     * @param widget
     *            バインド対象のウィジット側オブジェクト
     * @param formObj
     *            バインド対象のフォーム側オブジェクト
     * @param propDesc
     *            フォーム側のプロパティを表す {@link PropertyDesc} オブジェクト
     */
    protected abstract void doBind(ValueBinder binder, Object widget,
            Object formObj, PropertyDesc propDesc);

    /**
     * アノテーションから id を取り出します。<br />
     * 本メソッドはサブクラスで実装してください。<br />
     * 引数で渡されたアノテーションから id を取得して返してください。<br />
     * 
     * @param annotation
     *            アノテーション
     * @return id
     */
    protected abstract String getId(ANNOTATION_CLASS annotation);

    /**
     * フィールドからアノテーションを取り出します。<br />
     * 本メソッドはサブクラスで実装してください。<br />
     * 引数で渡されたフィールドからアノテーションを取得して返してください。<br />
     * 
     * @param field
     *            アノテーションを取り出す {@link Field} オブジェクト
     * @return アノテーション
     */
    protected abstract ANNOTATION_CLASS getAnnotation(Field field);
}
