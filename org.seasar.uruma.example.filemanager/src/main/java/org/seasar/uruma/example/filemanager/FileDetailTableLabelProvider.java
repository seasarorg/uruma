/*
 * Copyright 2004-2007 the Seasar Foundation and the Others.
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
package org.seasar.uruma.example.filemanager;

import java.io.File;
import java.util.Date;
import java.util.Formatter;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.program.Program;
import org.seasar.eclipse.common.util.ImageManager;
import org.seasar.framework.util.StringUtil;
import org.seasar.uruma.core.UrumaConstants;

public class FileDetailTableLabelProvider {

	public String getFileNameText(final File file) {
		return file.getName();
	}

	public String getFileSizeText(final File file) {
		if (file.isFile()) {
			Formatter formatter = new Formatter();
			return formatter.format("%,d", file.length()).out().toString();
		} else {
			return UrumaConstants.NULL_STRING;
		}
	}

	public String getFileUpdateTimeText(final File file) {
		Formatter formatter = new Formatter();
		formatter.format("%tY/%<tm/%<td(%<ta) %<tk:%<tM:%<tS", new Date(file
				.lastModified()));
		return formatter.out().toString();
	}

	public Image getFileNameImage(final File file) {
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
