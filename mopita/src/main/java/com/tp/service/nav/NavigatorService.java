package com.tp.service.nav;

import java.util.List;

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

	//tag manage
	public List<NavTag> getAllTags() {
		return tagDao.getAll();
	}

	public NavTag getNavTag(Long id) {
		return tagDao.get(id);
	}

	public void saveTag(NavTag entity) {
		tagDao.save(entity);
	}

	public void deleteTag(Long id) {
		tagDao.delete(id);
	}

	//board manage
	public List<Board> getAllBoards() {
		return boardDao.getAll();
	}

	public Board getBoard(Long id) {
		return boardDao.get(id);
	}

	public void saveBoard(Board entity) {
		boardDao.save(entity);
	}

	public void deleteBoard(Long id) {
		boardDao.delete(id);
	}

	//nav manage
	public List<Navigator> getAllNav() {
		return navigatorDao.getAll();
	}

	public Navigator getNav(Long id) {
		return navigatorDao.get(id);
	}

	public void deleteNav(Long id) {
		navigatorDao.delete(id);
	}

	public void saveNav(Navigator entity) {
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
