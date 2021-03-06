/*
 * Copyright 2004-2008 the Seasar Foundation and the Others.
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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.uruma.binding.value.ValueBinder;
import org.seasar.uruma.component.UIComponent;
import org.seasar.uruma.core.UrumaConstants;
import org.seasar.uruma.core.UrumaMessageCodes;
import org.seasar.uruma.exception.BindingException;
import org.seasar.uruma.log.UrumaLogger;
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
    private UrumaLogger logger = UrumaLogger.getLogger(getClass());

    private Class<WIDGET_TYPE> widgetType;

    protected static final String IMPORT_VALUE = "[ImportValue]";

    protected static final String EXPORT_VALUE = "[ExportValue]";

    protected static final String IMPORT_SELECTION = "[ImportSelection]";

    protected static final String EXPORT_SELECTION = "[ExportSelection]";

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
            final PropertyDesc propDesc, final UIComponent uiComp) {
        doImportValue(getWidgetType().cast(widget), formObj, propDesc, uiComp);
    }

    /*
     * @see org.seasar.uruma.binding.value.ValueBinder#exportValue(java.lang.Object,
     *      java.lang.Object, org.seasar.framework.beans.PropertyDesc)
     */
    public void exportValue(final Object widget, final Object formObj,
            final PropertyDesc propDesc, final UIComponent uiComp) {
        doExportValue(getWidgetType().cast(widget), formObj, propDesc, uiComp);
    }

    /*
     * @see org.seasar.uruma.binding.value.ValueBinder#importSelection(java.lang.Object,
     *      java.lang.Object, org.seasar.framework.beans.PropertyDesc)
     */
    public void importSelection(final Object widget, final Object formObj,
            final PropertyDesc propDesc, final UIComponent uiComp) {
        doImportSelection(getWidgetType().cast(widget), formObj, propDesc,
                uiComp);
    }

    /*
     * @see org.seasar.uruma.binding.value.ValueBinder#exportSelection(java.lang.Object,
     *      java.lang.Object, org.seasar.framework.beans.PropertyDesc)
     */
    public void exportSelection(final Object widget, final Object formObj,
            final PropertyDesc propDesc, final UIComponent uiComp) {
        doExportSelection(getWidgetType().cast(widget), formObj, propDesc,
                uiComp);
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
     * @param uiComp
     *            コンポーネント
     */
    protected void doImportValue(final WIDGET_TYPE widget,
            final Object formObj, final PropertyDesc propDesc,
            final UIComponent uiComp) {

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
     * @param uiComp
     *            コンポーネント
     */
    protected void doExportValue(final WIDGET_TYPE widget,
            final Object formObj, final PropertyDesc propDesc,
            final UIComponent uiComp) {
        if (widget instanceof StructuredViewer) {
            StructuredViewer viewer = StructuredViewer.class.cast(widget);

            Class<?> formFieldType = propDesc.getField().getType();
            Object contents = propDesc.getValue(formObj);
            IContentProvider contentProvider = viewer.getContentProvider();
            IBaseLabelProvider labelProvider = viewer.getLabelProvider();

            if (contentProvider != null
                    && contentProvider instanceof ContentsHolder) {
                ContentsHolder holder = (ContentsHolder) contentProvider;
                if (formFieldType.isArray()) {
                    if (contents != null) {
                        holder.setContents((Object[]) contents);
                    } else {
                        holder.setContents(new Object[] {});
                    }
                    setClassToProvider(labelProvider, formFieldType
                            .getComponentType());
                } else if (List.class.isAssignableFrom(formFieldType)) {
                    List<?> listContents = (List<?>) contents;

                    if ((listContents != null) && (listContents.size() > 0)) {
                        holder.setContents(listContents);

                        Object content = listContents.get(0);
                        setClassToProvider(labelProvider, content.getClass());
                    } else {
                        holder.setContents(new ArrayList<Object>());
                        setClassToProvider(labelProvider, Object.class);
                    }
                } else {
                    if (contents != null) {
                        holder.setContents(new Object[] { contents });
                        setClassToProvider(labelProvider, contents.getClass());
                    } else {
                        holder.setContents(new Object[] {});
                        setClassToProvider(labelProvider, Object.class);
                    }
                }

                logBinding(EXPORT_VALUE, formObj, propDesc, widget, null,
                        contents);

                viewer.setInput(contents);
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
     * @param uiComp
     *            コンポーネント
     * @throws BindingException
     *             ビューアで選択させれているオブジェクトの型とプロパティの型が一致しなかった場合
     */
    protected void doImportSelection(final WIDGET_TYPE widget,
            final Object formObj, final PropertyDesc propDesc,
            final UIComponent uiComp) {
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
                    Object content = Array.newInstance(propertyType
                            .getComponentType(), selectedArray.length);
                    System.arraycopy(selectedArray, 0, content, 0,
                            selectedArray.length);

                    logBinding(IMPORT_SELECTION, widget, null, formObj,
                            propDesc, content);
                    propDesc.setValue(formObj, content);
                } else if (propertyType.isAssignableFrom(List.class)) {
                    List<?> list = selection.toList();
                    logBinding(IMPORT_SELECTION, widget, null, formObj,
                            propDesc, list);
                    propDesc.setValue(formObj, list);
                } else if (propertyType.isAssignableFrom(firstElement
                        .getClass())) {
                    logBinding(IMPORT_SELECTION, widget, null, formObj,
                            propDesc, firstElement);
                    propDesc.setValue(formObj, firstElement);
                } else {
                    throw new BindingException(
                            UrumaMessageCodes.CLASS_NOT_MUTCH, formObj
                                    .getClass().getName(), propDesc
                                    .getPropertyName());
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
     * @param uiComp
     *            コンポーネント
     */
    protected void doExportSelection(final WIDGET_TYPE widget,
            final Object formObj, final PropertyDesc propDesc,
            final UIComponent uiComp) {
        if (widget instanceof Viewer) {
            Viewer viewer = Viewer.class.cast(widget);
            Object selection = propDesc.getValue(formObj);
            if (selection != null) {
                logBinding(EXPORT_SELECTION, formObj, propDesc, viewer, null,
                        selection);

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

    /**
     * バインディングの状況をログ出力します。<br />
     * 
     * @param command
     *            コマンド文字列
     * @param srcObj
     *            バインド元オブジェクト
     * @param srcProp
     *            バインド元プロパティ
     * @param destObj
     *            バインド先オブジェクト
     * @param destProp
     *            バインド先オブジェクト
     * @param value
     *            値
     */
    protected void logBinding(final String command, final Object srcObj,
            final PropertyDesc srcProp, final Object destObj,
            final PropertyDesc destProp, final Object value) {
        if (logger.isInfoEnabled()) {
            String srcName = UrumaConstants.NULL_STRING;
            if (srcProp != null) {
                srcName = srcProp.getPropertyName();
            }
            String destName = UrumaConstants.NULL_STRING;
            if (destProp != null) {
                destName = destProp.getPropertyName();
            }
            logger.log(UrumaMessageCodes.DO_BINDING, command, UrumaLogger
                    .getObjectDescription(srcObj), srcName, UrumaLogger
                    .getObjectDescription(destObj), destName, value);
        }
    }
}
