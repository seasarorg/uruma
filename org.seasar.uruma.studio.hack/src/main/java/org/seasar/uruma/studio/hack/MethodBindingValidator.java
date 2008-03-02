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

import java.io.FileNotFoundException;
import java.io.IOException;

import org.ashikunep.irenka.dom.CtAnnotationInstance;
import org.ashikunep.irenka.dom.CtAnnotationInstanceElement;
import org.ashikunep.irenka.dom.CtClass;
import org.ashikunep.irenka.dom.CtMethod;
import org.ashikunep.irenka.resource.CtFile;
import org.ashikunep.irenka.toolkit.Filer;
import org.ashikunep.irenka.toolkit.Messager;

import org.seasar.uruma.annotation.EventListener;

/**
 * メソッド・バインディングと画面定義の整合性を確認するHack
 * 
 * @author snuffkin
 */
public class MethodBindingValidator
{
	// TODO
	// hackは上から順に実行される?
	
    /**
     * Actionクラスに対応する画面定義の存在を確認するHack　Action
     * 
     * @when
     *   action.simpleName =~ ".*Action"
     */
    public void validateAction(CtClass<?> action,
                               Messager messager,
                               Filer filer)
    {
    	String resourceName = HackUtil.getResourceName(action);
        CtFile file = filer.getFile(resourceName);
        
        if (!file.exists()) {
            messager.warn(action, "クラスに対応する画面定義(" + resourceName + ")が存在しません");
            return;
        }
        
        try {
			Id2WidgetMapHolder.createId2WidgetMap(resourceName, file.openInputStream());
		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
    }
    
    /**
     * EventListenerに対応する画面定義の存在を確認するHack　Action
     * 
     * @when
     *   action.simpleName =~ ".*Action"
     *   method.parent = action
     *   public in method.modifiers
     *   annotation in method.annotations
     */
    public void validateEventListener(
    		CtClass<?> action,
    		CtMethod<Void> method,
    		CtAnnotationInstance<? extends EventListener> annotation,
            Messager messager)
    {
    	String resourceName = HackUtil.getResourceName(action);
    	
    	// 画面定義が存在しないケースはvalidateActionで検出しているため、無視する
    	if (!Id2WidgetMapHolder.exists(resourceName)) {
    		return;
    	}
    	
    	// EventListenerにidを指定した場合はその値をidとし、
    	// idを指定していない場合はメソッド名をidとする
    	CtAnnotationInstanceElement<?> element
    	    = annotation.getElement("id");
    	String id;
    	if (element != null) {
    		// TODO
    		id = element.getValue().toString().replaceAll("\"", "");
    	} else {
    		id = method.getSimpleName();
    	}

    	// 対応するidの存在チェック
    	if (!Id2WidgetMapHolder.exists(resourceName, id)) {
            messager.warn(annotation, "メソッドに対応するid(" + id + ")が画面定義(" + resourceName + ")に存在しません。");
    	}
    }
}
