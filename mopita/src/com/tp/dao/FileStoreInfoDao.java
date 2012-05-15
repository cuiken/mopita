package com.tp.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.tp.entity.FileStoreInfo;
import com.tp.orm.hibernate.HibernateDao;

@Component
public class FileStoreInfoDao extends HibernateDao<FileStoreInfo, Long> {

	private static final String DELETE_BY_THEME_AND_STORE = "delete from FileStoreInfo fsi where fsi.theme.id=? and fsi.store.id=?";
	private static final String DELETE_BY_FID = "delete from FileStoreInfo fsi where fsi.fiId=?";

	private static final String QUERY_BY_THEME_STORE = "select fsi from FileStoreInfo fsi where fsi.theme.id=? and fsi.store.id=?";
	private static final String QUERY_BY_FID = "select fsi from FileStoreInfo fsi where fsi.fiId=?";

	private static final String Q_BY_ = "select fsi from FileStoreInfo fsi where fsi.store.id=? and fsi.theme.id=? and fsi.language=?";

	public void deleteByThemeAndStore(Long fid, Long sid) {
		createQuery(DELETE_BY_THEME_AND_STORE, fid, sid).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<FileStoreInfo> getInfoByTheme(Long themeId, Long storeId) {
		return createQuery(QUERY_BY_THEME_STORE, themeId, storeId).list();
	}

	public void deleteByFileInfo(Long fid) {
		createQuery(DELETE_BY_FID, fid).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<FileStoreInfo> getByFileInfo(Long fiId) {
		return createQuery(QUERY_BY_FID, fiId).list();
	}

	public FileStoreInfo get(Long sid, Long fid, String language) {
		return findUnique(Q_BY_, sid, fid, language);
	}

}
