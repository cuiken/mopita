package com.tp.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.tp.entity.Category;
import com.tp.entity.FileStoreInfo;
import com.tp.entity.Shelf;
import com.tp.entity.Store;
import com.tp.entity.ThemeFile;
import com.tp.orm.Page;
import com.tp.service.CategoryManager;
import com.tp.service.FileManager;
import com.tp.utils.Constants;
import com.tp.utils.Servlets;
import com.tp.utils.Struts2Utils;

public class HomeAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private CategoryManager categoryManager;
	private FileManager fileManager;

	private Page<ThemeFile> hottestPage = new Page<ThemeFile>();
	private Page<ThemeFile> newestPage = new Page<ThemeFile>();
	private Page<ThemeFile> recommendPage = new Page<ThemeFile>();

	private Long id;
	private FileStoreInfo info;

	@Override
	public String execute() throws Exception {

		return list();
	}

	/**
	 * 商店首页显示列表
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		Store store = categoryManager.getDefaultStore();
		hottestPage = fileManager.searchFileByShelf(hottestPage, Shelf.Type.HOTTEST, store.getId());
		newestPage = fileManager.searchFileByShelf(newestPage, Shelf.Type.NEWEST, store.getId());
		recommendPage = fileManager.searchFileByShelf(recommendPage, Shelf.Type.RECOMMEND, store.getId());
		return SUCCESS;
	}

	/**
	 * 输出5个广告xml
	 * @return
	 * @throws Exception
	 */
	public String adXml() throws Exception {
		Store store = categoryManager.getDefaultStore();
		recommendPage = fileManager.searchFileByShelf(recommendPage, Shelf.Type.RECOMMEND, store.getId());
		String xml = fileManager.adXml(recommendPage.getResult());
		Struts2Utils.renderXml(xml);
		return null;
	}

	public String details() throws Exception {
		Store store = categoryManager.getDefaultStore();
		ThemeFile theme = fileManager.getThemeFile(id);
		FileStoreInfo info = fileManager.getStoreInfoBy(store.getId(), theme.getId(), "ZH");
		this.setInfo(info);
		Category cate = theme.getCategories().get(0);
		hottestPage = fileManager.searchFileByStoreAndCategory(hottestPage, store.getId(), cate.getId());
		return "details";
	}

	public String down() throws Exception {

		String apk = new String(Struts2Utils.getParameter("path").getBytes("iso-8859-1"), "utf-8");
		File apkFile = new File(Constants.FILE_STORAGE, apk);

		byte[] buffer = new byte[1024];
		InputStream is = new FileInputStream(apkFile);
		HttpServletResponse response = Struts2Utils.getResponse();
		response.reset();
		Servlets.setFileDownloadHeader(response, apkFile.getName());
		int len = 0;
		OutputStream out = response.getOutputStream();
		while ((len = is.read(buffer)) != -1) {
			out.write(buffer, 0, len);
		}
		out.flush();
		out.close();
		is.close();
		return null;
	}

	@Autowired
	public void setFileManager(FileManager fileManager) {
		this.fileManager = fileManager;
	}

	@Autowired
	public void setCategoryManager(CategoryManager categoryManager) {
		this.categoryManager = categoryManager;
	}

	public Page<ThemeFile> getHottestPage() {
		return hottestPage;
	}

	public Page<ThemeFile> getNewestPage() {
		return newestPage;
	}

	public Page<ThemeFile> getRecommendPage() {
		return recommendPage;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FileStoreInfo getInfo() {
		return info;
	}

	public void setInfo(FileStoreInfo info) {
		this.info = info;
	}
}
