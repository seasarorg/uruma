package org.seasar.uruma.example.filemanager;

import java.io.File;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.program.Program;
import org.seasar.eclipse.common.util.ImageManager;
import org.seasar.framework.util.StringUtil;
import org.seasar.uruma.viewer.GenericTableLabelProvider;

public class FileDetailTableLabelProvider extends GenericTableLabelProvider {

	@Override
	public Image getColumnImage(final Object element, final int columnIndex) {
		if (columnIndex != 0) {
			return null;
		}

		File file = new File(((FileDto) element).absolutePath);
		if (file.isDirectory()) {
			return ImageManager.getImage("folder");
		} else {
			String ext = StringUtil.substringToLast(file.getName(), ".");
			Image image = ImageManager.getImage(ext);

			if (image == null) {
				Program program = Program.findProgram(ext);
				if (program != null) {
					image = ImageManager.putImage(ext, program.getImageData());
				} else {
					return ImageManager.getImage("file");
				}
			}
			return image;

		}
	}
}
