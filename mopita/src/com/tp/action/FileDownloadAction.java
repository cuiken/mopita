package com.tp.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.tp.entity.ClientFile;
import com.tp.entity.LogFromClient;
import com.tp.entity.LogInHome;
import com.tp.service.ClientFileManager;
import com.tp.service.LogService;
import com.tp.utils.Constants;
import com.tp.utils.Struts2Utils;

public class FileDownloadAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private static final String PARA_IMEI = Constants.PARA_IMEI;
	private static final String PARA_IMSI = Constants.PARA_IMSI;
	private static final String PARA_STORE_TYPE = Constants.PARA_STORE_TYPE;
	private static final String PARA_DOWNLOAD_TYPE = Constants.PARA_DOWNLOAD_METHOD;
	private static final String PARA_LANGUAGE = Constants.PARA_LANGUAGE;
	private static final String PARA_CLIENT_VERSION = Constants.PARA_CLIENT_VERSION;
	private static final String PARA_RESOLUTION = Constants.PARA_RESOLUTION;
	private static final String PARA_FROM_MARKET = Constants.PARA_FROM_MARKET;
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
		String path = save();
		if (path.equals(""))
			return null;
		this.setInputPath("/" + path);
		return execute();
	}

	public String save() throws Exception {
		String imei = Struts2Utils.getParameter(PARA_IMEI);
		String imsi = Struts2Utils.getParameter(PARA_IMSI);
		String storeType = Struts2Utils.getParameter(PARA_STORE_TYPE);
		String downType = Struts2Utils.getParameter(PARA_DOWNLOAD_TYPE);
		String language = Struts2Utils.getParameter(PARA_LANGUAGE);
		String clientVersion = Struts2Utils.getParameter(PARA_CLIENT_VERSION);
		String resolution = Struts2Utils.getParameter(PARA_RESOLUTION);
		String fromMarket = Struts2Utils.getParameter(PARA_FROM_MARKET);
		LogFromClient entity = new LogFromClient();
		entity.setImei(imei);
		entity.setImsi(imsi);
		entity.setStoreType(storeType);
		entity.setDownType(downType);
		entity.setLanguage(language);
		entity.setClientVersion(clientVersion);
		entity.setResolution(resolution);
		entity.setFromMarket(fromMarket);
		logService.saveLogFromClent(entity);
		return getNewestClient(clientVersion);

	}

	public String getNewestClient(String versionFromClient) {
		if (versionFromClient == null) {
			String newestVersion = clientFileManager.getNewestVersionCode();
			ClientFile newClient = clientFileManager.getClientByVersion(newestVersion);
			return newClient.getPath();
		}

		String[] vs = StringUtils.split(versionFromClient, Constants.DOT_SEPARATOR);
		if (vs.length > 2) {
			String oldHeader = vs[0];
			String newVersion = clientFileManager.getMaxByVersion(oldHeader);
			String[] newvs = StringUtils.split(newVersion, Constants.DOT_SEPARATOR);
			String newHeader = newvs[0];
			String newUse = newvs[1];
			String oldUse = vs[1];
			if (oldHeader.equals(newHeader)) {
				if (Integer.valueOf(oldUse) < Integer.valueOf(newUse)) {
					ClientFile newClient = clientFileManager.getClientByVersion(newVersion);
					return newClient.getPath();
				}
			}
		}
		return "";
	}

	private LogInHome getLog() {
		HttpServletRequest request = Struts2Utils.getRequest();

		LogInHome log = new LogInHome();
		String requestLink = request.getServletPath() + "?" + request.getQueryString();

		log.setRequestLink(requestLink);

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
