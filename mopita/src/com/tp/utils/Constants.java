package com.tp.utils;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.google.common.collect.Lists;
import com.tp.entity.DownloadType;

public class Constants {

	public final static String FILE_STORAGE = Config.getProperty("file.storage");

	public final static String[] IMG_EXTENSION = { "jpg", "jpeg", "gif", "bmp", "png" };

	public static final String DOT_SEPARATOR = ".";

	public static final String SESS_DEFAULT_STORE = "DEFAULT_STORE";
	public static final String LOCK_STORE = "lock";

	public static final String PARA_LANGUAGE = "l";
	public static final String PARA_DOWNLOAD_METHOD = "dm";
	public static final String PARA_FROM_MARKET = "fm";
	public static final String PARA_IMEI = "imei";
	public static final String PARA_IMSI = "imsi";
	public static final String PARA_STORE_TYPE = "st";
	public static final String PARA_CLIENT_VERSION = "v";
	public static final String PARA_RESOLUTION = "r";

	public static final String QUERY_STRING = "queryString";

	public static final String PREFIX_MARKET_URI = "market://details?id=";
	public static final String LENVOL_STORE = "com.lenovo.leos.appstore";
	public static final String OPPO_NEARME = "com.oppo.market";

	public enum Language {
		ZH("zh"), EN("en"), JP("jp");
		private String value;

		Language(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	public static void setParamInSession(HttpSession session) {
		String language = Struts2Utils.getParameter(PARA_LANGUAGE);
		String fromMarket = Struts2Utils.getParameter(PARA_FROM_MARKET);
		String downMethod = Struts2Utils.getParameter(PARA_DOWNLOAD_METHOD);
		String imei = Struts2Utils.getParameter(PARA_IMEI);
		String imsi = Struts2Utils.getParameter(PARA_IMSI);
		String client_version = Struts2Utils.getParameter(PARA_CLIENT_VERSION);
		String resolution = Struts2Utils.getParameter(PARA_RESOLUTION);
		String store_type = Struts2Utils.getParameter(PARA_STORE_TYPE);

		if (imei != null) {
			session.setAttribute(PARA_IMEI, imei);
		}
		if (imsi != null) {
			session.setAttribute(PARA_IMSI, imsi);
		}
		if (client_version != null) {
			session.setAttribute(PARA_CLIENT_VERSION, client_version);
		}
		if (resolution != null) {
			session.setAttribute(PARA_RESOLUTION, resolution);
		}
		if (store_type != null) {
			session.setAttribute(PARA_STORE_TYPE, store_type);
		} else if (store_type == null && session.getAttribute(PARA_STORE_TYPE) == null) {
			session.setAttribute(PARA_STORE_TYPE, LOCK_STORE);
		}

		if (language != null) {
			if (defaultLanguage().contains(language.toLowerCase())) {
				session.setAttribute(PARA_LANGUAGE, language.toLowerCase());
			} else {
				session.setAttribute(PARA_LANGUAGE, Language.EN.getValue());
			}

		} else {

			session.setAttribute(PARA_LANGUAGE, getLocal());
		}
		getLocal();
		if (fromMarket != null) {
			session.setAttribute(PARA_FROM_MARKET, fromMarket);
		}
		if (downMethod != null) {
			session.setAttribute(PARA_DOWNLOAD_METHOD, downMethod);
		} else if (downMethod == null && session.getAttribute(PARA_DOWNLOAD_METHOD) == null) {
			session.setAttribute(PARA_DOWNLOAD_METHOD, DownloadType.HTTP.getValue());
		}
	}

	private static List<String> defaultLanguage() {
		List<String> languages = Lists.newArrayList();
		languages.add("zh");
		languages.add("en");
		languages.add("jp");
		return languages;
	}

	private static String getLocal() {
		Locale local = ServletActionContext.getContext().getLocale();
		return local.getLanguage();
	}
}
