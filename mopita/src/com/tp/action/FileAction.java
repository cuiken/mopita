package com.tp.action;

import java.io.File;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.tp.dao.HibernateUtils;
import com.tp.entity.Category;
import com.tp.entity.FileInfo;
import com.tp.entity.ThemeFile;
import com.tp.orm.Page;
import com.tp.orm.PropertyFilter;
import com.tp.orm.PageRequest.Sort;
import com.tp.service.CategoryManager;
import com.tp.service.FileInfoObservable;
import com.tp.service.FileManager;
import com.tp.utils.FileUtils;
import com.tp.utils.Struts2Utils;

@Namespace("/file")
@Results({ @Result(name = CRUDActionSupport.RELOAD, location = "file.action", type = "redirect") })
public class FileAction extends CRUDActionSupport<ThemeFile> {

	private static final long serialVersionUID = 1L;
	private ThemeFile entity;
	private Long id;
	private List<Long> checkedCategoryId;
	private Page<ThemeFile> page = new Page<ThemeFile>();
	private List<FileInfo> fileInfo;
	private FileManager fileManager;
	private CategoryManager categoryManager;
	private FileInfoObservable observer;

	private File file;

	@Override
	@RequiresPermissions("file:edit")
	public String delete() throws Exception {
		fileManager.deleteThemeFile(id);
		addActionMessage("删除成功");
		return RELOAD;
	}

	@Override
	@RequiresPermissions("file:edit")
	public String input() throws Exception {
		checkedCategoryId = entity.getCheckedCategoryIds();
		return INPUT;
	}

	@Override
	@RequiresPermissions("file:view")
	public String list() throws Exception {

		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(Struts2Utils.getRequest());
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
	@RequiresPermissions("file:edit")
	public String save() throws Exception {
		HibernateUtils.mergeByCheckedIds(entity.getCategories(), checkedCategoryId, Category.class);
		List<File> files = Lists.newArrayList();
		if (file != null) {
			files = FileUtils.unZip(file);
		}

		fileManager.saveFiles(files, entity);
		for (FileInfo info : entity.getFileInfo()) {
			info.setTheme(entity);
			observer.saveFileInfo(info);
		}
		addActionMessage("保存成功");
		return RELOAD;
	}

	public String checkTitle() throws Exception {

		String oldTitle = new String(Struts2Utils.getParameter("oldTitle").getBytes("iso-8859-1"), "utf-8");
		String newTitle = new String(Struts2Utils.getParameter("title").getBytes("iso-8859-1"), "utf-8");
		if (fileManager.isFileTitleUnique(newTitle, oldTitle)) {
			Struts2Utils.renderText("true");
		} else {
			Struts2Utils.renderText("false");
		}
		return null;
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

	@Autowired
	public void setObserver(FileInfoObservable observer) {
		this.observer = observer;
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

	public List<FileInfo> getFileInfo() {
		return fileInfo;
	}

	public void setFileInfo(List<FileInfo> fileInfo) {
		this.fileInfo = fileInfo;
	}

	public void setFile(File file) {
		this.file = file;
	}
}
