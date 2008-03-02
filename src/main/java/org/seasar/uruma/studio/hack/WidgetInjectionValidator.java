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
package org.seasar.uruma.studio.hack;

import org.ashikunep.irenka.dom.CtClass;
import org.ashikunep.irenka.dom.CtField;
import org.ashikunep.irenka.toolkit.Filer;
import org.ashikunep.irenka.toolkit.Messager;

import org.eclipse.swt.widgets.Widget;

/**
 * ウィジット・インジェクションと画面定義の整合性を確認するHack
 *  
 * @author snuffkin
 */
public class WidgetInjectionValidator {

    /**
     * ウィジット・インジェクションに対応する画面定義の存在を確認するHack　Action
     * 
     * @when
     *   action.simpleName =~ ".*Action"
     *   field.parent = action
     */
    public void validateWidgetInjection(
    		CtClass<?> action,
    		CtField<? extends Widget> field,
            Messager messager,
            Filer filer)
    {
    	String resourceName = HackUtil.getResourceName(action);
    	String id = field.getSimpleName();
    	String type = field.getType().getName();
    	int index = type.lastIndexOf(".");
    	if (index > 0) {
    		type = type.substring(index + 1);
    	}
    	
    	// 画面定義が存在しないケースはvalidateActionで検出しているため、無視する
    	if (!Id2WidgetMapHolder.exists(resourceName)) {
    		return;
    	}
    	
    	// 対応するidの存在チェック
    	String widget = Id2WidgetMapHolder.getWidgetName(resourceName, id);
    	if (widget == null) {
            messager.warn(field, "フィールドに対応するid(" + id + ")が画面定義(" + resourceName + ")に存在しません。");
            return;
    	}
    	
    	// widgetの型チェック
    	if (!type.equalsIgnoreCase(widget)) {
        	messager.warn(field, "フィールドに対応するid(" + id + ")の画面定義の型は(" + widget +")です。");
    	}
    }
}
