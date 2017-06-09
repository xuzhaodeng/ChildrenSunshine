package com.pas.edu.entity;

public class ExportExcel {
	
	private String url;
	
	private String fileName;
	

	public ExportExcel() {
		super();
	}

	public ExportExcel(String url, String fileName) {
		super();
		this.url = url;
		this.fileName = fileName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
