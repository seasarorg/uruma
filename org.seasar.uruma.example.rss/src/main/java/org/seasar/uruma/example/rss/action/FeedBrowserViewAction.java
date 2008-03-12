package org.seasar.uruma.example.rss.action;

import org.seasar.uruma.annotation.EventListener;
import org.seasar.uruma.annotation.ExportValue;
import org.seasar.uruma.annotation.Form;
import org.seasar.uruma.annotation.InitializeMethod;
import org.seasar.uruma.example.rss.dto.FeedEntryDto;
import org.seasar.uruma.example.rss.util.S2ContainerListtoHtmlUtil;

/**
 * @author y.sugigami
 */
@Form(FeedBrowserViewAction.class)
public class FeedBrowserViewAction  {
	
	@ExportValue(id = "feedBrowser")
	public String html;
	
	@InitializeMethod
	public void initialize() {
		html = S2ContainerListtoHtmlUtil.list("");
	}
	
	/**
	 * フェイードエントリが選択されたときに呼び出されるメソッドです。<br />
	 * 
	 * @param selected
	 *            選択されている {@link FeedEntryDto} オブジェクト
	 */
	@EventListener(id = "feedListTable")
	public void feedSelected(final FeedEntryDto selected) {
		html = selected.getDescription();
	}
}
