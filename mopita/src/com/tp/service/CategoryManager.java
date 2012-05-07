package com.tp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.tp.dao.CategoryDao;
import com.tp.dao.HibernateUtils;
import com.tp.dao.ShelfDao;
import com.tp.dao.StoreDao;
import com.tp.dto.ShelfDTO;
import com.tp.entity.Category;
import com.tp.entity.FileMultipleInfo;
import com.tp.entity.FileStoreInfo;
import com.tp.entity.Shelf;
import com.tp.entity.Store;
import com.tp.entity.ThemeFile;
import com.tp.mapper.JsonMapper;

@Component
@Transactional
public class CategoryManager {

	private CategoryDao categoryDao;
	private StoreDao storeDao;
	private ShelfDao shelfDao;
	private FileManager fileManager;

	public Category getCategory(Long id) {
		return categoryDao.get(id);
	}

	public Store getStore(Long id) {
		return storeDao.get(id);
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
		String[] defShelf = { "最新", "最热", "推荐" };
		for (int i = 0; i < defShelf.length; i++) {
			Shelf shf = new Shelf();
			shf.setName(defShelf[i]);
			shf.setDescription("默认创建");
			shf.setStore(store);
			this.saveShelf(shf);
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
			dto.setDescription(shelf.getDescription());
			sdto.add(dto);
		}
		JsonMapper mapper = JsonMapper.buildNormalMapper();
		return mapper.toJson(sdto);
	}

	public void copyAllStore(Long storeId, Store targetStore) {
		Store originalStore = this.getStore(storeId);
		List<Shelf> oriShelfs = originalStore.getShelfs();
		for (Shelf s : oriShelfs) {
			Shelf targetShelf = new Shelf();
			targetShelf.setName(s.getName());
			copyShelfFiles(targetShelf, s.getThemes());
			targetShelf.setDescription(s.getDescription());
			targetShelf.setStore(targetStore);
			this.saveShelf(targetShelf);
			copyFileStoreInfo(s.getThemes(), targetStore);
		}
	}

	private void copyShelfFiles(Shelf shelf, List<ThemeFile> themes) {

		List<Long> themeIds = Lists.newArrayList();
		for (ThemeFile file : themes) {
			themeIds.add(file.getId());
		}
		HibernateUtils.mergeByCheckedIds(shelf.getThemes(), themeIds,
				ThemeFile.class);
	}

	/**
	 * 整合FileStoreInfo信息
	 * 
	 */
	public void merge(Shelf shelf, List<Long> ids) {
		List<ThemeFile> themes = shelf.getThemes();
		Store store = shelf.getStore();

		if (ids == null) {
			for (ThemeFile f : themes) {
				if (!isFileInStore(store, shelf, f)) {
					fileManager.deleteByThemeId(f.getId());
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
				fileManager.deleteByThemeId(id);
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
				allFileInStore.addAll(shelf.getThemes());
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
}
