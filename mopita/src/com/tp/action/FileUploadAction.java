package com.tp.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.tp.dao.HibernateUtils;
import com.tp.entity.Category;
import com.tp.entity.ThemeFile;
import com.tp.service.CategoryManager;
import com.tp.service.FileStoreManager;
import com.tp.utils.Constants;
import com.tp.utils.FileUtils;

@Namespace("/file")
public class FileUploadAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private File upload;
	private String uploadFileName;
	private String uploadContentType;
	private List<Long> checkedCategoryIds;

	private String title;

	private String marketURL;

	private Long price;
	private String availMachine;
	private String unavailMachine;

	private String description;

	private FileStoreManager fileStoreManager;
	private CategoryManager categoryManager;

	@Override
	public String execute() throws Exception {

		return SUCCESS;
	}

	public String upload() throws IOException {
		String extension = FileUtils.getExtension(uploadFileName);
		if (extension.equalsIgnoreCase(Constants.ZIP)) {
			List<File> files = FileUtils.unZip(upload);
			ThemeFile theme = getThemeFile();
			fileStoreManager.saveFiles(files, theme);
			addActionMessage("上传成功");
		}

		return "";
	}

	private ThemeFile getThemeFile() {
		ThemeFile theme = new ThemeFile();
		theme.setName(uploadFileName);
		theme.setTitle(title);
		theme.setPrice(price);
		theme.setDescription(description);
		theme.setAvailMachine(availMachine);
		theme.setUnavailMachine(unavailMachine);
		theme.setMarketURL(marketURL);
		HibernateUtils.mergeByCheckedIds(theme.getCategories(),
				checkedCategoryIds, Category.class);
		return theme;
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

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public List<Category> getAllCategoryList() {
		return categoryManager.getCategories();
	}

	public void setCheckedCategoryIds(List<Long> checkedCategoryIds) {
		this.checkedCategoryIds = checkedCategoryIds;
	}

	public void setDescription(String description) {
		this.description = description;
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

	@Autowired
	public void setFileStoreManager(FileStoreManager fileStoreManager) {
		this.fileStoreManager = fileStoreManager;
	}

	@Autowired
	public void setCategoryManager(CategoryManager categoryManager) {
		this.categoryManager = categoryManager;
	}

}
