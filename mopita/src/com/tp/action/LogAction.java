package com.tp.action;

import org.apache.struts2.convention.annotation.Namespace;

import com.opensymphony.xwork2.ActionSupport;
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

		return null;
	}
}
