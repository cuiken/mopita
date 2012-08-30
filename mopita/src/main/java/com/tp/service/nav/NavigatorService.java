package com.tp.service.nav;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tp.dao.nav.BoardDao;
import com.tp.dao.nav.BoardIconDao;
import com.tp.dao.nav.NavTagDao;
import com.tp.dao.nav.NavigatorDao;
import com.tp.dao.nav.NavigatorIconDao;
import com.tp.dao.nav.TagIconDao;
import com.tp.entity.nav.Board;
import com.tp.entity.nav.BoardIcon;
import com.tp.entity.nav.NavigatorIcon;
import com.tp.entity.nav.Tag;
import com.tp.entity.nav.Navigator;
import com.tp.entity.nav.TagIcon;

@Component
@Transactional
public class NavigatorService {

	private NavTagDao tagDao;
	private BoardDao boardDao;
	private NavigatorDao navigatorDao;
	private BoardIconDao boardIconDao;
	private TagIconDao tagIconDao;
	private NavigatorIconDao navIconDao;

	//board icon
	public void saveBoardIcon(BoardIcon entity) {
		boardIconDao.save(entity);
	}

	//tag icon
	public void saveTagIcon(TagIcon entity) {
		tagIconDao.save(entity);
	}

	//nav icon
	public void saveNavIcon(NavigatorIcon entity) {
		navIconDao.save(entity);
	}

	//tag manage
	public List<Tag> getAllTags() {
		return tagDao.getAll();
	}

	public Tag getNavTag(Long id) {
		return tagDao.get(id);
	}

	public void saveTag(Tag entity) {
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

	@Autowired
	public void setBoardIconDao(BoardIconDao boardIconDao) {
		this.boardIconDao = boardIconDao;
	}

	@Autowired
	public void setTagIconDao(TagIconDao tagIconDao) {
		this.tagIconDao = tagIconDao;
	}

	@Autowired
	public void setNavIconDao(NavigatorIconDao navIconDao) {
		this.navIconDao = navIconDao;
	}
}
