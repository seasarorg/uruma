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

import org.ashikunep.irenka.dom.CtInfix;
import org.ashikunep.irenka.toolkit.Messager;

public class IllegalInfixFinder {

	/**
	 * @when
	 *   infix2 : {@link CtInfix}
	 *   infix3 : {@link CtInfix}
	 *   infix4 : {@link CtInfix}
	 *   infix5 : {@link CtInfix}
	 *   infix1.parent = infix2
	 *   infix2.parent = infix3
	 *   infix3.parent = infix4
	 *   infix4.parent = infix5
	 */
	public void foundTrue(CtInfix<?> infix1,
	                      Messager messager) {
		messager.warn(infix1,
				      "infix。");
	}
}
