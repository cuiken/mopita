package com.tp.dao;

import org.springframework.stereotype.Component;

import com.tp.entity.FileStoreInfo;
import com.tp.orm.hibernate.HibernateDao;

@Component
public class FileStoreInfoDao extends HibernateDao<FileStoreInfo, Long> {
	private static final String DELETE_BY_THEME = "delete from FileStoreInfo fsi where fsi.theme.id=?";

	public void deleteByTheme(Long id) {
		createQuery(DELETE_BY_THEME, id).executeUpdate();
	}

}
