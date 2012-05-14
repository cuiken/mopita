package com.tp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tp.entity.Store;
import com.tp.entity.ThemeFile;
import com.tp.entity.Shelf.Type;
import com.tp.orm.Page;

@Component
public class HomeManager {

	private CategoryManager categoryManager;
	private FileManager fileManager;

	public Store getDefaultStore() {
		return categoryManager.getStore("local");
	}

	public Page<ThemeFile> searchFileByShelf(final Page<ThemeFile> page, Type stype, Long sid) {
		return fileManager.searchFileByShelf(page, stype.getValue(), sid);
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
