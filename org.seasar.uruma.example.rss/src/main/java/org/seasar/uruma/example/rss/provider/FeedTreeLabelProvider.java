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

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.seasar.eclipse.common.util.ImageManager;
import org.seasar.uruma.example.rss.dto.FeedDto;
import org.seasar.uruma.example.rss.dto.NodeDto;

/**
 * @author y.sugigami
 */
public class FeedTreeLabelProvider extends LabelProvider {
	@Override
	public Image getImage(final Object element) {
		return ImageManager.getImage("rss");
	}

	@Override
	public String getText(final Object element) {
		NodeDto nodeDto = (NodeDto) element;
		FeedDto dto = (FeedDto) nodeDto.getTarget();
		return dto.getTitle();
	}
}
