package com.tp.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.tp.entity.FileStoreInfo;
import com.tp.service.FileStoreInfoManager;

@Namespace("/file")
public class FileStoreInfoAction extends CRUDActionSupport<FileStoreInfo>{

	private static final long serialVersionUID = 1L;
	
	private FileStoreInfo entity;
	private Long id;
	private Long themeId;
	private Long storeId;
	private List<FileStoreInfo> fileInfo=Lists.newArrayList();
	private FileStoreInfoManager storeInfoManager;

	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String input() throws Exception {
		
		return INPUT;
	}

	@Override
	public String list() throws Exception {
		fileInfo=storeInfoManager.getByTheme(themeId,storeId);
		return SUCCESS;
	}

	@Override
	protected void prepareModel() throws Exception {
		if(id==null){
			entity=new FileStoreInfo();
		}else{
			entity=storeInfoManager.get(id);
		}
		
	}

	@Override
	public String save() throws Exception {
		for(FileStoreInfo info:fileInfo){
			storeInfoManager.save(info);
		}
		addActionMessage("修改成功");
		return null;
	}

	@Override
	public FileStoreInfo getModel() {
		
		return entity;
	}

	@Autowired
	public void setStoreInfoManager(FileStoreInfoManager storeInfoManager) {
		this.storeInfoManager = storeInfoManager;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setThemeId(Long themeId) {
		this.themeId = themeId;
	}
	
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
	
	public List<FileStoreInfo> getFileInfo() {
		return fileInfo;
	}
	
	public void setFileInfo(List<FileStoreInfo> fileInfo) {
		this.fileInfo = fileInfo;
	}
}
