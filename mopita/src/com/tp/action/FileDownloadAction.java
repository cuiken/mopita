package com.tp.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.tp.entity.ClientFile;
import com.tp.entity.LogInHome;
import com.tp.service.ClientFileManager;
import com.tp.service.LogService;
import com.tp.utils.Constants;
import com.tp.utils.Struts2Utils;

public class FileDownloadAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private String inputPath;
	private String downloadFileName;
	private long contentLength;

	private LogService logService;
	private ClientFileManager clientFileManager;

	@Override
	public String execute() throws Exception {

		inputPath = Constants.FILE_STORAGE + new String(inputPath.getBytes("iso-8859-1"), "utf-8");
		File file = new File(inputPath);
		if (file.exists()) {
			long p = 0;
			long fileLength = file.length();
			downloadFileName = new String(file.getName().getBytes(), "ISO8859-1");

			InputStream inputStream = new FileInputStream(file);
			HttpServletResponse response = Struts2Utils.getResponse();
			HttpServletRequest request = Struts2Utils.getRequest();
			response.reset();
			response.setHeader("Accept-Ranges", "bytes");
			String range = request.getHeader("Range");
			if (range != null) {
				response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
				String r = range.replaceAll("bytes=", "");
				String[] rs = r.split("-");

				p = Long.parseLong(rs[0]);
			}
			response.setHeader("content-Length", String.valueOf(fileLength));
			if (p != 0) {
				String contentRange = new StringBuffer("bytes").append(new Long(p).toString()).append("-")
						.append(new Long(fileLength - 1).toString()).append("/")
						.append(new Long(fileLength).toString()).toString();
				response.setHeader("Content-Range", contentRange);
				inputStream.skip(p);
			}
			response.addHeader("Content-Disposition", "attachment; filename=" + "\"" + downloadFileName + "\"");
			response.setContentType("application/vnd.android.package-archive");
			byte[] buffer = new byte[1024];
			int len = 0;
			OutputStream output = response.getOutputStream();
			while ((len = inputStream.read(buffer)) != -1) {
				output.write(buffer, 0, len);
			}
			output.flush();
			output.close();
			inputStream.close();
		}
		return null;
	}

	public String getClient() throws Exception {
		LogInHome logHome = getLog();
		logService.saveLogInHome(logHome);
		String version = Struts2Utils.getParameter("v");
		String app = Struts2Utils.getParameter("app");

		ClientFile newClient;
		if (version == null || version.isEmpty() || (!app.isEmpty() && !app.equals("千机解锁") && !app.equals("Funlocker"))) {
			String newestVersion = clientFileManager.getNewestVersionCode();
			newClient = clientFileManager.getClientByVersion(newestVersion);

		} else {
			newClient = clientFileManager.getClientByVersion(version);

		}
		
		this.setInputPath("/" + newClient.getPath());
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

	@Autowired
	public void setClientFileManager(ClientFileManager clientFileManager) {
		this.clientFileManager = clientFileManager;
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
