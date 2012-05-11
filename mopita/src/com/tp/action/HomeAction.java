package com.tp.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.tp.entity.Shelf;
import com.tp.entity.ThemeFile;
import com.tp.service.HomeManager;

public class HomeAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private List<ThemeFile> hottestFiles;
	private List<ThemeFile> newestFiles;
	private List<ThemeFile> recommendFiles;

	private HomeManager homeManager;

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
		List<Shelf> shelfs = homeManager.getDefaultShelfs();
		for (Shelf shelf : shelfs) {
			if (shelf.getValue().equals(Shelf.Type.HOTTEST.getValue())) {
				hottestFiles = homeManager.getHottestFiles(shelf);
			} else if (shelf.getValue().equals(Shelf.Type.NEWEST.getValue())) {
				newestFiles = homeManager.getNewestFiles(shelf);
			} else if (shelf.getValue().equals(Shelf.Type.RECOMMEND.getValue())) {
				recommendFiles = homeManager.getRecommendFiles(shelf);
			}
		}
		return SUCCESS;
	}

	@Autowired
	public void setHomeManager(HomeManager homeManager) {
		this.homeManager = homeManager;
	}

	public List<ThemeFile> getHottestFiles() {
		return hottestFiles;
	}

	public List<ThemeFile> getNewestFiles() {
		return newestFiles;
	}

	public List<ThemeFile> getRecommendFiles() {
		return recommendFiles;
	}

}
