package com.tp.action;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.tp.entity.Category;
import com.tp.entity.DownloadType;
import com.tp.entity.FileStoreInfo;
import com.tp.entity.LogInHome;
import com.tp.entity.Market;
import com.tp.entity.Shelf;
import com.tp.entity.Store;
import com.tp.entity.ThemeFile;
import com.tp.orm.Page;
import com.tp.service.CategoryManager;
import com.tp.service.FileManager;
import com.tp.service.LogService;
import com.tp.service.MarketManager;
import com.tp.utils.Constants;
import com.tp.utils.Struts2Utils;

@Results({ @Result(name = "reload", location = "home.action", type = "redirect") })
public class HomeAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private Logger logger = LoggerFactory.getLogger(getClass());

	private CategoryManager categoryManager;
	private FileManager fileManager;
	private MarketManager marketManager;
	private LogService logService;

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
		HttpSession session = Struts2Utils.getSession();
		Constants.setParamInSession(session);
		setDefaultStore(session);
		writeLog(session);
		String language = (String) session.getAttribute(Constants.PARA_LANGUAGE);
		Long storeId = (Long) session.getAttribute(Constants.SESS_DEFAULT_STORE);

		hottestPage.setPageSize(12);
		hottestPage = fileManager.searchStoreInfoInShelf(hottestPage, Shelf.Type.HOTTEST, storeId, language);

		newestPage = fileManager.searchStoreInfoInShelf(newestPage, Shelf.Type.NEWEST, storeId, language);
		if (visitByBrowse(session)) {
			recommendPage = fileManager.searchStoreInfoInShelf(recommendPage, Shelf.Type.RECOMMEND, storeId, language);
			List<FileStoreInfo> recommendFiles = recommendPage.getResult();
			if (recommendFiles.size() > 0) {
				Collections.shuffle(recommendFiles);
				adFile = recommendFiles.get(0).getTheme();
			}
		}
		return SUCCESS;
	}

	private boolean visitByBrowse(HttpSession session) {

		return (String) session.getAttribute(Constants.PARA_RESOLUTION) == null;
	}

	private void setDefaultStore(HttpSession session) {
		try {
			String storeType = (String) session.getAttribute(Constants.PARA_STORE_TYPE);
			Store store = categoryManager.getStoreByValue(storeType);
			session.setAttribute(Constants.SESS_DEFAULT_STORE, store.getId());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void writeLog(HttpSession session) {
		HttpServletRequest request = Struts2Utils.getRequest();

		LogInHome log = new LogInHome();
		String requestLink = request.getServletPath() + "?" + request.getQueryString();
		String imei = (String) session.getAttribute(Constants.PARA_IMEI);
		String imsi = (String) session.getAttribute(Constants.PARA_IMSI);
		String lang = (String) session.getAttribute(Constants.PARA_LANGUAGE);
		String fromMarket = (String) session.getAttribute(Constants.PARA_FROM_MARKET);
		String downType = (String) session.getAttribute(Constants.PARA_DOWNLOAD_METHOD);
		String clientVersion = (String) session.getAttribute(Constants.PARA_CLIENT_VERSION);
		String reso = (String) session.getAttribute(Constants.PARA_RESOLUTION);
		log.setRequestLink(requestLink);
		log.setClientVersion(clientVersion);
		log.setDownType(downType);
		log.setFromMarket(fromMarket);
		log.setResolution(reso);
		log.setImei(imei);
		log.setImsi(imsi);
		log.setLanguage(lang);
		logService.saveLogInHome(log);
	}

	/**
	 * 输出5个广告xml
	 * @return
	 * @throws Exception
	 */
	public String adXml() throws Exception {
		Long storeId = categoryManager.getStoreByValue(Constants.LOCK_STORE).getId();
		Page<ThemeFile> adPage = new Page<ThemeFile>();
		adPage = fileManager.searchFileByShelf(adPage, Shelf.Type.RECOMMEND, storeId);
		HttpServletRequest request = Struts2Utils.getRequest();
		String domain = "http://" + request.getLocalAddr() + ":" + request.getLocalPort() + request.getContextPath();
		String xml = fileManager.adXml(adPage.getResult(), domain);
		Struts2Utils.renderXml(xml);
		return null;
	}

	public String details() throws Exception {
		try {
			HttpSession session = Struts2Utils.getSession();
			Constants.setParamInSession(session);
			setDefaultStore(session);
			writeLog(session);
			String language = (String) session.getAttribute(Constants.PARA_LANGUAGE);
			Long storeId = (Long) session.getAttribute(Constants.SESS_DEFAULT_STORE);
			info = fileManager.getStoreInfoBy(storeId, id, language);
			if (info == null)
				return "reload";
			setDownloadType(session);
			Category cate = info.getTheme().getCategories().get(0);
			catePage.setPageSize(3);
			catePage = fileManager.searchInfoByCategoryAndStore(catePage, cate.getId(), storeId, language);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return "reload";
		}
		return "details";
	}

	private void setDownloadType(HttpSession session) {

		String fromMarket = (String) session.getAttribute(Constants.PARA_FROM_MARKET);
		String downType = (String) session.getAttribute(Constants.PARA_DOWNLOAD_METHOD);
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
			List<ThemeFile> files = market.getThemes();
			if (files.contains(info.getTheme())) {
				info.getTheme().setDownloadURL(market.getMarketKey() + info.getTheme().getMarketURL());
			} else {
				info.getTheme().setDownloadURL(http);
			}
		}
	}

	public String more() throws Exception {
		HttpSession session = Struts2Utils.getSession();
		Constants.setParamInSession(session);
		setDefaultStore(session);
		writeLog(session);
		String language = (String) session.getAttribute(Constants.PARA_LANGUAGE);
		String storeType = (String) session.getAttribute(Constants.PARA_STORE_TYPE);
		Store store = categoryManager.getStoreByValue(storeType);
		categoryId = Long.valueOf(Struts2Utils.getParameter("cid"));
		categories = categoryManager.getCategories();
		catePage = fileManager.searchInfoByCategoryAndStore(catePage, categoryId, store.getId(), language);

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

	@Autowired
	public void setLogService(LogService logService) {
		this.logService = logService;
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
