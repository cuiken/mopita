package com.tp.action;

import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.tp.entity.FileStoreInfo;
import com.tp.entity.Shelf;
import com.tp.orm.Page;
import com.tp.service.CategoryManager;
import com.tp.service.FileManager;
import com.tp.utils.Constants;
import com.tp.utils.Struts2Utils;

@Namespace("/store")
public class LockerAction extends ActionSupport {
	private static final long serialVersionUID = 1L;

	private FileManager fileManager;
	private CategoryManager categoryManager;

	private Page<FileStoreInfo> hottestPage = new Page<FileStoreInfo>();

	private Page<FileStoreInfo> newestPage = new Page<FileStoreInfo>();

	@Override
	public String execute() throws Exception {

		return list();
	}

	public String list() throws Exception {

		String language = Struts2Utils.getParameter(Constants.PARA_LANGUAGE);
		String st = Struts2Utils.getParameter(Constants.PARA_STORE_TYPE);
		if (language == null) {
			language = Constants.getLocal();
		}
		if (st != null && st.equals(Constants.DM_LOCKER)) {
			Long storeId = categoryManager.getStoreByValue(Constants.DM_LOCKER).getId();

			hottestPage.setPageSize(12);
			hottestPage = fileManager.searchStoreInfoInShelf(hottestPage, Shelf.Type.HOTTEST, storeId, language);

			newestPage = fileManager.searchStoreInfoInShelf(newestPage, Shelf.Type.NEWEST, storeId, language);

		}
		return SUCCESS;
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

}
