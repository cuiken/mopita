package com.tp.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.opensymphony.xwork2.ActionSupport;
import com.tp.entity.Category;
import com.tp.entity.Preview;
import com.tp.entity.ThemeFile;
import com.tp.orm.Page;
import com.tp.service.CategoryManager;
import com.tp.service.FileManager;
import com.tp.utils.Constants;
import com.tp.utils.Struts2Utils;

@Namespace("/wap")
public class WapAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private Page<ThemeFile> page = new Page<ThemeFile>();
	private ThemeFile theme;
	private FileManager fileManager;
	private CategoryManager categoryManager;
	private Long id;
	private Long categoryId;
	private List<Category> categories;
	private List<String> urls = Lists.newArrayList();
	private Long previewId;

	@Override
	public String execute() throws Exception {
		if (categoryId == null) {
			categoryId = 1L;
		}
		categories = categoryManager.getCategories();
		page = fileManager.searchThemeFile(page, categoryId);
		List<ThemeFile> files = page.getResult();
		for (ThemeFile file : files) {
			String previewURL = "/wap/wap!getImage.action?id=" + file.getId();
			file.setPreviewURL(previewURL);

		}
		return SUCCESS;
	}

	public String getImage() throws Exception {
		ThemeFile fi = fileManager.getThemeFile(id);
		List<Preview> previewURLS = fi.getPreviews();

		String imgPath = Constants.FILE_STORAGE
				+ previewURLS.get(0).getPrePath();

		responseImage(imgPath);
		return null;
	}

	private void responseImage(String imgURL) throws Exception {
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("image/*");
		File file = new File(imgURL);
		byte[] buffer = new byte[1024];
		InputStream is = new FileInputStream(file);
		int len = 0;
		while ((len = is.read(buffer)) != -1) {
			response.getOutputStream().write(buffer, 0, len);
		}
		response.getOutputStream().flush();
		is.close();
	}

	public String getImages() throws Exception {
		Preview p = fileManager.getPreview(previewId);
		String imgURL = Constants.FILE_STORAGE + p.getPrePath();
		responseImage(imgURL);
		return null;
	}

	public String details() {
		categories = categoryManager.getCategories();
		theme = fileManager.getThemeFile(id);
		List<Preview> ps = theme.getPreviews();
		for (Preview p : ps) {
			urls.add("/wap/wap!getImages.action?previewId=" + p.getId());
		}

		return "details";
	}

	public Page<ThemeFile> getPage() {
		return page;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Autowired
	public void setFileManager(FileManager fileManager) {
		this.fileManager = fileManager;
	}

	@Autowired
	public void setCategoryManager(CategoryManager categoryManager) {
		this.categoryManager = categoryManager;
	}

	public List<String> getPreviews() {
		return urls;
	}

	public void setPreviewId(Long previewId) {
		this.previewId = previewId;
	}

	public ThemeFile getTheme() {
		return theme;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

}
