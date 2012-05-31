package com.tp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tp.dao.LogFromClientDao;
import com.tp.dao.LogInHomeDao;
import com.tp.entity.LogFromClient;
import com.tp.entity.LogInHome;
import com.tp.orm.Page;
import com.tp.orm.PropertyFilter;

@Component
@Transactional
public class LogService {

	private LogFromClientDao logClientDao;
	private LogInHomeDao logHomeDao;

	public void saveLogFromClent(LogFromClient entity) {
		logClientDao.save(entity);
	}

	public Page<LogFromClient> searchLogFromClient(final Page<LogFromClient> pages, List<PropertyFilter> filters) {
		return logClientDao.findPage(pages, filters);
	}

	public void saveLogInHome(LogInHome entity) {
		logHomeDao.save(entity);
	}

	public Page<LogInHome> searchLogInHome(final Page<LogInHome> pages, List<PropertyFilter> filters) {
		return logHomeDao.findPage(pages, filters);
	}

	@Autowired
	public void setLogClientDao(LogFromClientDao logClientDao) {
		this.logClientDao = logClientDao;
	}

	@Autowired
	public void setLogHomeDao(LogInHomeDao logHomeDao) {
		this.logHomeDao = logHomeDao;
	}
}
