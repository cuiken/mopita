package com.tp.dao;

import org.springframework.stereotype.Component;

import com.tp.entity.FileStore;
import com.tp.orm.Page;
import com.tp.orm.hibernate.HibernateDao;

@Component
public class FileStoreDao extends HibernateDao<FileStore, Long>{
	private static final String QUERY_FILE_BY_CATEGORY = "select f from FileStore f join f.categories c where c.id=?";

	public Page<FileStore> searchFileByCategory(final Page<FileStore> page,
			Long categoryId) {

		return findPage(page, QUERY_FILE_BY_CATEGORY, categoryId);
	}
}
