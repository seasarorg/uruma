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


public class FileDetailTableLabelProvider {

	public String getFileNameText(final FileDto dto) {
		return "TEST";
	}
	// @Override
	// public Image getColumnImage(final Object element, final int columnIndex)
	// {
	// if (columnIndex != 0) {
	// return null;
	// }
	//
	// File file = new File(((FileDto) element).absolutePath);
	// if (file.isDirectory()) {
	// return ImageManager.getImage("folder");
	// } else {
	// String ext = StringUtil.substringToLast(file.getName(), ".");
	// Image image = ImageManager.getImage(ext);
	//
	// if (image == null) {
	// Program program = Program.findProgram(ext);
	// if (program != null) {
	// image = ImageManager.putImage(ext, program.getImageData());
	// } else {
	// return ImageManager.getImage("file");
	// }
	// }
	// return image;
	//
	// }
	// }
}
