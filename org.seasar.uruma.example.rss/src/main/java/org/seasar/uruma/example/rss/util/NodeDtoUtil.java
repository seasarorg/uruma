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
package org.seasar.uruma.example.rss.util;

import org.seasar.uruma.example.rss.dto.GenericTreeDto;
import org.seasar.uruma.example.rss.dto.NodeDto;

/**
 * @author y.sugigami
 */
public class NodeDtoUtil {

	public static NodeDto<Object> createNodeDto(Object obj) {
		NodeDto<Object> NodeDto = new NodeDto<Object>();
		NodeDto.setTarget(obj);
		return NodeDto;
	}
	
	public static NodeDto<Object> createGenericTreeNodeDto(String name, String image) {
		NodeDto<Object> NodeDto = new NodeDto<Object>();
		GenericTreeDto genericTreeDto = new GenericTreeDto();
		genericTreeDto.setName(name);
		genericTreeDto.setImage(image);
		NodeDto.setTarget(genericTreeDto);
		return NodeDto;
	}
}
