package com.tp.action.nav;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.tp.action.CRUDActionSupport;
import com.tp.entity.nav.NavTag;
import com.tp.service.nav.NavigatorService;
import com.tp.utils.Constants;
import com.tp.utils.UUIDGenerator;

@Namespace("/nav")
@Results({ @Result(name = CRUDActionSupport.RELOAD, location = "nav-tag.action", type = "redirect") })
public class NavTagAction extends CRUDActionSupport<NavTag> {

	private static final long serialVersionUID = 1L;

	private static final String NAV_FOLDER = "nav";

	private NavTag entity;
	private Long id;
	private List<NavTag> tags = Lists.newArrayList();
	private File upload;
	private String uploadFileName;
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
		File targetDir = new File(Constants.FILE_STORAGE, NAV_FOLDER);
		String fileName = UUIDGenerator.generateUUID();
		String ext = StringUtils.substringAfterLast(uploadFileName, Constants.DOT_SEPARATOR);
		File destFile = new File(targetDir, fileName + Constants.DOT_SEPARATOR + ext);
		FileUtils.copyFile(upload, destFile);
		if (upload != null) {
			entity.setPicAddr(NAV_FOLDER + File.separator + destFile.getName());
		}
		navigatorService.saveTag(entity);
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

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	@Autowired
	public void setNavigatorService(NavigatorService navigatorService) {
		this.navigatorService = navigatorService;
	}
}
