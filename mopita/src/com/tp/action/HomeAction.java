package com.tp.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.tp.entity.Shelf;
import com.tp.entity.Store;
import com.tp.entity.ThemeFile;
import com.tp.orm.Page;
import com.tp.service.HomeManager;

public class HomeAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private HomeManager homeManager;

	private Page<ThemeFile> hottestPage = new Page<ThemeFile>();
	private Page<ThemeFile> newestPage = new Page<ThemeFile>();
	private Page<ThemeFile> recommendPage = new Page<ThemeFile>();

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
		Store store = homeManager.getDefaultStore();
		hottestPage = homeManager.searchFileByShelf(hottestPage, Shelf.Type.HOTTEST, store.getId());
		newestPage = homeManager.searchFileByShelf(newestPage, Shelf.Type.NEWEST, store.getId());
		recommendPage = homeManager.searchFileByShelf(recommendPage, Shelf.Type.RECOMMEND, store.getId());
		return SUCCESS;
	}

	@Autowired
	public void setHomeManager(HomeManager homeManager) {
		this.homeManager = homeManager;
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
}
