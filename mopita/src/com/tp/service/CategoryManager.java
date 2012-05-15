package com.tp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.tp.dao.CategoryDao;
import com.tp.dao.ShelfDao;
import com.tp.dao.ShelfFileLinkDao;
import com.tp.dao.StoreDao;
import com.tp.dto.ShelfDTO;
import com.tp.entity.Category;
import com.tp.entity.FileMultipleInfo;
import com.tp.entity.FileStoreInfo;
import com.tp.entity.Shelf;
import com.tp.entity.ShelfFileLink;
import com.tp.entity.Store;
import com.tp.entity.ThemeFile;
import com.tp.mapper.JsonMapper;

@Component
@Transactional
public class CategoryManager {

	private CategoryDao categoryDao;
	private StoreDao storeDao;
	private ShelfDao shelfDao;
	private ShelfFileLinkDao sflDao;
	private FileManager fileManager;

	public Category getCategory(Long id) {
		return categoryDao.get(id);
	}

	public Store getStore(Long id) {
		return storeDao.get(id);
	}

	public Store getDefaultStore() {
		return storeDao.findUniqueBy("value", "local");
	}

	public List<Category> getCategories() {
		return categoryDao.getAll();
	}

	public List<Store> getAllStore() {
		return storeDao.getAll();
	}

	public void saveCategory(Category category) {
		categoryDao.save(category);
	}

	public void saveStore(Store entity) {

		storeDao.save(entity);

	}

	public void createDefaultShelf(Store store) {

		for (Shelf.Type st : Shelf.Type.values()) {
			Shelf shelf = new Shelf();
			shelf.setName(st.getDisplayName());
			shelf.setValue(st.getValue());
			shelf.setDescription("created by default");
			shelf.setStore(store);
			this.saveShelf(shelf);
		}
	}

	public void deleteStore(Long id) {
		storeDao.delete(id);
	}

	public void deleteCategory(Long id) {
		categoryDao.delete(id);
	}

	public Shelf getShelf(Long id) {
		return shelfDao.get(id);
	}

	public void saveShelf(Shelf entity) {
		shelfDao.save(entity);
	}

	public List<Shelf> getAllShelf() {
		return shelfDao.getAll();
	}

	public void deleteShelf(Long id) {
		shelfDao.delete(id);
	}

	public String jsonString(List<Shelf> shelfs) {
		List<ShelfDTO> sdto = Lists.newArrayList();
		for (Shelf shelf : shelfs) {
			ShelfDTO dto = new ShelfDTO();
			dto.setId(shelf.getId());
			dto.setName(shelf.getName());
			dto.setValue(shelf.getValue());
			dto.setDescription(shelf.getDescription());
			sdto.add(dto);
		}
		JsonMapper mapper = JsonMapper.buildNormalMapper();
		return mapper.toJson(sdto);
	}

	public void copyAllStore(Long storeId, Store targetStore) {
		Store originalStore = this.getStore(storeId);
		List<Shelf> oriShelfs = originalStore.getShelfs();
		List<ThemeFile> singleThemeFile = Lists.newArrayList();
		for (Shelf oriShelf : oriShelfs) {
			Shelf targetShelf = new Shelf();
			targetShelf.setName(oriShelf.getName());
			copyShelfFiles(targetShelf, oriShelf.getShelfFile());
			targetShelf.setDescription(oriShelf.getDescription());
			targetShelf.setStore(targetStore);
			targetShelf.setValue(oriShelf.getValue());
			this.saveShelf(targetShelf);
			for (ThemeFile file : getThemes(oriShelf.getShelfFile())) { //remove the theme file where in one store on diff shelfs 
				if (!singleThemeFile.contains(file)) {
					singleThemeFile.add(file);
				}

			}
		}
		copyFileStoreInfo(singleThemeFile, targetStore);
	}

	private List<ThemeFile> getThemes(List<ShelfFileLink> links) {
		List<ThemeFile> themes = Lists.newArrayList();
		for (ShelfFileLink shelfFile : links) {
			themes.add(shelfFile.getTheme());
		}
		return themes;
	}

