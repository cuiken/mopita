package com.tp.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.tp.entity.LogFromClient;
import com.tp.orm.Page;
import com.tp.orm.PageRequest.Sort;
import com.tp.orm.PropertyFilter;
import com.tp.service.LogService;
import com.tp.utils.Constants;
import com.tp.utils.Struts2Utils;

@Namespace("/log")
public class LogAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private static final String PARA_IMEI = Constants.PARA_IMEI;
	private static final String PARA_IMSI = Constants.PARA_IMSI;
	private static final String PARA_STORE_TYPE = Constants.PARA_STORE_TYPE;
	private static final String PARA_DOWNLOAD_TYPE = Constants.PARA_DOWNLOAD_METHOD;
	private static final String PARA_LANGUAGE = Constants.PARA_LANGUAGE;
	private static final String PARA_CLIENT_VERSION = Constants.PARA_CLIENT_VERSION;
	private static final String PARA_RESOLUTION = Constants.PARA_RESOLUTION;
	private static final String PARA_FROM_MARKET = Constants.PARA_FROM_MARKET;

	private Page<LogFromClient> page = new Page<LogFromClient>();
	private List<Integer> sliders;
	private LogService logService;

	@Override
	public String execute() throws Exception {

		return save();
	}

	public String list() throws Exception {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(Struts2Utils.getRequest());
		if (!page.isOrderBySetted()) {
			page.setOrderBy("createTime");
			page.setOrderDir(Sort.DESC);
		}
		page = logService.searchLogFromClient(page, filters);
		sliders = page.getSlider((int) page.getTotalItems());
		return SUCCESS;
	}

	public String save() throws Exception {
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
		logService.saveLogFromClent(entity);
		return null;
	}

	public Page<LogFromClient> getPage() {
		return page;
	}

	public List<Integer> getSliders() {
		return sliders;
	}

	@Autowired
	public void setLogService(LogService logService) {
		this.logService = logService;
	}
}
