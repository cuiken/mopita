package com.tp.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.tp.dao.HibernateUtils;
import com.tp.entity.Category;
import com.tp.entity.FileMultipleInfo;
import com.tp.entity.ThemeFile;
import com.tp.orm.Page;
import com.tp.orm.PropertyFilter;
import com.tp.orm.PageRequest.Sort;
import com.tp.service.CategoryManager;
import com.tp.service.FileManager;
import com.tp.utils.Struts2Utils;

@Namespace("/file")
@Results( { @Result(name = CRUDActionSupport.RELOAD, location = "file.action", type = "redirect") })
public class FileAction extends CRUDActionSupport<ThemeFile> {

	private static final long serialVersionUID = 1L;
	private ThemeFile entity;
	private Long id;
	private List<Long> checkedCategoryId;
	private Page<ThemeFile> page = new Page<ThemeFile>();
	private List<FileMultipleInfo> fileInfo;
	private FileManager fileManager;
	private CategoryManager categoryManager;

	@Override
	public String delete() throws Exception {
		fileManager.deleteThemeFile(id);
		addActionMessage("删除成功");
		return RELOAD;
	}

	@Override
	public String input() throws Exception {
		checkedCategoryId = entity.getCheckedCategoryIds();
		return INPUT;
	}

	@Override
	public String list() throws Exception {

		List<PropertyFilter> filters = PropertyFilter
				.buildFromHttpRequest(Struts2Utils.getRequest());
		if (!page.isOrderBySetted()) {
			page.setOrderBy("createTime");
			page.setOrderDir(Sort.DESC);
		}
		page = fileManager.searchThemeFile(page, filters);

		return SUCCESS;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (id == null) {
			entity = new ThemeFile();
		} else {
			entity = fileManager.getThemeFile(id);
		}

	}

	@Override
	public String save() throws Exception {
		HibernateUtils.mergeByCheckedIds(entity.getCategories(), checkedCategoryId, Category.class);
		fileManager.saveThemeFile(entity);
		addActionMessage("保存成功");
		return RELOAD;
	}

	@Override
	public ThemeFile getModel() {

		return entity;
	}

	public List<Category> getAllCategoryList() {
		return categoryManager.getCategories();
	}

	@Autowired
	public void setFileManager(FileManager fileManager) {
		this.fileManager = fileManager;
	}

	@Autowired
	public void setCategoryManager(CategoryManager categoryManager) {
		this.categoryManager = categoryManager;
	}

	public Page<ThemeFile> getPage() {
		return page;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Long> getCheckedCategoryId() {
		return checkedCategoryId;
	}

	public void setCheckedCategoryId(List<Long> checkedCategoryId) {
		this.checkedCategoryId = checkedCategoryId;
	}

	public List<FileMultipleInfo> getFileInfo() {
		return fileInfo;
	}

	public void setFileInfo(List<FileMultipleInfo> fileInfo) {
		this.fileInfo = fileInfo;
	}
}
