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
package org.seasar.uruma.example.rss.provider;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.seasar.uruma.example.rss.dto.NodeDto;


/**
 * @author y-sugigami
 */
public class FeedTreeContentProvider implements ITreeContentProvider {

	public Object[] getChildren(final Object parentElement) {
		try {
			NodeDto nodeDto = (NodeDto) parentElement;	
			if (nodeDto.hasChildren()) {
				return nodeDto.getChildren().toArray();
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Object[] {};
	}

	public Object getParent(final Object element) {
		NodeDto nodeDto = (NodeDto) element;
		return nodeDto.getParent();
	}

	public boolean hasChildren(final Object element) {
		NodeDto nodeDto = (NodeDto) element;
		return nodeDto.hasChildren();
	}

	public Object[] getElements(final Object inputElement) {
		return getChildren(inputElement);
	}

	public void dispose() {
	}

	public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
	}

}
