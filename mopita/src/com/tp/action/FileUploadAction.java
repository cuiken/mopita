package com.tp.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.tp.dao.HibernateUtils;
import com.tp.entity.Category;
import com.tp.entity.FileInfo;
import com.tp.entity.FileType;
import com.tp.entity.ThemeFile;
import com.tp.service.CategoryManager;
import com.tp.service.FileManager;
import com.tp.utils.FileUtils;

@Namespace("/file")
@Results( {
		@Result(name = "editinfo", location = "file-info.action", params = { "themeId", "${id}" }, type = "redirect"),
		@Result(name = "reupload", location = "file-upload.action", type = "redirect") })
public class FileUploadAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private static final String RELOAD = "reupload";
	private static final String EDITINFO = "editinfo";
	private static final long MAX_SIZE = 10000000L;

	private File upload;
	private String uploadFileName;

	private String availMachine;
	private String unavailMachine;
	private String marketURL;

	private String title;
	private Long price;
	private String shortDescription;
	private String longDescription;
	private String author;

	private List<Long> checkedCategoryIds;

	private FileManager fileManager;
	private CategoryManager categoryManager;

	private Long id;

	@Override
	public String execute() throws Exception {

		return SUCCESS;
	}

	public String upload() throws IOException {
		String extension = FileUtils.getExtension(uploadFileName);
		if (!extension.equalsIgnoreCase(FileType.ZIP.getValue())) {
			addActionMessage("请上传一个zip文件");
			return RELOAD;
		}
		if (upload.length() > MAX_SIZE) {
			addActionMessage("上传文件过大");
			return RELOAD;
		}
		List<File> files = FileUtils.unZip(upload);
		ThemeFile theme = getThemeFile();
		FileInfo info = getFileInfo();
		theme = fileManager.saveFiles(files, theme, info);
		this.setId(theme.getId());
		addActionMessage("上传成功");
		return EDITINFO;
	}

	private ThemeFile getThemeFile() {
		ThemeFile theme = new ThemeFile();
		theme.setName(FileUtils.getFileName(uploadFileName));
		theme.setTitle(title);
		theme.setAvailMachine(availMachine);
		theme.setUnavailMachine(unavailMachine);
		theme.setMarketURL(marketURL);
		HibernateUtils.mergeByCheckedIds(theme.getCategories(), checkedCategoryIds, Category.class);
		return theme;
	}

	private FileInfo getFileInfo() {
		FileInfo info = new FileInfo();
		info.setTitle(title);
		info.setPrice(price);
		info.setLanguage("ZH");
		info.setLongDescription(longDescription);
		info.setShortDescription(shortDescription);
		info.setAuthor(author);
		return info;
	}

	public File getUpload() {
		return upload;
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

	public List<Category> getAllCategoryList() {
		return categoryManager.getCategories();
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setMarketURL(String marketURL) {
		this.marketURL = marketURL;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public void setAvailMachine(String availMachine) {
		this.availMachine = availMachine;
	}

	public void setUnavailMachine(String unavailMachine) {
		this.unavailMachine = unavailMachine;
	}

	public void setCheckedCategoryIds(List<Long> checkedCategoryIds) {
		this.checkedCategoryIds = checkedCategoryIds;
	}

	@Autowired
	public void setFileManager(FileManager fileManager) {
		this.fileManager = fileManager;
	}

	@Autowired
	public void setCategoryManager(CategoryManager categoryManager) {
		this.categoryManager = categoryManager;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
