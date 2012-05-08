package com.tp.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.tp.entity.FileStoreInfo;
import com.tp.orm.hibernate.HibernateDao;

@Component
public class FileStoreInfoDao extends HibernateDao<FileStoreInfo, Long> {

	private static final String DELETE_BY_THEME = "delete from FileStoreInfo fsi where fsi.theme.id=?";
	private static final String QUERY_BY_THEME = "select fsi from FileStoreInfo fsi where fsi.theme.id=? and fsi.store.id=?";

	public void deleteByTheme(Long id) {
		createQuery(DELETE_BY_THEME, id).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<FileStoreInfo> getInfoByTheme(Long themeId,Long storeId) {
		return createQuery(QUERY_BY_THEME, themeId,storeId).list();
	}
}
