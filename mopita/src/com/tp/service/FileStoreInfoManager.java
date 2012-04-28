package com.tp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tp.dao.FileStoreInfoDao;
import com.tp.entity.FileMultipleInfo;
import com.tp.entity.FileStoreInfo;
import com.tp.entity.Store;
import com.tp.entity.ThemeFile;

@Component
@Transactional
public class FileStoreInfoManager {

	private FileStoreInfoDao storeInfoDao;

	public FileStoreInfo get(Long id) {
		return storeInfoDao.get(id);
	}

	public void save(FileStoreInfo entity) {
		storeInfoDao.save(entity);
	}

	public void delete(Long id) {
		storeInfoDao.delete(id);
	}
	
	public void deleteByThemeId(Long id){
		
	}

	public void copyFileInfoToStore(List<ThemeFile> files, Store store) {
		for (ThemeFile file : files) {
			List<FileMultipleInfo> infos = file.getFileInfo();
			for (FileMultipleInfo fmi : infos) {
				FileStoreInfo fsi = new FileStoreInfo();
				fsi.setTitle(fmi.getTitle());
				fsi.setDescription(fmi.getDescription());
				fsi.setLanguage(fmi.getLanguage());
				fsi.setPrice(fmi.getPrice());
				fsi.setTheme(fmi.getTheme());
				fsi.setStore(store);
				this.save(fsi);
			}
		}
	}

	public void merge(List<ThemeFile> existFiles, List<Long> checkedIds) {
		if (checkedIds == null) {
			existFiles.clear();
			return;
		}
		for(ThemeFile file:existFiles){
			Long id=file.getId();
			if(!checkedIds.contains(id)){
//				deleteInfo(id);
			}else{
				checkedIds.remove(id);
			}
		}
	}

	@Autowired
	public void setStoreInfoDao(FileStoreInfoDao storeInfoDao) {
		this.storeInfoDao = storeInfoDao;
	}
}
