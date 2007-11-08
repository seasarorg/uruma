package org.seasar.uruma.example.filemanager;

import org.seasar.uruma.annotation.BindingLabel;

/**
 * @author y-komori
 * 
 */
public class FileDto {
	public String absolutePath;

	@BindingLabel
	public String fileName;

	@BindingLabel
	public String fileSize;

	@BindingLabel
	public String fileUpdateTime;
}
