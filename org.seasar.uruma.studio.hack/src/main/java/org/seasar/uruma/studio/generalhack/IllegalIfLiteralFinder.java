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
package org.seasar.uruma.studio.generalhack;

import org.ashikunep.irenka.dom.CtIf;
import org.ashikunep.irenka.toolkit.Messager;

/**
 * if条件に固定値true/falseを使用している箇所をwarningとするHack Action
 * 
 * @author  snuffkin
 */
public class IllegalIfLiteralFinder {

	/**
	 * @when ctIf.condition = true
	 */
	public void foundTrue(CtIf ctIf, Messager messager) {
		messager.warn(ctIf, "if条件に固定値trueを使用しています。");
	}

	/**
	 * @when ctIf.condition = false
	 */
	public void foundFalse(CtIf ctIf, Messager messager) {
		messager.warn(ctIf, "if条件に固定値falseを使用しています。");
	}
}
