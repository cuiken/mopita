package com.tp.action.nav;

import java.io.File;
import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.tp.action.CRUDActionSupport;
import com.tp.entity.nav.Board;
import com.tp.entity.nav.NavTag;
import com.tp.service.nav.NavigatorService;
import com.tp.utils.Constants;
import com.tp.utils.FileUtils;

@Namespace("/nav")
@Results({ @Result(name = CRUDActionSupport.RELOAD, location = "nav-tag.action", type = "redirect") })
public class NavTagAction extends CRUDActionSupport<NavTag> {

	private static final long serialVersionUID = 1L;

	private NavTag entity;
	private Long id;
	private Long boardId;
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

		if (upload != null) {
			File destFile = FileUtils.copyFile(upload, uploadFileName);
			entity.setPicAddr(Constants.NAV_FOLDER + File.separator + destFile.getName());
		}
		if (boardId != null) {
			Board board = navigatorService.getBoard(boardId);
			entity.setBoard(board);
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

	public List<Board> getBoards() {
		return navigatorService.getAllBoards();
	}

	public void setBoardId(Long boardId) {
		this.boardId = boardId;
	}

	@Autowired
	public void setNavigatorService(NavigatorService navigatorService) {
		this.navigatorService = navigatorService;
	}
}
