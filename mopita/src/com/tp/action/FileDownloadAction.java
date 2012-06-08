package com.tp.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.tp.entity.LogInHome;
import com.tp.service.LogService;
import com.tp.utils.Constants;
import com.tp.utils.Struts2Utils;

@Results({ @Result(name = "success", params = { "inputName", "inputStream", "contentDisposition",
		"attachment; filename=\"${downloadFileName}\"", "bufferSize", "4096", "contentType",
		"application/vnd.android.package-archive", "contentLength", "${contentLength}" }, type = "stream") })
public class FileDownloadAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private String inputPath;
	private String downloadFileName;
	private long contentLength;
	private InputStream inputStream;

	private LogService logService;

	@Override
	public String execute() throws Exception {
		LogInHome log = getLog();
		logService.saveLogInHome(log);
		inputPath = Constants.FILE_STORAGE + new String(inputPath.getBytes("iso-8859-1"), "utf-8");
		File file = new File(inputPath);
		downloadFileName = new String(file.getName().getBytes(), "ISO8859-1");
		contentLength = file.length();
		inputStream = new FileInputStream(file);
		return SUCCESS;
	}

	public String getClient() throws Exception {

		this.setInputPath("/client/FunlockerClientV2.0.0.apk");
		return execute();
	}

	private LogInHome getLog() {
		HttpServletRequest request = Struts2Utils.getRequest();

		LogInHome log = new LogInHome();
		String requestLink = request.getServletPath() + "?" + request.getQueryString();
		String language = Struts2Utils.getParameter(Constants.PARA_LANGUAGE);
		String fromMarket = Struts2Utils.getParameter(Constants.PARA_FROM_MARKET);
		String downMethod = Struts2Utils.getParameter(Constants.PARA_DOWNLOAD_METHOD);
		String imei = Struts2Utils.getParameter(Constants.PARA_IMEI);
		String imsi = Struts2Utils.getParameter(Constants.PARA_IMSI);
		String clientVersion = Struts2Utils.getParameter(Constants.PARA_CLIENT_VERSION);
		String resolution = Struts2Utils.getParameter(Constants.PARA_RESOLUTION);
		String storeType = Struts2Utils.getParameter(Constants.PARA_STORE_TYPE);

		int index = StringUtils.indexOf(requestLink, "&inputPath");
		requestLink = StringUtils.substring(requestLink, 0, index);

		log.setRequestLink(requestLink);
		log.setClientVersion(clientVersion);
		log.setStoreType(storeType);
		log.setDownType(downMethod);
		log.setFromMarket(fromMarket);
		log.setResolution(resolution);
		log.setImei(imei);
		log.setImsi(imsi);
		log.setLanguage(language);
		return log;
	}

	@Autowired
	public void setLogService(LogService logService) {
		this.logService = logService;
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
