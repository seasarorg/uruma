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
package org.seasar.uruma.binding.value.binder;

import java.util.List;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.uruma.binding.value.ValueBinder;
import org.seasar.uruma.exception.BindingException;
import org.seasar.uruma.util.AssertionUtil;
import org.seasar.uruma.viewer.ContentsHolder;
import org.seasar.uruma.viewer.TargetClassHoldingProvider;

/**
 * {@link ValueBinder} のための基底クラスです。<br />
 * 
 * @param <WIDGET_TYPE>
 *            対応するウィジットの型
 * @author y-komori
 */
public abstract class AbstractValueBinder<WIDGET_TYPE> implements ValueBinder {
    private Class<WIDGET_TYPE> widgetType;

    /**
     * {@link AbstractValueBinder} を構築します。<br />
     * 
     * @param widgetType
     *            ウィジットの型
     */
    public AbstractValueBinder(final Class<WIDGET_TYPE> widgetType) {
        AssertionUtil.assertNotNull("widgetType", widgetType);
        this.widgetType = widgetType;
    }

    /*
     * @see org.seasar.uruma.binding.value.ValueBinder#importValue(java.lang.Object,
     *      java.lang.Object, org.seasar.framework.beans.PropertyDesc)
     */
    public void importValue(final Object widget, final Object formObj,
            final PropertyDesc propDesc) {
        doImportValue(getWidgetType().cast(widget), formObj, propDesc);
    }

    /*
     * @see org.seasar.uruma.binding.value.ValueBinder#exportValue(java.lang.Object,
     *      java.lang.Object, org.seasar.framework.beans.PropertyDesc)
     */
    public void exportValue(final Object widget, final Object formObj,
            final PropertyDesc propDesc) {
        doExportValue(getWidgetType().cast(widget), formObj, propDesc);
    }

    /*
     * @see org.seasar.uruma.binding.value.ValueBinder#importSelection(java.lang.Object,
     *      java.lang.Object, org.seasar.framework.beans.PropertyDesc)
     */
    public void importSelection(final Object widget, final Object formObj,
            final PropertyDesc propDesc) {
        doImportSelection(getWidgetType().cast(widget), formObj, propDesc);
    }

    /*
     * @see org.seasar.uruma.binding.value.ValueBinder#exportSelection(java.lang.Object,
     *      java.lang.Object, org.seasar.framework.beans.PropertyDesc)
     */
    public void exportSelection(final Object widget, final Object formObj,
            final PropertyDesc propDesc) {
        doExportSelection(getWidgetType().cast(widget), formObj, propDesc);
    }

    /*
     * @see org.seasar.uruma.binding.value.ValueBinder#getWidgetType()
     */
    public Class<WIDGET_TYPE> getWidgetType() {
        return this.widgetType;
    }

    /**
     * ウィジットの値をフォームへ設定します。<br />
     * 本メソッドをサブクラスでオーバーライドしてください。<br />
     * デフォルトでは何も行いません。
     * 
     * @param widget
     *            ウィジット側オブジェクト
     * @param formObj
     *            フォーム側オブジェクト
     * @param propDesc
     *            フォーム側のプロパティを表す {@link PropertyDesc} オブジェクト
     */
    protected void doImportValue(final WIDGET_TYPE widget,
            final Object formObj, final PropertyDesc propDesc) {

    }

    /**
     * フォームの値をウィジットへ設定します。<br />
     * デフォルトでは、 <code>widget</code> が {@link StructuredViewer}
     * のサブクラスかつコンテンツプロバイダが {@link ContentsHolder} の実装クラスである場合に、
     * <code>propDesc</code> の持つ値をコンテンツプロバイダへ設定します。<br />
     * デフォルト処理をカスタマイズしたい場合は、サブクラスでオーバーライドしてください。<br />
     * 
     * @param widget
     *            ウィジット側オブジェクト
     * @param formObj
     *            フォーム側オブジェクト
     * @param propDesc
     *            フォーム側のプロパティを表す {@link PropertyDesc} オブジェクト
     */
    protected void doExportValue(final WIDGET_TYPE widget,
            final Object formObj, final PropertyDesc propDesc) {
        if (widget instanceof StructuredViewer) {
            System.out.println("Export!!");

            StructuredViewer viewer = StructuredViewer.class.cast(widget);

            Class<?> formFieldType = propDesc.getField().getType();
            Object contents = propDesc.getValue(formObj);
            IContentProvider contentProvider = viewer.getContentProvider();
            IBaseLabelProvider labelProvider = viewer.getLabelProvider();

            if (contents != null) {
                if (contentProvider != null
                        && contentProvider instanceof ContentsHolder) {
                    ContentsHolder holder = (ContentsHolder) contentProvider;
                    if (formFieldType.isArray()) {
                        holder.setContents((Object[]) contents);
                        setClassToProvider(labelProvider, formFieldType
                                .getComponentType());
                    } else if (List.class.isAssignableFrom(formFieldType)) {
                        List<?> listContents = (List<?>) contents;

                        if (listContents.size() > 0) {
                            holder.setContents(listContents);

                            Object content = listContents.get(0);
                            setClassToProvider(labelProvider, content
                                    .getClass());
                        }
                    } else {
                        holder.setContents(new Object[] { contents });
                        setClassToProvider(labelProvider, contents.getClass());
                    }

                    viewer.setInput(contents);
                }
            }
        }

    }

