package com.tp.utils;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

public class Constants {

	public final static String FILE_STORAGE = Config.getProperty("file.storage");

	public static final String NAV_FOLDER = "nav";

	public final static String[] IMG_EXTENSION = { "jpg", "jpeg", "gif", "bmp", "png" };

	public static final String ENCODE_UTF_8 = "UTF-8";

	public static final String DOT_SEPARATOR = ".";

	public static final String ST_LOCK = "lock";
	public static final String JP_LOCKER = "jplocker";
	public static final String DM_LOCKER = "dmlocker";

	public static final String ID_LOCK = "lockId";
	public static final String ID_JPLOCKER = "jplockerId";

	public static final String PARA_LANGUAGE = "l";
	public static final String PARA_DOWNLOAD_METHOD = "dm";
	public static final String PARA_FROM_MARKET = "fm";
	public static final String PARA_IMEI = "imei";
	public static final String PARA_IMSI = "imsi";
	public static final String PARA_STORE_TYPE = "st";
	public static final String PARA_CLIENT_VERSION = "v";
	public static final String PARA_CONTENT_VERSION = "cv";
	public static final String PARA_CLIENT_TYPE = "ct";
	public static final String PARA_RESOLUTION = "r";

	public static final String QUERY_STRING = "queryString";
	public static final String AD_XML = "adXml";
	public static final String GET_CLIENT = "getClient";

	public static final String LOCKER_STANDARD = "st";
	public static final String LOCKER_JP = "jp";
	public static final String LOCKER_DM = "dm";

	public static final String LENVOL_STORE = "com.lenovo.leos.appstore";
	public static final String OPPO_NEARME = "com.oppo.market";
	public static final String MARKET_GOOGLE = "com.android.vending";

	public enum Language {
		ZH("zh"), EN("en"), JP("jp"), JA("ja");
		private String value;

		Language(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	public static String getDomain() {
		HttpServletRequest request = Struts2Utils.getRequest();
		StringBuilder buffer = new StringBuilder();
		buffer.append("http://").append(request.getServerName()).append(":").append(request.getLocalPort())
				.append(request.getContextPath());
		return buffer.toString();
	}

	public static String getLocal() {
		Locale local = ServletActionContext.getContext().getLocale();
		return local.getLanguage();
	}
}
