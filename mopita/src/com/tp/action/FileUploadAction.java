package com.tp.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.tp.entity.Category;
import com.tp.entity.FileMultipleInfo;
import com.tp.entity.ThemeFile;
import com.tp.service.CategoryManager;
import com.tp.service.FileManager;
import com.tp.utils.Constants;
import com.tp.utils.FileUtils;

@Namespace("/file")
public class FileUploadAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private File upload;
	private String uploadFileName;

	private String availMachine;
	private String unavailMachine;
	private String marketURL;

	private String title;
	private Long price;
	private String description;

	private FileManager fileManager;
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
			FileMultipleInfo info=getFileInfo();
			fileManager.saveFiles(files, theme,info);
			addActionMessage("上传成功");
		}

		return "";
	}

	private ThemeFile getThemeFile() {
		ThemeFile theme = new ThemeFile();
		theme.setName(uploadFileName);
		theme.setAvailMachine(availMachine);
		theme.setUnavailMachine(unavailMachine);
		theme.setMarketURL(marketURL);

		return theme;
	}

	private FileMultipleInfo getFileInfo() {
		FileMultipleInfo info = new FileMultipleInfo();
		info.setTitle(title);
		info.setPrice(price);
		info.setDescription(description);
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
	public void setFileManager(FileManager fileManager) {
		this.fileManager = fileManager;
	}

	@Autowired
	public void setCategoryManager(CategoryManager categoryManager) {
		this.categoryManager = categoryManager;
	}

}