    /**
     * ウィジットで選択されているオブジェクトをフォームへ設定します。<br />
     * デフォルトでは、 <code>widget</code> が {@link Viewer} のサブクラスである場合に
     * <code>propDesc</code> の表すプロパティにビューアから取得した選択中オブジェクトを設定します。<br />
     * デフォルト処理をカスタマイズしたい場合は、サブクラスでオーバーライドしてください。<br />
     * 
     * @param widget
     *            ウィジット側オブジェクト
     * @param formObj
     *            フォーム側オブジェクト
     * @param propDesc
     *            フォーム側のプロパティを表す {@link PropertyDesc} オブジェクト
     * @throws BindingException
     *             ビューアで選択させれているオブジェクトの型とプロパティの型が一致しなかった場合
     */
    protected void doImportSelection(final WIDGET_TYPE widget,
            final Object formObj, final PropertyDesc propDesc) {
        if (widget instanceof Viewer) {
            Viewer viewer = Viewer.class.cast(widget);

            IStructuredSelection selection = (IStructuredSelection) viewer
                    .getSelection();
            int size = selection.size();
            if (size > 0) {
                Class<?> propertyType = propDesc.getPropertyType();
                Object firstElement = selection.getFirstElement();
                if (propertyType.isArray()) {
                    Object[] selectedArray = selection.toArray();
                    if (propertyType.isAssignableFrom(selectedArray.getClass())) {
                        propDesc.setValue(formObj, selectedArray);
                    } else {
                        throw new BindingException(
                                BindingException.CLASS_NOT_MUTCH, null, formObj
                                        .getClass(), propDesc.getField());
                    }
                } else if (propertyType.isAssignableFrom(List.class)) {
                    propDesc.setValue(formObj, selection.toList());
                } else if (propertyType.isAssignableFrom(firstElement
                        .getClass())) {
                    propDesc.setValue(formObj, firstElement);
                } else {
                    throw new BindingException(
                            BindingException.CLASS_NOT_MUTCH, null, formObj
                                    .getClass(), propDesc.getField());
                }
            }
        }
    }

    /**
     * フォームの持つオブジェクトをウィジットの選択状態として設定します。<br />
     * デフォルトでは、 <code>widget</code> が {@link Viewer} のサブクラスである場合に
     * <code>propDesc</code> の持つ値を {@link StructuredSelection}
     * にラップしてビューアに設定します。<br />
     * デフォルト処理をカスタマイズしたい場合は、サブクラスでオーバーライドしてください。<br />
     * 
     * @param widget
     *            ウィジット側オブジェクト
     * @param formObj
     *            フォーム側オブジェクト
     * @param propDesc
     *            フォーム側のプロパティを表す {@link PropertyDesc} オブジェクト
     */
    protected void doExportSelection(final WIDGET_TYPE widget,
            final Object formObj, final PropertyDesc propDesc) {
        if (widget instanceof Viewer) {
            Viewer viewer = Viewer.class.cast(widget);
            Object selection = propDesc.getValue(formObj);
            if (selection != null) {
                viewer.setSelection(new StructuredSelection(selection), true);
            }
        }
    }

    protected void setClassToProvider(final IBaseLabelProvider provider,
            final Class<?> clazz) {
        if ((provider != null)
                && (provider instanceof TargetClassHoldingProvider)) {
            TargetClassHoldingProvider.class.cast(provider).setTargetClass(
                    clazz);
        }
    }
}
