package com.tp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tp.dao.LogFromClientDao;
import com.tp.entity.LogFromClient;
import com.tp.orm.Page;
import com.tp.orm.PropertyFilter;

@Component
@Transactional
public class LogService {

	private LogFromClientDao logDao;

	public List<LogFromClient> getAll() {
		return logDao.getAll();
	}

	public void save(LogFromClient entity) {
		logDao.save(entity);
	}

	public Page<LogFromClient> searchLog(final Page<LogFromClient> pages, List<PropertyFilter> filters) {
		return logDao.findPage(pages, filters);
	}

	@Autowired
	public void setLogDao(LogFromClientDao logDao) {
		this.logDao = logDao;
	}
}
