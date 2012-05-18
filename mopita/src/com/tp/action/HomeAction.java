package com.tp.action;

import java.util.Collections;
import java.util.List;

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
import com.tp.utils.Struts2Utils;

public class HomeAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private CategoryManager categoryManager;
	private FileManager fileManager;

	private Page<FileStoreInfo> hottestPage = new Page<FileStoreInfo>();
	private Page<FileStoreInfo> recommendPage = new Page<FileStoreInfo>();

	private Page<FileStoreInfo> newestPage = new Page<FileStoreInfo>();
	private Page<FileStoreInfo> catePage = new Page<FileStoreInfo>();

	private Long id;
	private FileStoreInfo info;
	private List<Category> categories;
	private Long categoryId;
	private ThemeFile adFile;

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

		String language = Constants.getLanguage();
		Store store = categoryManager.getDefaultStore();
		hottestPage = fileManager.searchStoreInfoInShelf(hottestPage, Shelf.Type.HOTTEST, store.getId(), language);

		newestPage = fileManager.searchStoreInfoInShelf(newestPage, Shelf.Type.NEWEST, store.getId(), language);

		recommendPage = fileManager
				.searchStoreInfoInShelf(recommendPage, Shelf.Type.RECOMMEND, store.getId(), language);
		List<FileStoreInfo> recommendFiles = recommendPage.getResult();
		if (recommendFiles.size() > 0) {
			Collections.shuffle(recommendFiles);
			adFile = recommendFiles.get(0).getTheme();
		}
		return SUCCESS;
	}

	/**
	 * 输出5个广告xml
	 * @return
	 * @throws Exception
	 */
	public String adXml() throws Exception {
		Store store = categoryManager.getDefaultStore();
		Page<ThemeFile> adPage = new Page<ThemeFile>();
		adPage = fileManager.searchFileByShelf(adPage, Shelf.Type.RECOMMEND, store.getId());
		String xml = fileManager.adXml(adPage.getResult());
		Struts2Utils.renderXml(xml);
		return null;
	}

	public String details() throws Exception {
		String lan = Constants.getLanguage();
		Store store = categoryManager.getDefaultStore();
		ThemeFile theme = fileManager.getThemeFile(id);
		FileStoreInfo info = fileManager.getStoreInfoBy(store.getId(), theme.getId(), lan);
		this.setInfo(info);
		Category cate = theme.getCategories().get(0);
		//		hottestPage = fileManager.searchFileByStoreAndCategory(hottestPage, store.getId(), cate.getId());
		catePage = fileManager.searchInfoByCategoryAndStore(catePage, cate.getId(), store.getId(), lan);
		return "details";
	}

	public String more() throws Exception {
		String lan = Constants.getLanguage();
		Store store = categoryManager.getDefaultStore();
		categoryId = Long.valueOf(Struts2Utils.getParameter("cid"));
		categories = categoryManager.getCategories();
		catePage = fileManager.searchInfoByCategoryAndStore(catePage, categoryId, store.getId(), lan);
		//		hottestPage = fileManager.searchThemeFile(hottestPage, categoryId);
		return "more";
	}

	@Autowired
	public void setFileManager(FileManager fileManager) {
		this.fileManager = fileManager;
	}

	@Autowired
	public void setCategoryManager(CategoryManager categoryManager) {
		this.categoryManager = categoryManager;
	}

	public Page<FileStoreInfo> getHottestPage() {
		return hottestPage;
	}

	public Page<FileStoreInfo> getNewestPage() {
		return newestPage;
	}

	public Page<FileStoreInfo> getRecommendPage() {
		return recommendPage;
	}

	public Page<FileStoreInfo> getCatePage() {
		return catePage;
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

	public List<Category> getCategories() {
		return categories;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public ThemeFile getAdFile() {
		return adFile;
	}

}
