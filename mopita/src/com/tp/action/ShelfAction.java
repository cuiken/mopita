package com.tp.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.tp.entity.Shelf;
import com.tp.entity.Store;
import com.tp.service.CategoryManager;

@Namespace("/category")
@Results( { @Result(name = CRUDActionSupport.RELOAD, location = "shelf.action", type = "redirect") })
public class ShelfAction extends CRUDActionSupport<Shelf> {

	private static final long serialVersionUID = 1L;
	private static final String MANAGE = "manage";

	private Long id;
	private Shelf entity;
	private Long checkedStoreId;
	private List<Shelf> shelfs;
	private CategoryManager categoryManager;

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
		Store s = categoryManager.getStore(checkedStoreId);
		entity.setStore(s);
		categoryManager.saveShelf(entity);
		return RELOAD;
	}

	public String manage() throws Exception {

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
}
