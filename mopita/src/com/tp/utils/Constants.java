package com.tp.utils;

public class Constants {

	public final static String FILE_STORAGE = Config.getProperty("file.storage");

	public final static String[] IMG_EXTENSION = { "jpg", "jpeg", "gif", "bmp", "png" };

	public static final String DOT_SEPARATOR = ".";

	public final static String ZIP = "zip";
	public final static String UX = "ux";
	public final static String APK = "apk";
	public final static String PREVIEW_CLIENT = "pre_c";
	public final static String PREVIEW_WEB = "pre_w";
	public static final String ICON = "icon_";
	public static final String AD = "ad_";

	public static final String LANGUAGE_KEY = "lan";
	public static final String ZH = "ZH";

	public static final String PREFIX_MARKET_URI = "market://details?id=";

	public static String getLanguage() {
		String language = Struts2Utils.getParameter("l");

		if (language != null) {
			Struts2Utils.getSession().setAttribute(Constants.LANGUAGE_KEY, language.toUpperCase());
		} else if (language == null && Struts2Utils.getSession().getAttribute("lan") == null) {
			Struts2Utils.getSession().setAttribute(Constants.LANGUAGE_KEY, Constants.ZH);
		}
		return (String) Struts2Utils.getSession().getAttribute(Constants.LANGUAGE_KEY);
	}
}
