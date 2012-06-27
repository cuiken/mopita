package com.tp.action;

import static com.tp.utils.Constants.AD_XML;
import static com.tp.utils.Constants.GET_CLIENT;
import static com.tp.utils.Constants.LOCK_STORE;
import static com.tp.utils.Constants.PARA_CLIENT_VERSION;
import static com.tp.utils.Constants.PARA_DOWNLOAD_METHOD;
import static com.tp.utils.Constants.PARA_FROM_MARKET;
import static com.tp.utils.Constants.PARA_IMEI;
import static com.tp.utils.Constants.PARA_IMSI;
import static com.tp.utils.Constants.PARA_LANGUAGE;
import static com.tp.utils.Constants.PARA_RESOLUTION;
import static com.tp.utils.Constants.PARA_STORE_TYPE;
import static com.tp.utils.Constants.QUERY_STRING;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.tp.entity.DownloadType;
import com.tp.entity.LogInHome;
import com.tp.entity.Store;
import com.tp.service.CategoryManager;
import com.tp.service.LogService;
import com.tp.utils.Constants;
import com.tp.utils.Constants.Language;
import com.tp.utils.Struts2Utils;

public class HomeInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;
	private Logger logger = LoggerFactory.getLogger(getClass());

	private LogService logService;
	private CategoryManager categoryManager;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Object action = invocation.getAction();
		String method = invocation.getProxy().getMethod();
		Map<String, Object> paramMap = invocation.getInvocationContext().getParameters();
		if (action instanceof HomeAction || action instanceof JplockerAction) {
			saveLog(method, paramMap);
			setParamInSession(method);
		}
		if (action instanceof FileDownloadAction) {
			if (method.equals(GET_CLIENT)) {
				saveLog(method, paramMap);
			}
		}
		return invocation.invoke();
	}

	private void saveLog(String requestMethod, Map<String, Object> requestParam) {
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
		} else if (store_type == null && session.getAttribute(PARA_STORE_TYPE) == null) {
			session.setAttribute(PARA_STORE_TYPE, LOCK_STORE);
		}

		setDefaultStore(session);
		if (language != null) {

			if (defaultLanguage().contains(language.toLowerCase())) {
				session.setAttribute(PARA_LANGUAGE, language.toLowerCase());
			} else {
				session.setAttribute(PARA_LANGUAGE, Language.EN.getValue());
			}

		} else {

			session.setAttribute(PARA_LANGUAGE, getLocal());
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

	private void setDefaultStore(HttpSession session) {
		try {
			String storeType = (String) session.getAttribute(PARA_STORE_TYPE);
			Store store = categoryManager.getStoreByValue(storeType);
			session.setAttribute(Constants.SESS_DEFAULT_STORE, store.getId());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
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

	private static String getLocal() {
		Locale local = ServletActionContext.getContext().getLocale();
		return local.getLanguage();
	}

	@Autowired
	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	@Autowired
	public void setCategoryManager(CategoryManager categoryManager) {
		this.categoryManager = categoryManager;
	}
}
