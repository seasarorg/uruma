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

/**
 * {@link SuppressWarningsFinder} のためのテストクラスです。<br />
 * 
 * @author snuffkin
 */
public class SuppressWarningsFinderTest {

	// warning
	@SuppressWarnings("unchecked")
	private String variable1;

	private String variable2;
	
	public String getVariable1() {
		return variable1;
	}

	// warning
	@SuppressWarnings("unchecked")
	public String getVariable2() {
		return variable2;
	}

	@Override
	public String toString() {
		return null;
	}
}
