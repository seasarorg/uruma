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

import org.ashikunep.irenka.dom.CtAnnotationInstance;
import org.ashikunep.irenka.dom.CtAnnotationInstanceElement;
import org.ashikunep.irenka.dom.CtClass;
import org.ashikunep.irenka.dom.CtField;
import org.ashikunep.irenka.toolkit.Messager;

/**
 * Hackを利用するためのユーティリティ・メソッドを集めたクラス
 *  
 * @author  snuffkin
 */
public class HackUtil {
    public static String getResourceName(CtClass<?> action) {
		String resourceRoot = "src/main/resources";
		String fileName = action.getName().replaceAll("\\.", "/").replace(
				"Action", "")
				+ ".xml";
		String resourceName = resourceRoot + "/" + fileName;
		return resourceName;
	}

	public static void validateField(CtClass<?> action, CtField<?> field,
			CtAnnotationInstance<?> annotation, Messager messager) {
		String resourceName = HackUtil.getResourceName(action);
		if (!Id2WidgetMapHolder.exists(resourceName)) {
			return;
		}
		CtAnnotationInstanceElement<?> element = annotation.getElement("id");
		String id;
		if (element != null) {
			id = element.getValue().toString().replaceAll("\"", "");
		} else {
			id = field.getSimpleName();
		}
		String widget = Id2WidgetMapHolder.getWidgetName(resourceName, id);
		if (widget == null) {
			messager.warn(field, "フィールドに対応するid(" + id + ")が画面定義("
					+ resourceName + ")に存在しません。");
			return;
		}
	}
}
