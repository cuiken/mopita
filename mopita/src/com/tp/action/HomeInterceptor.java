package com.tp.action;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.tp.entity.DownloadType;
import com.tp.entity.LogInHome;
import com.tp.service.LogService;
import com.tp.utils.Constants;
import com.tp.utils.Struts2Utils;
import com.tp.utils.Constants.Language;

import edu.emory.mathcs.backport.java.util.Arrays;
import static com.tp.utils.Constants.*;

public class HomeInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

	private LogService logService;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		if (invocation.getAction() instanceof HomeAction) {
			String method = invocation.getProxy().getMethod();
			Map<String, Object> paramMap = invocation.getInvocationContext().getParameters();
			Set<Entry<String, Object>> keys = paramMap.entrySet();
			for (Entry<String, Object> e : keys) {
				String k = e.getKey().toString();
				String v = e.getValue().toString();

			}
		}
		return invocation.invoke();
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

		if (fromMarket != null) {
			session.setAttribute(PARA_FROM_MARKET, fromMarket);
		}
		if (downMethod != null) {
			session.setAttribute(PARA_DOWNLOAD_METHOD, downMethod);
		} else if (downMethod == null && session.getAttribute(PARA_DOWNLOAD_METHOD) == null) {
			session.setAttribute(PARA_DOWNLOAD_METHOD, DownloadType.HTTP.getValue());
		}
	}

	@SuppressWarnings("unchecked")
	private static List<String> defaultLanguage() {
		
		return Arrays.asList(Language.values());

	}

	private static String getLocal() {
		Locale local = ServletActionContext.getContext().getLocale();
		return local.getLanguage();
	}

	private void writeLog(HttpSession session) {
		HttpServletRequest request = Struts2Utils.getRequest();

		LogInHome log = new LogInHome();
		String requestLink = request.getServletPath() + "?" + request.getQueryString();
		String imei = (String) session.getAttribute(Constants.PARA_IMEI);
		String imsi = (String) session.getAttribute(Constants.PARA_IMSI);
		String lang = (String) session.getAttribute(Constants.PARA_LANGUAGE);
		String fromMarket = (String) session.getAttribute(Constants.PARA_FROM_MARKET);
		String downType = (String) session.getAttribute(Constants.PARA_DOWNLOAD_METHOD);
		String clientVersion = (String) session.getAttribute(Constants.PARA_CLIENT_VERSION);
		String reso = (String) session.getAttribute(Constants.PARA_RESOLUTION);
		String storeType = (String) session.getAttribute(Constants.PARA_STORE_TYPE);
		log.setRequestLink(requestLink);
		log.setClientVersion(clientVersion);
		log.setStoreType(storeType);
		log.setDownType(downType);
		log.setFromMarket(fromMarket);
		log.setResolution(reso);
		log.setImei(imei);
		log.setImsi(imsi);
		log.setLanguage(lang);
		logService.saveLogInHome(log);
	}

	@Autowired
	public void setLogService(LogService logService) {
		this.logService = logService;
	}
}
