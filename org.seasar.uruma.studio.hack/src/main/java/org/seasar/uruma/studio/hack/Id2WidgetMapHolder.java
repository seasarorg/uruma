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

import java.io.InputStream;
import java.util.Hashtable;
import java.util.Map;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * id-widgetマップを保持するクラス
 * 
 * @author  snuffkin
 */
public class Id2WidgetMapHolder {
	/**
	 * map: resourceName -> (map: id -> widget name)
	 */
	private static Map<String, Map<String, String>> id2WidgetMapList = new Hashtable<String, Map<String, String>>();

	/**
	 * 指定した画面定義のid-widgetマップが存在するかどうか確認する。
	 * @param resourceName
	 * @return  true:存在する、false:存在しない。
	 */
	public static boolean exists(String resourceName) {
		return id2WidgetMapList.containsKey(resourceName);
	}

	/**
	 * 指定した画面定義の、指定したidがid-widgetマップに存在するかどうか確認する。
	 * @param resourceName
	 * @param id
	 * @return  true:存在する、false:存在しない。
	 */
	public static boolean exists(String resourceName, String id) {
		Map<String, String> id2WidgetMap = id2WidgetMapList.get(resourceName);
		if (id2WidgetMap != null) {
			return id2WidgetMap.containsKey(id);
		}
		return false;
	}

	/**
	 * 画面定義名をキーにして、id-widgetマップを返す。
	 * @param resourceName
	 * @return  id-widgetマップ。存在しない場合はnull。
	 */
	private static Map<String, String> getId2WidgetMap(String resourceName) {
		return id2WidgetMapList.get(resourceName);
	}

	/**
	 * 画面定義名とidをキーにして、widget名を返す。
	 * @param resourceName
	 * @param id
	 * @return 　widget名。存在しない場合はnull。
	 */
	public static String getWidgetName(String resourceName, String id) {
		Map<String, String> map = getId2WidgetMap(resourceName);
		if (map != null) {
			return map.get(id);
		}
		return null;
	}

	/**
	 * 指定した画面定義に対するid-widgetマップが存在しない場合に、id-widgetマップを作成する。
	 * @param resourceName
	 * @param is
	 */
	public static void createId2WidgetMap(String resourceName, InputStream is) {
		if (exists(resourceName) == false) {
			Map<String, String> map = createId2WidgetMap(is);
			id2WidgetMapList.put(resourceName, map);
		}
	}

	private static Map<String, String> createId2WidgetMap(InputStream is) {
		Map<String, String> map = new Hashtable<String, String>();
		XPath xpath = XPathFactory.newInstance().newXPath();
		NodeList nodes;
		try {
			nodes = (NodeList) xpath.evaluate("/descendant::node()[@id]",
					new InputSource(is), XPathConstants.NODESET);
			for (int index = 0; index < nodes.getLength(); index++) {
				Node node = nodes.item(index);
				String name = node.getNodeName();
				Node attr = (Node) xpath.evaluate("@id", node,
						XPathConstants.NODE);
				String id = attr.getNodeValue();
				map.put(id, name);
			}
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		return map;
	}
}
