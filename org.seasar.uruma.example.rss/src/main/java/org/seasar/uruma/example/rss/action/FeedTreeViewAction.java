package org.seasar.uruma.example.rss.action;

import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.seasar.uruma.annotation.ExportSelection;
import org.seasar.uruma.annotation.ExportValue;
import org.seasar.uruma.annotation.Form;
import org.seasar.uruma.annotation.InitializeMethod;
import org.seasar.uruma.example.rss.dto.FeedDto;
import org.seasar.uruma.example.rss.dto.Node;
import org.seasar.uruma.example.rss.logic.FeedLogic;
import org.seasar.uruma.example.rss.logic.impl.FeedLogicImpl;

/**
 * 
 * @author y.sugigami
 */
@Form(FeedTreeViewAction.class)
public class FeedTreeViewAction {
	
	public IWorkbenchConfigurer workbenchConfigurer;
	
	public IActionBarConfigurer actionBarConfigurer;
	
	public IWorkbenchWindowConfigurer workbenchWindowConfigurer;
	
	public FeedLogic feedLogic = new FeedLogicImpl();
	
	@ExportValue(id = "feedTree")
	public Node<FeedDto> root;

	@ExportSelection(id = "feedTree")
	public Node<FeedDto> selection = root;

	private String[] feedUrls = new String[] {
			"http://www.amazon.co.jp/rss/bestsellers/electronics/3371341/ref=pd_ts_rss_link",
			"http://b.hatena.ne.jp/hotentry?mode=rss", 
			"http://www.pheedo.jp/f/slashdot_japan"
			};
	
	
	@InitializeMethod 
	public void initialize() {
		FeedDto dummy = new FeedDto();
		root = new Node<FeedDto>();
		root.setTarget(dummy);
		
		for (String feedUrl : feedUrls) {
			FeedDto feedDto = feedLogic.fetch(feedUrl);
			Node<FeedDto> child = new Node<FeedDto>();
			child.setTarget(feedDto);
			root.addChild(child);
		}
			
	}
}
