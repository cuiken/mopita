package com.tp.service.nav;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tp.dao.nav.ClickLogDao;
import com.tp.entity.nav.ClickLog;

@Component
@Transactional
public class ClickLogService {

	private ClickLogDao clickLogDao;

	public void saveClickLog(ClickLog entity) {
		clickLogDao.save(entity);
	}

	public Long countButtonClick(Long id, String userId) {
		String hql = "select count(log) from ClickLog log where log.buttonId=? and log.userId=?";
		return (Long) clickLogDao.createQuery(hql, id, userId).uniqueResult();
	}

	@Autowired
	public void setClickLogDao(ClickLogDao clickLogDao) {
		this.clickLogDao = clickLogDao;
	}
}
