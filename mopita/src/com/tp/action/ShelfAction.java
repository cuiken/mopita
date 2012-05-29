package com.tp.action;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.tp.entity.Shelf;
import com.tp.entity.ShelfFileLink;
import com.tp.entity.Store;
import com.tp.entity.ThemeFile;
import com.tp.service.CategoryManager;
import com.tp.service.FileManager;
import com.tp.service.ShelfFileLinkManager;
import com.tp.utils.Struts2Utils;

@Namespace("/category")
@Results({
		@Result(name = CRUDActionSupport.RELOAD, location = "shelf.action", type = "redirect"),
		@Result(name = "shelf-manage", location = "shelf!manage.action", params = { "id", "${selectId}" }, type = "redirect") })
public class ShelfAction extends CRUDActionSupport<Shelf> {

	private static final long serialVersionUID = 1L;
	private static final String MANAGE = "manage";
	private static final long noSelect = 0L;
	private Long id;
	private Shelf entity;
	private Long checkedStoreId;
	private List<ThemeFile> onShelfFiles = Lists.newArrayList();
	private List<ThemeFile> remainFiles = Lists.newArrayList();
	private List<Long> checkedFileIds;
	private Long selectId;
	private CategoryManager categoryManager;
	private FileManager fileManager;
	private ShelfFileLinkManager linkManager;

	@Override
//	@RequiresPermissions("shelf:edit")
	public String delete() throws Exception {
		categoryManager.deleteShelf(id);
		addActionMessage("删除成功");
		return RELOAD;
	}

	@Override
//	@RequiresPermissions("shelf:edit")
	public String input() throws Exception {
		checkedStoreId = entity.getCheckedId();
		return INPUT;
	}

	@Override
//	@RequiresPermissions("shelf:view")
	public String list() throws Exception {

		return SUCCESS;
	}

	public String filterShelf() throws Exception {
		Store store = categoryManager.getStore(checkedStoreId);
		List<Shelf> shelfs = store.getShelfs();
		String json = categoryManager.jsonString(shelfs);
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
//	@RequiresPermissions("shelf:edit")
	public String save() throws Exception {
		Store store = categoryManager.getStore(checkedStoreId);
		entity.setStore(store);
		categoryManager.saveShelf(entity);
		addActionMessage("保存成功");
		return RELOAD;
	}

//	@RequiresPermissions("shelf:edit")
	public String saveFile() {
		if (selectId == noSelect)
			return "";
		entity = categoryManager.getShelf(selectId);
		categoryManager.merge(entity, checkedFileIds);
		linkManager.mergeCheckedIds(entity, checkedFileIds);

		addActionMessage("保存成功");
		return "shelf-manage";
	}

	public String manage() throws Exception {
		getOnshelfThemes(entity);
		List<ThemeFile> allFiles = fileManager.getAllThemeFile();
		this.remainFiles = fileManager.getRemainFiles(allFiles, onShelfFiles);
		return MANAGE;
	}

	public String getRemainFile() {
		if (id == noSelect)
			return "";
		mergedOnShelfFiles();
		List<ThemeFile> allFiles = fileManager.getAllThemeFile();
		this.remainFiles = fileManager.getRemainFiles(allFiles, onShelfFiles);
		String json = fileManager.jsonString(remainFiles);
		Struts2Utils.renderJson(json);
		return null;
	}

	private void mergedOnShelfFiles() {
		Shelf shelf = categoryManager.getShelf(id);
		getOnshelfThemes(shelf);
	}

	private void getOnshelfThemes(Shelf shelf) {
		List<ShelfFileLink> shelfFiles = shelf.getShelfFile();
		for (ShelfFileLink sf : shelfFiles) {
			onShelfFiles.add(sf.getTheme());
		}
	}

	public String getOnShelfFile() {
		if (id == noSelect)
			return "";
		mergedOnShelfFiles();
		String json = fileManager.jsonString(onShelfFiles);
		Struts2Utils.renderJson(json);
		return null;
	}

	public void prepareFile() throws Exception {
		prepareModel();
	}

	public void prepareManage() throws Exception {
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

	@Autowired
	public void setLinkManager(ShelfFileLinkManager linkManager) {
		this.linkManager = linkManager;
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

	public List<ThemeFile> getAllFiles() {
		return fileManager.getAllThemeFile();
	}

	public List<Long> getCheckedFileIds() {
		return checkedFileIds;
	}

	public void setCheckedFileIds(List<Long> checkedFileIds) {
		this.checkedFileIds = checkedFileIds;
	}

	public Long getSelectId() {
		return selectId;
	}

	public void setSelectId(Long selectId) {
		this.selectId = selectId;
	}
}
