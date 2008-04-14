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
package org.seasar.uruma.example.rss.action;

import java.lang.reflect.Field;
import java.util.List;

import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.uruma.annotation.ExportValue;
import org.seasar.uruma.annotation.Form;
import org.seasar.uruma.annotation.InitializeMethod;
import org.seasar.uruma.binding.enables.EnablesDependingDef;
import org.seasar.uruma.component.Template;
import org.seasar.uruma.component.UIComponent;
import org.seasar.uruma.component.UIComponentContainer;
import org.seasar.uruma.component.UIElement;
import org.seasar.uruma.component.jface.MenuComponent;
import org.seasar.uruma.component.jface.MenuItemComponent;
import org.seasar.uruma.component.rcp.PartComponent;
import org.seasar.uruma.component.rcp.PerspectiveComponent;
import org.seasar.uruma.component.rcp.ViewPartComponent;
import org.seasar.uruma.component.rcp.WorkbenchComponent;
import org.seasar.uruma.context.ApplicationContext;
import org.seasar.uruma.context.PartContext;
import org.seasar.uruma.context.WidgetHandle;
import org.seasar.uruma.context.WindowContext;
import org.seasar.uruma.core.TemplateManager;
import org.seasar.uruma.example.rss.constants.WidgetConstants;
import org.seasar.uruma.example.rss.dto.GenericTreeDto;
import org.seasar.uruma.example.rss.dto.NodeDto;
import org.seasar.uruma.example.rss.util.NodeDtoUtil;
import org.seasar.uruma.rcp.util.UrumaServiceUtil;


/**
 * Urumaが内部で保持しているコンテキストを表示する。<br />
 * 
 * @author y.sugigami
 */
@Form(DebugTreeViewAction.class)
public class DebugTreeViewAction {
	
	/**
	 * コンテキストのツリー。 <br />
	 */
	@ExportValue(id = WidgetConstants.DEBUG_TREE)
	public NodeDto<Object> root;

	/**
	 * テンプレートのツリー。 <br />
	 */
	@ExportValue(id = WidgetConstants.TEMPLATE_MANAGER_TREE)
	public NodeDto<Object> rootTemplateManager;
	
	/**
	 * 初期化処理 <br />
	 */
	@InitializeMethod 
	public void initialize() {
		root = new NodeDto<Object>();
		
		//
		// WorkbenchComponent
		//
		WorkbenchComponent workbenchComponent = UrumaServiceUtil.getService().getWorkbenchComponent();
		
		String text = workbenchComponent.getClass().getName() + 
			" id=[" + workbenchComponent.getId() +"] " +
			" basePath=[" + workbenchComponent.getBasePath() + "]";
		
		NodeDto<Object> workbenchComponentNodeDto = NodeDtoUtil.createGenericTreeNodeDto(text, null);
		root.addChild(workbenchComponentNodeDto);
		
		List<UIElement> list = workbenchComponent.getChildren();
		for (UIElement element : list) {
			ccc(workbenchComponentNodeDto, element);
		}
		
		//
		// ApplicationContext
		//
		WindowContext wc = UrumaServiceUtil.getService().getWorkbenchWindowContext();
		ApplicationContext ac = wc.getApplicationContext();
		
		String textAc = ac.getClass().getName(); 
		NodeDto<Object> acNodeDto = NodeDtoUtil.createGenericTreeNodeDto(textAc, null);
		root.addChild(acNodeDto);
		
		String textWc = wc.getClass().getName() +
			" name=[" + wc.getName() + "]"; 
		
		NodeDto<Object> wcNodeDto = NodeDtoUtil.createGenericTreeNodeDto(textWc, null);
		acNodeDto.addChild(wcNodeDto);
		
		
		// PartContext
		NodeDto<Object> partContextNodeDto = NodeDtoUtil.createGenericTreeNodeDto("PartContext", null);
		wcNodeDto.addChild(partContextNodeDto);
		for (PartContext pc : wc.getPartContextList()) {
			String textPc = pc.getClass().getName() +
			" name=[" + pc.getName() + "]" +
			" partActionObject=[" + pc.getPartActionObject().getClass().getSimpleName() + "]";
			
			NodeDto<Object> pcNodeDto = NodeDtoUtil.createGenericTreeNodeDto(textPc, null);
			partContextNodeDto.addChild(pcNodeDto);
			
			for (WidgetHandle wh : pc.getWidgetHandles()) {
				createWidgetHandle(pcNodeDto, wh);
			}
		}
		
		// EnablesDependingDef
		NodeDto<Object> enablesDependingDefNodeDto = NodeDtoUtil.createGenericTreeNodeDto("EnablesDependingDef", null);
		wcNodeDto.addChild(enablesDependingDefNodeDto);
		for (EnablesDependingDef ed : wc.getEnablesDependingDefList()) {
			String textPc = ed.getClass().getName() +
			" targetId=[" + ed.getTargetId()+ "]" +
			" type=[" + ed.getType() + "]";
			
			NodeDto<Object> pcNodeDto = NodeDtoUtil.createGenericTreeNodeDto(textPc, null);
			enablesDependingDefNodeDto.addChild(pcNodeDto);

			createWidgetHandle(pcNodeDto, ed.getWidgetHandle());
		}
		
		// EnablesDependingDef
		NodeDto<Object> widgetHandleNodeDto = NodeDtoUtil.createGenericTreeNodeDto("WidgetHandle", null);
		wcNodeDto.addChild(widgetHandleNodeDto);
		for (WidgetHandle wh : wc.getWidgetHandles()) {
			createWidgetHandle(widgetHandleNodeDto, wh);
		}
		
		
		//
		// TemplateManager
		//
		TemplateManager templateManager = (TemplateManager) UrumaServiceUtil.getService().getContainer().getComponent(TemplateManager.class);
		List<Template> viewTemplates = templateManager.getTemplates(ViewPartComponent.class);
   
		rootTemplateManager = new NodeDto<Object>();
		
		for (Template template : viewTemplates) {
			NodeDto<Object> NodeDto = new NodeDto<Object>();
			GenericTreeDto treeDto = new GenericTreeDto();
			UIComponentContainer uic = template.getRootComponent();
			treeDto.setName(uic.getId());
			NodeDto.setTarget(treeDto);
			rootTemplateManager.addChild(NodeDto);
			
			
			createChildTemplateManager(NodeDto, uic);
		}
	}


