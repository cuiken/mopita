package com.tp.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.tp.dao.HibernateUtils;
import com.tp.entity.Shelf;
import com.tp.entity.Store;
import com.tp.entity.ThemeFile;
import com.tp.service.CategoryManager;
import com.tp.service.FileManager;
import com.tp.utils.Struts2Utils;

@Namespace("/category")
@Results( { @Result(name = CRUDActionSupport.RELOAD, location = "shelf.action", type = "redirect") })
public class ShelfAction extends CRUDActionSupport<Shelf> {

	private static final long serialVersionUID = 1L;
	private static final String MANAGE = "manage";

	private Long id;
	private Shelf entity;
	private Long checkedStoreId;
	private List<Shelf> shelfs;
	private List<ThemeFile> onShelfFiles;
	private List<ThemeFile> remainFiles;
	private List<Long> checkedFileIds;
	private CategoryManager categoryManager;
	private FileManager fileManager;

	@Override
	public String delete() throws Exception {
		categoryManager.deleteShelf(id);
		addActionMessage("删除成功");
		return RELOAD;
	}

	@Override
	public String input() throws Exception {
		checkedStoreId = entity.getCheckedId();
		return INPUT;
	}

	@Override
	public String list() throws Exception {
		//shelfs = categoryManager.getAllShelf();
		return SUCCESS;
	}
	
	public String filterShelf()throws Exception{
		Store store=categoryManager.getStore(checkedStoreId);
		List<Shelf> shelfs=store.getShelfs();
		String json=categoryManager.jsonString(shelfs);
		Struts2Utils.renderJson(json);
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (id == null) {
			entity = new Shelf();
		} else {
			entity = categoryManager.getShelf(id);
		}

	}

	@Override
	public String save() throws Exception {
		Store store = categoryManager.getStore(checkedStoreId);
		entity.setStore(store);
		categoryManager.saveShelf(entity);
		addActionMessage("保存成功");
		return RELOAD;
	}
	
	public String saveFile(){
		fileManager.merge(entity, checkedFileIds);
		HibernateUtils.mergeByCheckedIds(entity.getThemes(), checkedFileIds, ThemeFile.class);
		categoryManager.saveShelf(entity);
		return RELOAD;
	}

	public String manage() throws Exception {
		
		this.onShelfFiles = entity.getThemes();
		List<ThemeFile> allFiles = fileManager.getAllThemeFile();
		this.remainFiles = fileManager.getRemainFiles(allFiles, onShelfFiles);
		return MANAGE;
	}
	
	public String getRemainFile(){
		Shelf sh=categoryManager.getShelf(id);
		this.onShelfFiles = sh.getThemes();
		List<ThemeFile> allFiles = fileManager.getAllThemeFile();
		this.remainFiles = fileManager.getRemainFiles(allFiles, onShelfFiles);
		String json=fileManager.jsonString(remainFiles);
		Struts2Utils.renderJson(json);
		return null;
	}
	public String getOnShelfFile(){
		Shelf sh=categoryManager.getShelf(id);
		this.onShelfFiles = sh.getThemes();
		String json=fileManager.jsonString(onShelfFiles);
		Struts2Utils.renderJson(json);
		return null;
	}
	
	public String checkShelfName() throws Exception{
		String newShelfName = new String(Struts2Utils.getParameter("name").getBytes("iso-8859-1"),"utf-8");
		String oldShelfName = new String(Struts2Utils.getParameter("oldName").getBytes("iso-8859-1"),"utf-8");
		Long storeId=Long.valueOf(Struts2Utils.getParameter("storeId"));
		if(categoryManager.isShelfUnique(newShelfName,oldShelfName, storeId)){
			Struts2Utils.renderText("true");
		}else{
			Struts2Utils.renderText("false");
		}
		return null;
	}
	
	public String file(){
		checkedFileIds=entity.getCheckedFileIds();
		return "file";
	}
	
	public void prepareSaveFile() throws Exception{
		prepareModel();
	}
	
	public void prepareFile() throws Exception{
		prepareModel();
	}
	
	public void prepareManage() throws Exception{
		prepareModel();
	}

	@Override
	public Shelf getModel() {

		return entity;
	}

	@Autowired
	public void setCategoryManager(CategoryManager categoryManager) {
		this.categoryManager = categoryManager;
	}

	@Autowired
	public void setFileManager(FileManager fileManager) {
		this.fileManager = fileManager;
	}

	public List<Shelf> getShelfs() {
		return shelfs;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCheckedStoreId() {
		return checkedStoreId;
	}

	public void setCheckedStoreId(Long checkedStoreId) {
		this.checkedStoreId = checkedStoreId;
	}

	public List<Store> getAllStores() {
		return categoryManager.getAllStore();
	}

	public List<ThemeFile> getOnShelfFiles() {
		return onShelfFiles;
	}

	public List<ThemeFile> getRemainFiles() {
		return remainFiles;
	}
	
	public List<ThemeFile> getAllFiles(){
		return fileManager.getAllThemeFile();
	}
	
	public List<Long> getCheckedFileIds() {
		return checkedFileIds;
	}
	
	public void setCheckedFileIds(List<Long> checkedFileIds) {
		this.checkedFileIds = checkedFileIds;
	}
}
