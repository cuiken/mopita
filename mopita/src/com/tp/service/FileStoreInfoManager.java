package com.tp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tp.dao.FileStoreInfoDao;
import com.tp.entity.FileStoreInfo;

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
	
	public List<FileStoreInfo> getByTheme(Long tid,Long sid){
		return storeInfoDao.getInfoByTheme(tid,sid);
	}

	@Autowired
	public void setStoreInfoDao(FileStoreInfoDao storeInfoDao) {
		this.storeInfoDao = storeInfoDao;
	}

}
