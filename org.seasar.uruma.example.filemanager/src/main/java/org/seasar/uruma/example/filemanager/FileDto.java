package org.seasar.uruma.example.filemanager;

/**
 * @author y-komori
 * 
 */
public class FileDto {
	private String fileName;
	private String fileSize;
	private String fileUpdateTime;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileUpdateTime() {
		return fileUpdateTime;
	}

	public void setFileUpdateTime(String fileUpdateTime) {
		this.fileUpdateTime = fileUpdateTime;
	}

}
