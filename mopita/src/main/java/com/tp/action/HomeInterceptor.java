package com.tp.action;

import static com.tp.utils.Constants.METHOD_AD_XML;
import static com.tp.utils.Constants.METHOD_GET_CLIENT;
import static com.tp.utils.Constants.PARA_APP_NAME;
import static com.tp.utils.Constants.PARA_CLIENT_VERSION;
import static com.tp.utils.Constants.PARA_CONTENT_VERSION;
import static com.tp.utils.Constants.PARA_DOWNLOAD_METHOD;
import static com.tp.utils.Constants.PARA_DO_TYPE;
import static com.tp.utils.Constants.PARA_FROM_MARKET;
import static com.tp.utils.Constants.PARA_IMEI;
import static com.tp.utils.Constants.PARA_IMSI;
import static com.tp.utils.Constants.PARA_LANGUAGE;
import static com.tp.utils.Constants.PARA_NET_ENVIRONMENT;
import static com.tp.utils.Constants.PARA_OPERATORS;
import static com.tp.utils.Constants.PARA_RESOLUTION;
import static com.tp.utils.Constants.PARA_STORE_TYPE;
import static com.tp.utils.Constants.QUERY_STRING;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.tp.entity.DownloadType;
import com.tp.entity.LogForContent;
import com.tp.entity.LogInHome;
import com.tp.service.LogService;
import com.tp.utils.Constants;
import com.tp.utils.Constants.Language;
import com.tp.utils.DateFormatUtils;
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
			setParamInSession(method, paramMap);
			String language = (String) Struts2Utils.getSessionAttribute(Constants.PARA_LANGUAGE);
			if (language.equalsIgnoreCase("ja")) {
				Struts2Utils.getSession().setAttribute(Constants.PARA_LANGUAGE, Language.JP.getValue());
			}
		}
		if (action instanceof LockerAction) {
			setParamInSession(method, paramMap);
		}
		if (action instanceof SaveLogAction) {
			if (method.equals("client")) {
				method = "getClient2";
				saveLog(method, paramMap);
			} else {

				saveContentLog(paramMap);
			}
		}
		if (action instanceof FileDownloadAction) {
			if (method.equals(METHOD_GET_CLIENT)) {
				saveLog(method, paramMap);
			}
		}
		return invocation.invoke();
	}
	
	private void saveContentLog(Map<String, Object> requestParam) throws Exception {

		LogForContent log = new LogForContent();
		Set<Entry<String, Object>> keys = requestParam.entrySet();
		
		for (Entry<String, Object> e : keys) {
			String k = e.getKey();
			String v = ((String[]) e.getValue())[0];
			if (k.equals(PARA_IMEI)) {
				log.setImei(v);
			} else if (k.equals(PARA_IMSI)) {
				log.setImsi(v);
			} else if (k.equals(PARA_DO_TYPE)) {
				log.setDoType(v);
			} else if (k.equals(PARA_LANGUAGE)) {
				log.setLanguage(v);
			} else if (k.equals(PARA_RESOLUTION)) {
				log.setResolution(v);
			} else if (k.equals(PARA_NET_ENVIRONMENT)) {
				log.setNetEnv(v);
			} else if (k.equals(PARA_APP_NAME)) {
				log.setAppName(v);
			} else if (k.equals(PARA_OPERATORS)) {
				log.setOperators(v);
			} else if (k.equals(PARA_CONTENT_VERSION)) {
				log.setContentVersion(v);
			} else if (k.equals(PARA_CLIENT_VERSION)) {
				log.setClientVersion(v);
			} else if (k.equals(PARA_FROM_MARKET)) {
				log.setFromMarket(v);
			}
		}
		logService.saveLogContent(log);
	}

	private void saveLog(String requestMethod, Map<String, Object> requestParam) throws Exception {
		if (requestMethod.equals(METHOD_AD_XML)) {
			return;
		}
		if (requestMethod.equals("execute") && requestParam.get("f") == null) {
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
		log.setRequestParams(removeLastChara(buffer.toString()));
		log.setCreateTime(DateFormatUtils.convert(new Date()));
		logService.saveLogInHome(log);
	}

	private String removeLastChara(String str) {

		return StringUtils.substring(str, 0, str.length() - 1);
	}

	private void setParamInSession(String method, Map<String, Object> params) {
		HttpSession session = Struts2Utils.getSession();
		String language = Struts2Utils.getParameter(PARA_LANGUAGE);
		String dm = Struts2Utils.getParameter(PARA_DOWNLOAD_METHOD);
		if (language == null || language.isEmpty()) {
			language = Constants.getLocal();
		} else if (!(defaultLanguage().contains(language.toLowerCase()))) {
			language = Language.EN.getValue();
		}
		if (dm == null || dm.isEmpty()) {
			dm = DownloadType.HTTP.getValue();
		}
		session.setAttribute(PARA_DOWNLOAD_METHOD, dm);
		session.setAttribute(PARA_LANGUAGE, language);

		StringBuilder buffer = new StringBuilder();
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			String key = entry.getKey();
			String value = ((String[]) entry.getValue())[0];

			if (key.equals(PARA_STORE_TYPE)) {
				session.setAttribute(PARA_STORE_TYPE, value);
			}

			if (key.equals(PARA_IMEI)) {
				session.setAttribute(PARA_IMEI, value);
			}
			if (key.equals(PARA_IMSI)) {
				session.setAttribute(PARA_IMSI, value);
			}
			if (key.equals(PARA_CLIENT_VERSION)) {
				session.setAttribute(PARA_CLIENT_VERSION, value);
			}
			if (key.equals(PARA_RESOLUTION)) {
				session.setAttribute(PARA_RESOLUTION, value);
			}
			if (key.equals(PARA_FROM_MARKET)) {
				session.setAttribute(PARA_FROM_MARKET, value);
			}

			Locale local = new Locale(language);
			ServletActionContext.getContext().setLocale(local);

			buffer.append(entry.getKey() + "=" + value).append("&");
		}

		if (method.equals("execute")) {
			session.setAttribute(QUERY_STRING, removeLastChara(buffer.toString()));
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
