package com.tp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tp.dao.LogFromClientDao;
import com.tp.dao.LogInHomeDao;
import com.tp.entity.LogFromClient;
import com.tp.entity.LogInHome;

@Component
@Transactional
public class LogService {

	private LogFromClientDao logClientDao;
	private LogInHomeDao logHomeDao;

	public void saveLogFromClent(LogFromClient entity) {
		logClientDao.save(entity);
	}

	/**
	 * 查询用户量
	 */
	public Long countTotalUser(String sdate, String edate) {
		return logClientDao.countUserByDate(sdate, edate);
	}

	/**
	 * 查询客户端启用次数
	 */
	public Long countUse(String sdate, String edate) {
		return logClientDao.countOpenUseByDate(sdate, edate);
	}

	/**
	 * 查询商店访问量
	 */
	public Long countVisitHome(String sdate, String edate) {
		return logHomeDao.countByMethod("execute", sdate, edate);
	}

	/**
	 * 查询客户端总下载量
	 */
	public Long countClientDownload(String sdate, String edate) {
		return logHomeDao.countByMethod("getClient", sdate, edate);
	}

	public void saveLogInHome(LogInHome entity) {
		logHomeDao.save(entity);
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
