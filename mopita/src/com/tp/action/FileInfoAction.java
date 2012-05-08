package com.tp.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.tp.entity.FileMultipleInfo;
import com.tp.entity.ThemeFile;
import com.tp.service.FileInfoObservable;
import com.tp.service.FileManager;

@Namespace("/file")
@Results( { @Result(name = CRUDActionSupport.RELOAD, location = "file-info.action", params = {
		"themeId", "${themeId}" }, type = "redirect") })
public class FileInfoAction extends CRUDActionSupport<FileMultipleInfo> {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long themeId;
	private FileMultipleInfo entity;

	private FileManager fileManager;
	private FileInfoObservable observer;

	@Override
	public String delete() throws Exception {
		observer.deleteFileInfo(id);
		return RELOAD;
	}

	@Override
	public String input() throws Exception {

		return INPUT;
	}

	@Override
	public String list() throws Exception {

		return SUCCESS;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (id == null) {
			entity = new FileMultipleInfo();
		} else {
			entity = fileManager.getFileInfo(id);
		}

	}

	@Override
	public String save() throws Exception {
		ThemeFile file = fileManager.getThemeFile(themeId);
		List<FileMultipleInfo> existInfo = file.getFileInfo();
		if (existInfo.contains(entity)) {
			addActionMessage("信息已存在");
		} else {
			entity.setTheme(file);
			observer.saveFileInfo(entity);
			addActionMessage("保存成功");
		}

		return RELOAD;
	}

	@Override
	public FileMultipleInfo getModel() {

		return entity;
	}

	@Autowired
	public void setFileManager(FileManager fileManager) {
		this.fileManager = fileManager;
	}

	@Autowired
	public void setObserver(FileInfoObservable observer) {
		this.observer = observer;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Long getThemeId() {
		return themeId;
	}

	public void setThemeId(Long themeId) {
		this.themeId = themeId;
	}

	public List<FileMultipleInfo> getAllInfo() {
		ThemeFile file = fileManager.getThemeFile(themeId);
		return file.getFileInfo();
	}

}
