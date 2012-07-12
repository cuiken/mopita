package com.tp.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;

import javax.activation.MimetypesFileTypeMap;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ArrayUtils;

import com.opensymphony.xwork2.ActionSupport;
import com.tp.utils.Constants;
import com.tp.utils.Servlets;
import com.tp.utils.Struts2Utils;

public class ImageAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private String path;

	private static final String[] GZIP_MIME_TYPES = { "text/html", "text/xml", "text/plain", "text/css",
			"text/javascript", "application/xml", "application/xhtml+xml", "application/x-javascript" };

	private static final int GZIP_MINI_LENGTH = 512;

	private MimetypesFileTypeMap mimetypesFileTypeMap;
	public static final long ONE_YEAR_SECONDS = 60 * 60 * 24 * 365;

	@Override
	public String execute() throws Exception {
		return getImage();
	}

	public String getImage() throws Exception {
		if (!path.isEmpty()) {
			path=URLDecoder.decode(path, "UTF-8");
			String imgURL = Constants.FILE_STORAGE + new String(path.getBytes("iso-8859-1"), "utf-8");
			responseImage(imgURL);
		}
		return null;
	}

	private void responseImage(String imgURL) throws Exception {
		File file = new File(imgURL);
		HttpServletResponse response = Struts2Utils.getResponse();
		HttpServletRequest request = Struts2Utils.getRequest();
		response.setContentType("image/*");
		ContentInfo contentInfo = getContentInfo(file);
		//根据Etag或ModifiedSince Header判断客户端的缓存文件是否有效, 如仍有效则设置返回码为304,直接返回.
		if (!Servlets.checkIfModifiedSince(request, response, contentInfo.lastModified)
				|| !Servlets.checkIfNoneMatchEtag(request, response, contentInfo.etag)) {
			return;
		}
		Servlets.setExpiresHeader(response, ONE_YEAR_SECONDS);
		Servlets.setLastModifiedHeader(response, System.currentTimeMillis());
		Servlets.setEtag(response, contentInfo.etag);

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

	@PostConstruct
	public void init() {
		mimetypesFileTypeMap = new MimetypesFileTypeMap();
		mimetypesFileTypeMap.addMimeTypes("text/css css");
	}

	private ContentInfo getContentInfo(File file) {
		ContentInfo contentInfo = new ContentInfo();

		contentInfo.file = file;
		//		contentInfo.contentPath = contentPath;
		contentInfo.fileName = file.getName();
		contentInfo.length = (int) file.length();

		contentInfo.lastModified = file.lastModified();
		contentInfo.etag = "W/\"" + contentInfo.lastModified + "\"";

		contentInfo.mimeType = mimetypesFileTypeMap.getContentType(contentInfo.fileName);

		if (contentInfo.length >= GZIP_MINI_LENGTH && ArrayUtils.contains(GZIP_MIME_TYPES, contentInfo.mimeType)) {
			contentInfo.needGzip = true;
		} else {
			contentInfo.needGzip = false;
		}

		return contentInfo;
	}

	static class ContentInfo {
		protected String contentPath;
		protected File file;
		protected String fileName;
		protected int length;
		protected String mimeType;
		protected long lastModified;
		protected String etag;
		protected boolean needGzip;
	}
}
