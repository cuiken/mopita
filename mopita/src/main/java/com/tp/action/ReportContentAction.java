package com.tp.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.opensymphony.xwork2.ActionSupport;
import com.tp.entity.LogCountContent;
import com.tp.orm.Page;
import com.tp.orm.PropertyFilter;
import com.tp.orm.PageRequest.Sort;
import com.tp.service.LogService;
import com.tp.utils.Struts2Utils;

@Namespace("/report")
public class ReportContentAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private Page<LogCountContent> page = new Page<LogCountContent>();
	private LogService logService;
	private List<Integer> sliders = Lists.newArrayList();

	public String execute() {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(Struts2Utils.getRequest());
		if (!page.isOrderBySetted()) {
			page.setOrderBy("createTime");
			page.setOrderDir(Sort.DESC);
		}
		page = logService.searchLogCountContent(page, filters);
		sliders = page.getSlider(10);
		return SUCCESS;
	}

	@Autowired
	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public Page<LogCountContent> getPage() {
		return page;
	}

	public List<Integer> getSliders() {
		return sliders;
	}
}
