package com.tp.action.nav;

import java.io.File;
import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.tp.action.CRUDActionSupport;
import com.tp.dao.HibernateUtils;
import com.tp.entity.nav.Board;
import com.tp.entity.nav.NavTag;
import com.tp.entity.nav.Navigator;
import com.tp.service.nav.NavigatorService;
import com.tp.utils.Constants;
import com.tp.utils.FileUtils;

@Namespace("/nav")
@Results({ @Result(name = CRUDActionSupport.RELOAD, location = "navigator.action", type = "redirect") })
public class NavigatorAction extends CRUDActionSupport<Navigator> {

	private static final long serialVersionUID = 1L;

	private Navigator entity;
	private Long id;
	private List<Navigator> navigators;
	private File upload;
	private String uploadFileName;
	private NavigatorService navigatorService;

	private List<Long> checkedTagIds;
	private List<Long> checkedBoardIds;

	@Override
	public Navigator getModel() {

		return entity;
	}

	@Override
	public String list() throws Exception {
		navigators = navigatorService.getAllNav();
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
		checkedBoardIds = entity.getCheckedBoardIds();
		checkedTagIds = entity.getCheckedTagIds();
		return INPUT;
	}

	@Override
	public String save() throws Exception {
		if (upload != null) {
			File destFile = FileUtils.copyFile(upload, uploadFileName);
			entity.setPicAddr(Constants.NAV_FOLDER + File.separator + destFile.getName());
		}
		HibernateUtils.mergeByCheckedIds(entity.getBoards(), checkedBoardIds, Board.class);
		HibernateUtils.mergeByCheckedIds(entity.getTags(), checkedTagIds, NavTag.class);
		navigatorService.saveNav(entity);
		return RELOAD;
	}

	@Override
	public String delete() throws Exception {
		navigatorService.deleteNav(id);
		return RELOAD;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (id == null) {
			entity = new Navigator();
		} else {
			entity = navigatorService.getNav(id);
		}

	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public List<Navigator> getNavigators() {
		return navigators;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Board> getAllBoards() {
		return navigatorService.getAllBoards();
	}

	public List<NavTag> getAllTags() {
		return navigatorService.getAllTags();
	}

	public List<Long> getCheckedTagIds() {
		return checkedTagIds;
	}

	public void setCheckedTagIds(List<Long> checkedTagIds) {
		this.checkedTagIds = checkedTagIds;
	}

	public List<Long> getCheckedBoardIds() {
		return checkedBoardIds;
	}

	public void setCheckedBoardIds(List<Long> checkedBoardIds) {
		this.checkedBoardIds = checkedBoardIds;
	}

	@Autowired
	public void setNavigatorService(NavigatorService navigatorService) {
		this.navigatorService = navigatorService;
	}
}