	private void createChildTemplateManager(NodeDto<Object> nodeDto, Object uic) {
		BeanDesc beanDesc = BeanDescFactory.getBeanDesc(uic.getClass());
		for (int i = 0; i < beanDesc.getFieldSize(); i++) {
			Field filed = beanDesc.getField(i);
			
			NodeDto<Object> nodeDtoChild = new NodeDto<Object>();
			GenericTreeDto treeChildDto = new GenericTreeDto();
			
			String value = "";
			Object obj = null;
			if (filed.isAccessible()) {
				try {
					obj = filed.get(uic);
					if (obj != null) {
						value = obj.getClass().getSimpleName();
						value += ": " + obj.toString();	
					} else {
						value = "null";
					}
				} catch (IllegalArgumentException ignore) {
				} catch (IllegalAccessException ignore) {
				}
			}
			
			treeChildDto.setName(filed.getName() + "=[" + value  +"]");
			nodeDtoChild.setTarget(treeChildDto);
			nodeDto.addChild(nodeDtoChild);
			
			if (obj instanceof List<?>) {
				List<?> list = (List<?>) obj;
				for (int j = 0; j < list.size(); j++) {
					Object array_element = list.get(j);

					NodeDto<Object> NodeDtoCChild = new NodeDto<Object>();
					GenericTreeDto treeCChildDto = new GenericTreeDto();
					treeCChildDto.setName(array_element.toString());
					NodeDtoCChild.setTarget(treeCChildDto);
					nodeDtoChild.addChild(NodeDtoCChild);
					
					createChildTemplateManager(NodeDtoCChild, array_element);
				}
			}
		}
	}
	
	private void createWidgetHandle(NodeDto parent, WidgetHandle wh) {
		String text = wh.getClass().getName() +
			" id=[" + wh.getId() + "]" +
			" widgetClass=[" + wh.getWidgetClass().getName() + "]" ;
		
		NodeDto<Object> whNodeDto = NodeDtoUtil.createGenericTreeNodeDto(text, null);
		parent.addChild(whNodeDto);
		
		UIComponent uc = wh.getUiComponent();
		if (uc != null) {
			String textUc = uc.getClass().getName() +
			" id=[" + uc.getId() + "]" +
			" path=[" + uc.getPath() + "]" +
			" style=[" + uc.getStyle() + "]" +
			" renderer=[" + uc.getRenderer().getClass().getName()+ "]";
			
			NodeDto<Object> ucNodeDto = NodeDtoUtil.createGenericTreeNodeDto(textUc, null);
			whNodeDto.addChild(ucNodeDto);
		}
	}
	
	private void ccc(NodeDto parent, UIElement element) {
		NodeDto NodeDto = NodeDtoUtil.createGenericTreeNodeDto("", null);
		String text = "";
		if (MenuComponent.class.isAssignableFrom(element.getClass())) {
			MenuComponent c = (MenuComponent) element;
			text = c.getClass().getName() + 
				" accelerator=[" + c.accelerator + "]" +
				" defaultItemId=[" + c.defaultItemId + "]" +
				" description=[" + c.description + "]" +
				" enabled=[" + c.enabled + "]" +
				" enablesDependingId=[" + c.enablesDependingId + "]" +
				" enablesFor=[" + c.enablesFor + "]" 
				;
			for (UIElement elementChild : c.getChildren()) {
				ccc(NodeDto, elementChild);
			}
			
		} else if (MenuItemComponent.class.isAssignableFrom(element.getClass())) {
			MenuItemComponent c = (MenuItemComponent) element;
			text = c.getClass().getName() + 
				" accelerator=[" + c.accelerator + "]" +
				" description=[" + c.description + "]" +
				" enabled=[" + c.enabled + "]" +
				" enablesDependingId=[" + c.enablesDependingId + "]" +
				" enablesFor=[" + c.enablesFor + "]" 
				;

		} else if (PerspectiveComponent.class.isAssignableFrom(element.getClass())) {
			PerspectiveComponent c = (PerspectiveComponent) element;
			text = c.getClass().getName() +
				" id=[" + c.id + "]" +
				" fixed=[" + c.fixed+ "]" +
				" icon=[" + c.icon+ "]" 
				;
			for (UIElement elementChild : c.getChildren()) {
				ccc(NodeDto, elementChild);
			}

		} else if (PartComponent.class.isAssignableFrom(element.getClass())) {
			PartComponent c = (PartComponent) element;
			text = c.getClass().getName() +
				" ref=[" + c.ref + "]" +
				" position=[" + c.position+ "]" +
				" ratio=[" + c.ratio+ "]" 
				;

		} else {
			text = element.getClass().getName();
		}
		
		((GenericTreeDto) NodeDto.getTarget()).setName(text);
		parent.addChild(NodeDto);

	}

}
