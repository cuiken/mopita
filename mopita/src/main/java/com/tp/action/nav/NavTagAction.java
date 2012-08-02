package com.tp.action.nav;

import java.io.File;
import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.tp.action.CRUDActionSupport;
import com.tp.entity.nav.NavTag;
import com.tp.service.nav.NavigatorService;

@Namespace("/nav")
@Results({ @Result(name = CRUDActionSupport.RELOAD, location = "nav-tag.action", type = "redirect") })
public class NavTagAction extends CRUDActionSupport<NavTag> {

	private static final long serialVersionUID = 1L;
	private NavTag entity;
	private Long id;
	private List<NavTag> tags = Lists.newArrayList();
	private File image;
	private NavigatorService navigatorService;

	@Override
	public NavTag getModel() {

		return entity;
	}

	@Override
	public String list() throws Exception {
		tags = navigatorService.getAllTags();
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {

		return INPUT;
	}

	@Override
	public String save() throws Exception {

		return RELOAD;
	}

	@Override
	public String delete() throws Exception {

		return RELOAD;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (id == null) {
			entity = new NavTag();
		} else {
			entity = navigatorService.getNavTag(id);
		}
	}

	public List<NavTag> getTags() {
		return tags;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Autowired
	public void setNavigatorService(NavigatorService navigatorService) {
		this.navigatorService = navigatorService;
	}
}
