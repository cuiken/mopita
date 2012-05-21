package com.tp.dao.unlock;

import org.springframework.stereotype.Component;

import com.tp.entity.unlock.UnlockFile;
import com.tp.orm.Page;
import com.tp.orm.hibernate.HibernateDao;

@Component
public class UnlockFileDao extends HibernateDao<UnlockFile, Long> {
	private static final String QUERY_FILE_BY_CATEGORY = "select f from UnlockFile f join f.categories c where c.id=?";
	private static final String Q_BY_SHELF = "select f from UnlockFile f join f.shelfFiles s where s.shelf.value=? and s.shelf.store.id=? order by s.sort";

	private static final String Q_BY_STORE_AND_CATEGORY = "select distinct f from UnlockFile f join f.shelfFiles s join f.categories c join f.infoStore is where s.shelf.store.id=? and c.id=? and is.language=? order by s.sort";

	public Page<UnlockFile> searchFileByCategory(final Page<UnlockFile> page, Long categoryId) {

		return findPage(page, QUERY_FILE_BY_CATEGORY, categoryId);
	}

	public Page<UnlockFile> searchFileByShelf(final Page<UnlockFile> page, String shelfType, Long sid) {
		return findPage(page, Q_BY_SHELF, shelfType, sid);
	}

	public Page<UnlockFile> searchFileByStoreAndCategory(final Page<UnlockFile> page, Long sid, Long cid, String lang) {
		return findPage(page, Q_BY_STORE_AND_CATEGORY, sid, cid, lang);
	}
}
