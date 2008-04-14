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
package org.seasar.uruma.example.rss.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * 汎用的なツリーのデータ階層です。 <br />
 * 
 * @author y.sugigami
 */
public class NodeDto<T> {

	private T target;
	
	private T parent;
	
	private List<NodeDto<T>> Children = new ArrayList<NodeDto<T>>();
	
	public void setTarget(T target) {
		this.target = target;
	}
	
	public T getTarget() {
		return this.target;
	}

	public T getParent() {
		return parent;
	}

	public void setParent(T parent) {
		this.parent = parent;
	}

	public List<NodeDto<T>> getChildren() {
		return Children;
	}

	public void addChild(NodeDto<T> child) {
		this.Children.add(child);
	}
	
	public boolean hasChildren() {
		if ( ! Children.isEmpty()) {
			return true;
		}
		return false;
	}
	
}
