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
import org.ashikunep.irenka.dom.CtClass;
import org.ashikunep.irenka.dom.CtField;
import org.ashikunep.irenka.toolkit.Messager;
import org.seasar.uruma.annotation.ExportValue;
import org.seasar.uruma.annotation.ImportExportValue;
import org.seasar.uruma.annotation.ImportValue;

/**
 * バリュー・バインディングと画面定義の整合性を確認するHack
 * 
 * @author  snuffkin
 */
public class ValueBindingValidator {

    /**
	 * インポート・バリュー・バインディングに対応する画面定義の存在を確認するHack　Action
	 * @when action.simpleName =~ ".*Action" field.parent = action annotation.type <=  {@link ImportValue} annotation in field.annotations
	 */
	public void validateImportBinding(CtClass<?> action, CtField<?> field,
			CtAnnotationInstance<?> annotation, Messager messager) {
		HackUtil.validateField(action, field, annotation, messager);
	}

	/**
	 * エクスポート・バリュー・バインディングに対応する画面定義の存在を確認するHack　Action
	 * @when action.simpleName =~ ".*Action" field.parent = action annotation.type <=  {@link ExportValue} annotation in field.annotations
	 */
	public void validateExportBinding(CtClass<?> action, CtField<?> field,
			CtAnnotationInstance<?> annotation, Messager messager) {
		HackUtil.validateField(action, field, annotation, messager);
	}

	/**
	 * インポート・エクスポート・バリュー・バインディングに対応する画面定義の存在を確認するHack　Action
	 * @when action.simpleName =~ ".*Action" field.parent = action annotation.type <=  {@link ImportExportValue} annotation in field.annotations
	 */
	public void validateExportBindig(CtClass<?> action, CtField<?> field,
			CtAnnotationInstance<?> annotation, Messager messager) {
		HackUtil.validateField(action, field, annotation, messager);
	}
}
