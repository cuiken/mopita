package com.tp.utils;

import javax.servlet.http.HttpSession;

import com.tp.entity.DownloadType;

public class Constants {

	public final static String FILE_STORAGE = Config.getProperty("file.storage");

	public final static String[] IMG_EXTENSION = { "jpg", "jpeg", "gif", "bmp", "png" };

	public static final String DOT_SEPARATOR = ".";

	public static final String SESS_KEY_LANGUAGE = "lan";
	public static final String SESS_KEY_MARKET = "fm";
	public static final String SESS_KEY_DT = "dt";
	public static final String SESS_DEFAULT_STORE="DEFAULT_STORE";

	public static final String PARA_LANGUAGE = "l";
	public static final String PARA_DOWNLOAD_METHOD = "dm";
	public static final String PARA_FROM_MARKET = "fm";

	public static final String PREFIX_MARKET_URI = "market://details?id=";

	public enum Language {
		ZH("ZH");
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

		if (language != null) {
			session.setAttribute(SESS_KEY_LANGUAGE, language.toUpperCase());
		} else if (language == null && Struts2Utils.getSession().getAttribute(SESS_KEY_LANGUAGE) == null) {
			session.setAttribute(SESS_KEY_LANGUAGE, Language.ZH.getValue());
		}
		if (fromMarket != null) {
			session.setAttribute(SESS_KEY_MARKET, fromMarket);
		}
		if (downMethod != null) {
			session.setAttribute(SESS_KEY_DT, downMethod);
		} else {
			session.setAttribute(SESS_KEY_DT, DownloadType.HTTP.getValue());
		}
	}
}
