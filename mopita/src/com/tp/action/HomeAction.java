package com.tp.action;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.tp.entity.Category;
import com.tp.entity.DownloadType;
import com.tp.entity.FileStoreInfo;
import com.tp.entity.Market;
import com.tp.entity.Shelf;
import com.tp.entity.Store;
import com.tp.entity.ThemeFile;
import com.tp.orm.Page;
import com.tp.service.CategoryManager;
import com.tp.service.FileManager;
import com.tp.service.MarketManager;
import com.tp.utils.Constants;
import com.tp.utils.Struts2Utils;

public class HomeAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private CategoryManager categoryManager;
	private FileManager fileManager;
	private MarketManager marketManager;

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
		Constants.setParamInSession();

		String language = (String) Struts2Utils.getSession().getAttribute(Constants.SESS_KEY_LANGUAGE);
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
		HttpSession session = Struts2Utils.getSession();
		String language = (String) session.getAttribute(Constants.SESS_KEY_LANGUAGE);

		Store store = categoryManager.getDefaultStore();
		ThemeFile theme = fileManager.getThemeFile(id);
		info = fileManager.getStoreInfoBy(store.getId(), theme.getId(), language);
		setDownloadType(session);
		Category cate = theme.getCategories().get(0);
		//		hottestPage = fileManager.searchFileByStoreAndCategory(hottestPage, store.getId(), cate.getId());
		catePage = fileManager.searchInfoByCategoryAndStore(catePage, cate.getId(), store.getId(), language);
		return "details";
	}

	private void setDownloadType(HttpSession session) {

		String fromMarket = (String) session.getAttribute(Constants.SESS_KEY_MARKET);
		String downType = (String) session.getAttribute(Constants.SESS_KEY_DT);
		String http = "file-download.action?inputPath=" + info.getTheme().getApkPath();
		if (downType.equals(DownloadType.MARKET.getValue())) {
			marketDownload(fromMarket, http);
		} else {
			info.getTheme().setDownloadURL(http);
		}
	}

	private void marketDownload(String fromMarket, String http) {
		Market market = marketManager.findByPkName(fromMarket);
		if (market == null || market.getMarketKey().isEmpty()) {
			info.getTheme().setDownloadURL(http);
		} else {
			info.getTheme().setDownloadURL(market.getMarketKey() + info.getTheme().getMarketURL());
		}
	}

	public String more() throws Exception {
		String language = (String) Struts2Utils.getSession().getAttribute(Constants.SESS_KEY_LANGUAGE);
		Store store = categoryManager.getDefaultStore();
		categoryId = Long.valueOf(Struts2Utils.getParameter("cid"));
		categories = categoryManager.getCategories();
		catePage = fileManager.searchInfoByCategoryAndStore(catePage, categoryId, store.getId(), language);
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

	@Autowired
	public void setMarketManager(MarketManager marketManager) {
		this.marketManager = marketManager;
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
