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

import org.apache.log4j.Category;
import org.ashikunep.irenka.dom.CtIf;
import org.ashikunep.irenka.dom.CtMethodInvocation;
import org.ashikunep.irenka.toolkit.Messager;

/**
 * {@link Category#isDebugEnabled()}を利用せずに
 * {@link Category#debug(Object)}を利用した箇所をwarningとするHack Action
 * 
 * @author  snuffkin
 */
public class IllegalDebugFinder {
	
	/**
	 * @when invocation.target =  {@link Category#debug(Object)} except { enabled :  {@link CtMethodInvocation} enabled.target =  {@link Category#isDebugEnabled()} guard :  {@link CtIf} guard in enabled.ancestors guard in invocation.ancestors }
	 */
	public void found(CtMethodInvocation<?> invocation, Messager messager) {
		messager.warn(invocation,
				"Category#isDebugEnabledを使用せずにCategory#debugを呼び出しています。");
	}
}
