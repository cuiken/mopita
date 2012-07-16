package com.tp.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.opensymphony.xwork2.ActionSupport;
import com.tp.entity.LogCountClient;
import com.tp.entity.LogCountContent;
import com.tp.orm.Page;
import com.tp.orm.PropertyFilter;
import com.tp.orm.PageRequest.Sort;
import com.tp.service.LogService;
import com.tp.utils.Struts2Utils;

@Namespace("/report")
public class ReportAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private LogService logService;
	private Page<LogCountClient> cpage = new Page<LogCountClient>();
	private Page<LogCountContent> ctpage = new Page<LogCountContent>();
	private List<Integer> sliders = Lists.newArrayList();

	public String client() {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(Struts2Utils.getRequest());
		if (!cpage.isOrderBySetted()) {
			cpage.setOrderBy("createTime");
			cpage.setOrderDir(Sort.DESC);
		}
		cpage = logService.searchLogCountClient(cpage, filters);
		return "client";
	}

	public String content() {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(Struts2Utils.getRequest());
		if (!ctpage.isOrderBySetted()) {
			ctpage.setOrderBy("createTime");
			ctpage.setOrderDir(Sort.DESC);
		}
		ctpage = logService.searchLogCountContent(ctpage, filters);
		sliders = ctpage.getSlider(10);
		return "content";
	}

	@Autowired
	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public Page<LogCountClient> getCpage() {
		return cpage;
	}

	public Page<LogCountContent> getCtpage() {
		return ctpage;
	}

	public List<Integer> getSliders() {
		return sliders;
	}
}
