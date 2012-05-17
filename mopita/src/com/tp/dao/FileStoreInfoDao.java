package com.tp.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.tp.entity.FileStoreInfo;
import com.tp.orm.Page;
import com.tp.orm.hibernate.HibernateDao;

@Component
public class FileStoreInfoDao extends HibernateDao<FileStoreInfo, Long> {

	private static final String DELETE_BY_THEME_AND_STORE = "delete from FileStoreInfo fsi where fsi.theme.id=? and fsi.store.id=?";
	private static final String DELETE_BY_FID = "delete from FileStoreInfo fsi where fsi.fiId=?";

	private static final String QUERY_BY_THEME_STORE = "select fsi from FileStoreInfo fsi where fsi.theme.id=? and fsi.store.id=?";
	private static final String QUERY_BY_FID = "select fsi from FileStoreInfo fsi where fsi.fiId=?";

	private static final String Q_BY_ = "select fsi from FileStoreInfo fsi where fsi.store.id=? and fsi.theme.id=? and fsi.language=?";

	private static final String Q_SHELF_THEME_STORE = "select fsi from FileStoreInfo fsi join fsi.theme.shelfFiles s where s.shelf.value=? and s.shelf.store.id=? and fsi.language=? order by s.sort";

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

	/**
	 * 查找指定货架上文件的语言信息
	 * @param page
	 * @param sid
	 * @param language
	 * @return
	 */
	public Page<FileStoreInfo> searchStoreInfoInShelf(final Page<FileStoreInfo> page, String st,Long storeId,String language) {
		return findPage(page, Q_SHELF_THEME_STORE, st,storeId,language);
	}

}
