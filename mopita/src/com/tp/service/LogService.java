package com.tp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tp.dao.LogCountClientDao;
import com.tp.dao.LogFromClientDao;
import com.tp.dao.LogInHomeDao;
import com.tp.entity.LogCountClient;
import com.tp.entity.LogFromClient;
import com.tp.entity.LogInHome;
import com.tp.orm.Page;
import com.tp.orm.PropertyFilter;

@Component
@Transactional
public class LogService {

	public static final String METHOD_EXECUTE = "execute";
	public static final String METHOD_GETCLIENT = "getClient";

	private LogFromClientDao logClientDao;
	private LogInHomeDao logHomeDao;
	private LogCountClientDao countClientDao;

	public void saveLogFromClent(LogFromClient entity) {
		logClientDao.save(entity);
	}

	public void saveLogInHome(LogInHome entity) {
		logHomeDao.save(entity);
	}

	public void saveLogCountClient(LogCountClient entity) {
		countClientDao.save(entity);
	}

	public LogCountClient getLogClientCountByDate(String date) {
		return countClientDao.findUniqueBy("createTime", date);
	}

	public Page<LogCountClient> searchLogCountClient(final Page<LogCountClient> page, final List<PropertyFilter> filters) {
		return countClientDao.findPage(page, filters);
	}

	public LogCountClient getLogCountClient(Long id) {
		return countClientDao.get(id);
	}

	public void createClientReport(String sdate, String edate) {
		LogCountClient client = new LogCountClient();
		long start = System.currentTimeMillis();
		long downTotal = countClientDownloadTotal(sdate, edate);
		long downByContent = countClientDownloadByContent(sdate, edate);
		long downByShare = countClientDownloadByShare(sdate, edate);
		long totalUser = countTotalUser(edate);
		long perTotalUser = 0L;

		LogCountClient perCount = getLogClientCountByDate(sdate);
		if (perCount != null) {
			perTotalUser = perCount.getTotalUser();
		}
		client.setCreateTime(sdate);
		client.setDownByContent(downByContent);
		client.setDownByShare(downByShare);
		client.setTotalDownload(downTotal);
		client.setDownByOther(downTotal - downByContent - downByShare);
		client.setVisitStoreCount(countVisitHome(sdate, edate));
		client.setVisitStoreUser(countVisitUser(sdate, edate));
		client.setOpenCount(countUse(sdate, edate));
		client.setTotalUser(totalUser);
		client.setIncrementUser(totalUser - perTotalUser);
		client.setOpenUser(countOpenUser(sdate, edate));
		long end = System.currentTimeMillis();
		client.setTakeTimes(end - start);
		countClientDao.save(client);
	}

	/**
	 * 查询用户量
	 */
	private Long countOpenUser(String sdate, String edate) {
		return logClientDao.countUserByDate(sdate, edate);
	}

	/**
	 * 查询总用户量
	 */

	private Long countTotalUser(String edate) {
		return logClientDao.countTotalUser(edate);
	}

	/**
	 * 查询客户端启用次数
	 */
	private Long countUse(String sdate, String edate) {
		return logClientDao.countOpenUseByDate(sdate, edate);
	}

	/**
	 * 查询商店访问量
	 */
	private Long countVisitHome(String sdate, String edate) {
		return logHomeDao.countByMethod(METHOD_EXECUTE, "%%", sdate, edate);
	}

	/**
	 * 查询商店访问用户量
	 */
	private Long countVisitUser(String sdate, String edate) {
		return logHomeDao.countUserInHome(METHOD_EXECUTE, sdate, edate);
	}

	/**
	 * 查询客户端总下载量
	 */
	private Long countClientDownloadTotal(String sdate, String edate) {
		return logHomeDao.countByMethod(METHOD_GETCLIENT, "%%", sdate, edate);
	}

	/**
	 * 分享下载客户端量
	 */
	private Long countClientDownloadByShare(String sdate, String edate) {
		return logHomeDao.countByMethod(METHOD_GETCLIENT, "%f:share%", sdate, edate);
	}

	/**
	 * 从内容下载客户端量
	 */
	private Long countClientDownloadByContent(String sdate, String edate) {
		return logHomeDao.countByMethod(METHOD_GETCLIENT, "%cv:%", sdate, edate);
	}

	@Autowired
	public void setLogClientDao(LogFromClientDao logClientDao) {
		this.logClientDao = logClientDao;
	}

	@Autowired
	public void setLogHomeDao(LogInHomeDao logHomeDao) {
		this.logHomeDao = logHomeDao;
	}

	@Autowired
	public void setCountClientDao(LogCountClientDao countClientDao) {
		this.countClientDao = countClientDao;
	}
}
