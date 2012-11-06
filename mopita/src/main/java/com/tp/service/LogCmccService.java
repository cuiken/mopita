package com.tp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tp.dao.LogForCmccDao;
import com.tp.entity.LogForCmcc;

@Component
@Transactional
public class LogCmccService {

	private LogForCmccDao cmccDao;

	public void save(LogForCmcc entity) {
		cmccDao.save(entity);
	}

	@Autowired
	public void setCmccDao(LogForCmccDao cmccDao) {
		this.cmccDao = cmccDao;
	}
}
