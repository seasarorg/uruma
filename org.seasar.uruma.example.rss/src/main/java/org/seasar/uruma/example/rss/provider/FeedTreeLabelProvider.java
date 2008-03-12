package org.seasar.uruma.example.rss.provider;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.seasar.eclipse.common.util.ImageManager;
import org.seasar.uruma.example.rss.dto.FeedDto;
import org.seasar.uruma.example.rss.dto.Node;

/**
 * @author susie
 */
public class FeedTreeLabelProvider extends LabelProvider {
	@Override
	public Image getImage(final Object element) {
		return ImageManager.getImage("rss");
	}

	@Override
	public String getText(final Object element) {
		Node node = (Node) element;
		FeedDto dto = (FeedDto) node.getTarget();
		return dto.getTitle();
	}
}
