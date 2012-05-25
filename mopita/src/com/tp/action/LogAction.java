package com.tp.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.tp.entity.LogFromClient;
import com.tp.service.LogService;
import com.tp.utils.Struts2Utils;

@Namespace("/log")
public class LogAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private static final String PARA_IMEI = "imei";
	private static final String PARA_IMSI = "imsi";
	private static final String PARA_STORE_TYPE = "st";
	private static final String PARA_DOWNLOAD_TYPE = "dm";
	private static final String PARA_LANGUAGE = "l";
	private static final String PARA_CLIENT_VERSION = "v";
	private static final String PARA_RESOLUTION = "r";
	private static final String PARA_FROM_MARKET = "fm";

	private List<LogFromClient> logs;
	private LogService logService;

	@Override
	public String execute() throws Exception {
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
		logService.save(entity);
		return null;
	}

	public String list() {
		logs = logService.getAll();
		return SUCCESS;
	}

	public List<LogFromClient> getLogs() {
		return logs;
	}

	@Autowired
	public void setLogService(LogService logService) {
		this.logService = logService;
	}
}
