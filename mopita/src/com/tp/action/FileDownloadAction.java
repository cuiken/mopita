package com.tp.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;
import com.tp.utils.Constants;

@Results( { @Result(name = "success", params = { "inputName", "inputStream", "contentDisposition",
		"attachment; filename=\"${downloadFileName}\"", "bufferSize", "4096", "contentType",
		"application/vnd.android.package-archive", "contentLength", "${contentLength}" }, type = "stream") })
public class FileDownloadAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private String inputPath;
	private String downloadFileName;
	private long contentLength;
	private InputStream inputStream;

	@Override
	public String execute() throws Exception {
		inputPath = Constants.FILE_STORAGE + new String(inputPath.getBytes("iso-8859-1"), "utf-8");
		File file = new File(inputPath);
		downloadFileName = new String(file.getName().getBytes(), "ISO8859-1");
		contentLength = file.length();
		inputStream = new FileInputStream(file);
		return SUCCESS;
	}

	public InputStream getInputStream() {

		return inputStream;

	}

	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}

	public String getDownloadFileName() {
		return downloadFileName;
	}

	public long getContentLength() {
		return contentLength;
	}
}