	private void copyShelfFiles(Shelf targetShelf, List<ShelfFileLink> oriLinks) {
		for (ShelfFileLink ori : oriLinks) {
			ShelfFileLink targetLink = new ShelfFileLink();
			targetLink.setTheme(ori.getTheme());
			targetLink.setShelf(targetShelf);
			targetLink.setSort(ori.getSort());
			sflDao.save(targetLink);
		}

	}

	/**
	 * 整合FileStoreInfo信息
	 * 
	 */
	public void merge(Shelf shelf, List<Long> ids) {
		List<ThemeFile> themes = getThemes(shelf.getShelfFile());
		Store store = shelf.getStore();

		if (ids == null) {
			for (ThemeFile f : themes) {
				if (!isFileInStore(store, shelf, f)) {
					fileManager.deleteStoreInfoByThemeAndStore(f.getId(), store.getId());
				}
			}

			return;
		}
		List<Long> checkedIds = Lists.newArrayList();
		List<ThemeFile> checkedThemes = Lists.newArrayList();
		checkedThemes.addAll(themes);
		checkedIds.addAll(ids);

		for (ThemeFile file : checkedThemes) {
			Long id = file.getId();
			if (!checkedIds.contains(id) && !isFileInStore(store, shelf, file)) {
				fileManager.deleteStoreInfoByThemeAndStore(id, store.getId());
			} else {
				checkedIds.remove(id);
			}
		}
		for (Long id : checkedIds) {
			ThemeFile file = fileManager.getThemeFile(id);
			if (!isFileInStore(store, shelf, file)) {
				copyFileStoreInfo(file, store);
			}

		}
	}

	private boolean isFileInStore(Store store, Shelf sh, ThemeFile theme) {
		List<ThemeFile> allFileInStore = Lists.newArrayList();
		List<Shelf> shelfs = store.getShelfs();
		for (Shelf shelf : shelfs) {
			if (!shelf.equals(sh))
				allFileInStore.addAll(getThemes(shelf.getShelfFile()));
		}
		return allFileInStore.contains(theme);
	}

	private void copyFileStoreInfo(List<ThemeFile> themes, Store store) {
		for (ThemeFile theme : themes) {
			copyFileStoreInfo(theme, store);
		}
	}

	private void copyFileStoreInfo(ThemeFile theme, Store store) {
		List<FileMultipleInfo> infos = theme.getFileInfo();
		for (FileMultipleInfo fmi : infos) {
			FileStoreInfo storeInfo = new FileStoreInfo();
			storeInfo.setTitle(fmi.getTitle());
			storeInfo.setShortDescription(fmi.getShortDescription());
			storeInfo.setLongDescription(fmi.getLongDescription());
			storeInfo.setAuthor(fmi.getAuthor());
			storeInfo.setLanguage(fmi.getLanguage());
			storeInfo.setFiId(fmi.getId());
			storeInfo.setTheme(theme);
			storeInfo.setStore(store);
			fileManager.saveStoreInfo(storeInfo);
		}
	}

	@Transactional(readOnly = true)
	public boolean isStoreNameUnique(String newStoreName, String oldStoreName) {
		return storeDao.isPropertyUnique("name", newStoreName, oldStoreName);
	}

	public boolean isCategoryUnique(String newName, String oldName) {
		return categoryDao.isPropertyUnique("name", newName, oldName);
	}

	public boolean isShelfUnique(String newName, String oldName, Long id) {
		return shelfDao.isShelfNameUnique(newName, oldName, id);
	}

	@Autowired
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Autowired
	public void setStoreDao(StoreDao storeDao) {
		this.storeDao = storeDao;
	}

	@Autowired
	public void setShelfDao(ShelfDao shelfDao) {
		this.shelfDao = shelfDao;
	}

	@Autowired
	public void setFileManager(FileManager fileManager) {
		this.fileManager = fileManager;
	}

	@Autowired
	public void setSflDao(ShelfFileLinkDao sflDao) {
		this.sflDao = sflDao;
	}
}
