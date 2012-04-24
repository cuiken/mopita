package com.tp.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.tp.entity.Category;
import com.tp.service.CategoryManager;

@Namespace("/category")
@Results( { @Result(name = CRUDActionSupport.RELOAD, location = "category.action", type = "redirect") })
public class CategoryAction extends CRUDActionSupport<Category> {

	private static final long serialVersionUID = 1L;

	private Category entity;
	private Long id;
	private List<Category> categories;
	private CategoryManager categoryManager;

	@Override
	public String delete() throws Exception {
		categoryManager.deleteCategory(id);
		addActionMessage("删除成功");
		return RELOAD;
	}

	@Override
	public String input() throws Exception {

		return INPUT;
	}

	@Override
	public String list() throws Exception {
		categories = categoryManager.getCategories();
		return SUCCESS;
	}

	@Override
	protected void prepareModel() throws Exception {

		if (id == null) {
			entity = new Category();
		} else {
			entity = categoryManager.getCategory(id);
		}
	}

	@Override
	public String save() throws Exception {

		categoryManager.saveCategory(entity);
		addActionMessage("保存成功");
		return RELOAD;
	}

	@Override
	public Category getModel() {

		return entity;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Autowired
	public void setCategoryManager(CategoryManager categoryManager) {
		this.categoryManager = categoryManager;
	}
}
