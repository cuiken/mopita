package com.tp.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import com.opensymphony.xwork2.ActionSupport;
import com.tp.utils.Constants;
import com.tp.utils.Struts2Utils;

public class ImageAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private String path;

	@Override
	public String execute() throws Exception {
		return getImage();
	}

	public String getImage() throws Exception {
		String imgURL = Constants.FILE_STORAGE + new String(path.getBytes("iso-8859-1"), "utf-8");
		responseImage(imgURL);
		return null;
	}

	private void responseImage(String imgURL) throws Exception {
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("image/*");
		File file = new File(imgURL);
		byte[] buffer = new byte[1024];
		InputStream is = new FileInputStream(file);
		int len = 0;
		OutputStream output = response.getOutputStream();
		while ((len = is.read(buffer)) != -1) {
			output.write(buffer, 0, len);
		}
		output.flush();
		output.close();
		is.close();
	}

	public void setPath(String path) {
		this.path = path;
	}

}
