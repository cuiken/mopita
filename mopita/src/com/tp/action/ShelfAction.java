package com.tp.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.tp.entity.Shelf;
import com.tp.entity.Store;
import com.tp.entity.ThemeFile;
import com.tp.service.CategoryManager;
import com.tp.service.FileManager;

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
		shelfs = categoryManager.getAllShelf();
		return SUCCESS;
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
		return RELOAD;
	}

	public String manage() throws Exception {
		entity = categoryManager.getShelf(id);
		this.onShelfFiles = entity.getTheme();
		List<ThemeFile> allFiles = fileManager.getAllThemeFile();
		this.remainFiles = fileManager.getRemainFiles(allFiles, onShelfFiles);
		return MANAGE;
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
}
