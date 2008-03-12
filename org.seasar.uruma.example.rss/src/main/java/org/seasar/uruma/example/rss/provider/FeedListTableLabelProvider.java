package org.seasar.uruma.example.rss.provider;

import org.eclipse.swt.graphics.Image;
import org.seasar.eclipse.common.util.ImageManager;
import org.seasar.uruma.example.rss.dto.FeedEntryDto;

/**
 * ファイルビューのためのラベルプロバイダです。<br />
 * 
 * @author susie
 */
public class FeedListTableLabelProvider {

	public String getNameText(final FeedEntryDto dto) {
		return dto.getTitle();
	}

	public String getUpdateTimeText(final FeedEntryDto dto) {
		return dto.getUpdatedDate();
	}

	public Image getNameImage(final FeedEntryDto dto) {
		return ImageManager.getImage("rss");
	}
}
