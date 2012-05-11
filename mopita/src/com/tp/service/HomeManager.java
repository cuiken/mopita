package com.tp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.tp.entity.Shelf;
import com.tp.entity.ShelfFileLink;
import com.tp.entity.Store;
import com.tp.entity.ThemeFile;

public class HomeManager {

	private CategoryManager categoryManager;
	private FileManager fileManager;

	public List<ThemeFile> getRecommendFiles(Shelf recoShelf) {
		return getThemeByShelf(recoShelf);
	}

	public List<ThemeFile> getHottestFiles(Shelf hottestShelf) {
		return getThemeByShelf(hottestShelf);
	}

	public List<ThemeFile> getNewestFiles(Shelf newestShelf) {
		return getThemeByShelf(newestShelf);
	}

	private List<ThemeFile> getThemeByShelf(Shelf shelf) {
		List<ThemeFile> themes = Lists.newArrayList();
		List<ShelfFileLink> links = shelf.getShelfFile();
		for (ShelfFileLink link : links) {
			themes.add(link.getTheme());
		}
		return themes;
	}

	public List<Shelf> getDefaultShelfs() {
		Store local = categoryManager.getStore(1L);
		return local.getShelfs();

	}

	@Autowired
	public void setCategoryManager(CategoryManager categoryManager) {
		this.categoryManager = categoryManager;
	}

	@Autowired
	public void setFileManager(FileManager fileManager) {
		this.fileManager = fileManager;
	}
}
