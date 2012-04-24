package com.tp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tp.dao.CategoryDao;
import com.tp.dao.ShelfDao;
import com.tp.dao.StoreDao;
import com.tp.entity.Category;
import com.tp.entity.Shelf;
import com.tp.entity.Store;

@Component
@Transactional
public class CategoryManager {

	private CategoryDao categoryDao;
	private StoreDao storeDao;
	private ShelfDao shelfDao;

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
	
	public void createDefaultShelf(Store store){
		String [] defShelf={"最新","最热","推荐"};
		for(int i=0;i<defShelf.length;i++){
			Shelf shf=new Shelf();
			shf.setName(defShelf[i]);
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

	public void copyAllStore(Long storeId, Store targetStore) {
		Store originalStore = this.getStore(storeId);
		List<Shelf> oriShelfs = originalStore.getShelfs();
		for (Shelf s : oriShelfs) {
			Shelf targetShelf = new Shelf();
			targetShelf.setName(s.getName());
			targetShelf.setDescription(s.getDescription());
			targetShelf.setStore(targetStore);
			this.saveShelf(targetShelf);
		}
	}

	@Transactional(readOnly = true)
	public boolean isStoreNameUnique(String newStoreName, String oldStoreName) {
		return storeDao.isPropertyUnique("name", newStoreName, oldStoreName);
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
}
