package com.tp.service.nav;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tp.dao.nav.BoardDao;
import com.tp.dao.nav.NavTagDao;
import com.tp.dao.nav.NavigatorDao;
import com.tp.entity.nav.Board;
import com.tp.entity.nav.NavTag;
import com.tp.entity.nav.Navigator;

@Component
@Transactional
public class NavigatorService {

	private NavTagDao tagDao;
	private BoardDao boardDao;
	private NavigatorDao navigatorDao;

	public void saveTag(NavTag entity) {
		tagDao.save(entity);
	}

	public void saveBoard(Board entity) {
		boardDao.save(entity);
	}

	public void saveNavi(Navigator entity) {
		navigatorDao.save(entity);
	}

	@Autowired
	public void setTagDao(NavTagDao tagDao) {
		this.tagDao = tagDao;
	}

	@Autowired
	public void setBoardDao(BoardDao boardDao) {
		this.boardDao = boardDao;
	}

	@Autowired
	public void setNavigatorDao(NavigatorDao navigatorDao) {
		this.navigatorDao = navigatorDao;
	}

}
