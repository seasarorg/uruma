/*
 * Copyright 2004-2006 the Seasar Foundation and the Others.
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
package org.seasar.jface.layout;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Layout;
import org.seasar.framework.util.StringUtil;
import org.seasar.jface.component.impl.ControlComponent;
import org.seasar.jface.util.PropertyUtil;
import org.seasar.jface.util.SWTUtil;

/**
 * レイアウトとレイアウトデータの生成をサポートするための抽象クラスです。</br>
 * 
 * @author y-komori
 * 
 * @param <LAYOUT_TYPE>
 * @param <LAYOUT_DATA_TYPE>
 */
public abstract class AbstractLayoutSupport<LAYOUT_TYPE extends Layout, LAYOUT_DATA_TYPE>
        implements LayoutSupport<LAYOUT_TYPE, LAYOUT_DATA_TYPE> {

    /*
     * @see org.seasar.jface.layout.LayoutSupport#createLayout(java.lang.String)
     */
    public LAYOUT_TYPE createLayout(final String layoutParam) {
        LAYOUT_TYPE layout = createLayout();
        if (!StringUtil.isEmpty(layoutParam)) {
            Map<String, String> layoutParams = setupLayoutParam(layout,
                    layoutParam);
            setupAdditionalLayoutParam(layout, layoutParams);
        }
        return layout;
    }

    protected void setupLayoutData(final Object layoutData,
            final ControlComponent controlComponent) {
        for (String name : controlComponent.getLayoutDataNames()) {
            String value = controlComponent.getLayoutData(name);
            int constant = SWTUtil.getSWTConstant(value);
            if (constant != SWT.NULL) {
                PropertyUtil.setField(layoutData, name, constant);
            } else {
                PropertyUtil.setField(layoutData, name, value);
            }
        }
    }

    protected Map<String, String> setupLayoutParam(final Layout layout,
            final String params) {
        Map<String, String> layoutParams = new HashMap<String, String>();
        StringTokenizer st = new StringTokenizer(params, ";");
        while (st.hasMoreElements()) {
            String param = st.nextToken();
            int eqPos = param.indexOf("=");
            if (eqPos >= 0) {
                String property = param.substring(0, eqPos).trim();
                String value = param.substring(eqPos + 1).trim();

                int swtConstant = SWTUtil.getSWTConstant(value);
                if (swtConstant != SWT.NONE) {
                    value = Integer.toString(swtConstant);
                }

                PropertyUtil
                        .setField(layout, property, Integer.parseInt(value));
                layoutParams.put(property, value);
            }
        }
        return layoutParams;
    }

    /**
     * <code>createLayout(String layoutParam)</code> メソッドの内部で呼び出されるメソッドです。</br>
     * <p>
     * 本クラスにおける実装では何も行いません。</br> サブクラスで独自にレイアウトパラメータの処理を行いたい場合、オーバーライドしてください。
     * </p>
     * 
     * @param layout
     *            レイアウト
     * @param layoutParams
     *            レイアウトパラメータ
     */
    protected void setupAdditionalLayoutParam(final Layout layout,
            final Map<String, String> layoutParams) {
    }
}
