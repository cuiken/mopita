package com.tp.dao;

import org.springframework.stereotype.Component;

import com.tp.entity.FileInfo;
import com.tp.orm.Page;
import com.tp.orm.hibernate.HibernateDao;

@Component
public class FileInfoDao extends HibernateDao<FileInfo, Long> {

	private static final String QUERY_FILE_BY_CATEGORY = "select f from FileInfo f join f.categories c where c.id=?";

	public Page<FileInfo> searchFileByCategory(final Page<FileInfo> page,
			Long categoryId) {

		return findPage(page, QUERY_FILE_BY_CATEGORY, categoryId);
	}
}
