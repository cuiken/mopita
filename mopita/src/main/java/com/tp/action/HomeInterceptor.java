package com.tp.action;

import static com.tp.utils.Constants.AD_XML;
import static com.tp.utils.Constants.GET_CLIENT;
import static com.tp.utils.Constants.PARA_CLIENT_VERSION;
import static com.tp.utils.Constants.PARA_DOWNLOAD_METHOD;
import static com.tp.utils.Constants.PARA_FROM_MARKET;
import static com.tp.utils.Constants.PARA_IMEI;
import static com.tp.utils.Constants.PARA_IMSI;
import static com.tp.utils.Constants.PARA_LANGUAGE;
import static com.tp.utils.Constants.PARA_RESOLUTION;
import static com.tp.utils.Constants.PARA_STORE_TYPE;
import static com.tp.utils.Constants.QUERY_STRING;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.tp.entity.DownloadType;
import com.tp.entity.LogInHome;
import com.tp.service.LogService;
import com.tp.utils.Constants;
import com.tp.utils.DateFormatUtils;
import com.tp.utils.Constants.Language;
import com.tp.utils.Struts2Utils;

public class HomeInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

	private LogService logService;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Object action = invocation.getAction();
		String method = invocation.getProxy().getMethod();
		Map<String, Object> paramMap = invocation.getInvocationContext().getParameters();
		if (action instanceof HomeAction || action instanceof JplockerAction) {
			saveLog(method, paramMap);
			setParamInSession(method);
			String language = (String) Struts2Utils.getSessionAttribute(Constants.PARA_LANGUAGE);
			if (language.equalsIgnoreCase("ja")) {
				Struts2Utils.getSession().setAttribute(Constants.PARA_LANGUAGE, Language.JP.getValue());
			}
		}
		if (action instanceof FileDownloadAction) {
			if (method.equals(GET_CLIENT)) {
				saveLog(method, paramMap);
			}
		}
		return invocation.invoke();
	}

	private void saveLog(String requestMethod, Map<String, Object> requestParam) throws Exception {
		if (requestMethod.equals(AD_XML)) {
			return;
		}
		StringBuilder buffer = new StringBuilder();
		LogInHome log = new LogInHome();
		log.setRequestMethod(requestMethod);
		Set<Entry<String, Object>> keys = requestParam.entrySet();
		for (Entry<String, Object> e : keys) {
			String k = e.getKey();
			String v = ((String[]) e.getValue())[0];
			if (v != null && !v.isEmpty()) {
				v = new String(v.getBytes("iso-8859-1"), Constants.ENCODE_UTF_8);
			}
			if (k.equals(PARA_IMEI)) {
				log.setImei(v);
			} else if (k.equals(PARA_IMSI)) {
				log.setImsi(v);
			} else if (k.equals(PARA_DOWNLOAD_METHOD)) {
				log.setDownType(v);
			} else if (k.equals(PARA_LANGUAGE)) {
				log.setLanguage(v);
			} else if (k.equals(PARA_RESOLUTION)) {
				log.setResolution(v);
			} else if (k.equals(PARA_STORE_TYPE)) {
				log.setStoreType(v);
			} else if (k.equals(PARA_CLIENT_VERSION)) {
				log.setClientVersion(v);
			} else if (k.equals(PARA_FROM_MARKET)) {
				log.setFromMarket(v);
			} else {
				buffer.append(k).append(":").append(v).append(",");
			}
		}
		log.setRequestParams(removeLastComma(buffer.toString()));
		log.setCreateTime(DateFormatUtils.convert(new Date()));
		logService.saveLogInHome(log);
	}

	private String removeLastComma(String str) {
		int index = StringUtils.lastIndexOf(str, ",");
		return StringUtils.substring(str, 0, index);
	}

	private String getQueryString() {
		HttpServletRequest request = Struts2Utils.getRequest();
		return request.getQueryString();

	}

	private void setParamInSession(String method) {
		HttpSession session = Struts2Utils.getSession();
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
		}

		if (language != null) {

			if (defaultLanguage().contains(language.toLowerCase())) {
				session.setAttribute(PARA_LANGUAGE, language.toLowerCase());
			} else {
				session.setAttribute(PARA_LANGUAGE, Language.EN.getValue());
			}

		} else {

			session.setAttribute(PARA_LANGUAGE, Constants.getLocal());
		}
		Locale local = new Locale((String) session.getAttribute(PARA_LANGUAGE));
		ServletActionContext.getContext().setLocale(local);
		if (fromMarket != null) {
			session.setAttribute(PARA_FROM_MARKET, fromMarket);
		}
		if (downMethod != null) {
			session.setAttribute(PARA_DOWNLOAD_METHOD, downMethod);
		} else if (downMethod == null && session.getAttribute(PARA_DOWNLOAD_METHOD) == null) {
			session.setAttribute(PARA_DOWNLOAD_METHOD, DownloadType.HTTP.getValue());
		}

		if (method.equals("execute")) {
			session.setAttribute(QUERY_STRING, getQueryString());
		}
	}

	private static List<String> defaultLanguage() {

		List<String> languages = Lists.newArrayList();
		Language[] lans = Language.values();
		for (Language l : lans) {
			languages.add(l.getValue());
		}
		return languages;

	}

	@Autowired
	public void setLogService(LogService logService) {
		this.logService = logService;
	}

}
