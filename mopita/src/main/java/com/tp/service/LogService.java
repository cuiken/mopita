package com.tp.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.search.IndexSearcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tp.dao.LogCountClientDao;
import com.tp.dao.LogCountContentDao;
import com.tp.dao.LogCountContentMarketDao;
import com.tp.dao.LogForContentDao;
import com.tp.dao.LogFromClientDao;
import com.tp.dao.LogInHomeDao;
import com.tp.dao.MarketDao;
import com.tp.dao.ThemeFileDao;
import com.tp.entity.LogContentMarket;
import com.tp.entity.LogCountClient;
import com.tp.entity.LogCountContent;
import com.tp.entity.LogForContent;
import com.tp.entity.LogFromClient;
import com.tp.entity.LogInHome;
import com.tp.entity.Market;
import com.tp.entity.ThemeFile;
import com.tp.orm.Page;
import com.tp.orm.PropertyFilter;
import com.tp.search.LogSearch;
import com.tp.utils.DateFormatUtils;

@Component
@Transactional
public class LogService {

	public static final String METHOD_EXECUTE = "execute";
	public static final String METHOD_GETCLIENT = "getClient";

	private LogFromClientDao logClientDao;
	private LogInHomeDao logHomeDao;
	private LogForContentDao logContentDao;
	private LogCountClientDao countClientDao;
	private LogCountContentDao countContentDao;
	private LogCountContentMarketDao ccMarketDao;
	private ThemeFileDao themeDao;
	private MarketDao marketDao;

	private LogSearch logSearch;

	public List<LogInHome> queryLogInHomeByDate(String sdate, String edate) {
		return logHomeDao.queryByDate(sdate, edate);
	}

	public void saveLogFromClent(LogFromClient entity) {
		logClientDao.save(entity);
	}

	public void saveLogContent(LogForContent entity) {
		logContentDao.save(entity);
	}
	
	public void saveLogContents(List<LogForContent> contents){
		for(LogForContent content:contents){
			logContentDao.save(content);
		}
	}

	public void saveLogInHome(LogInHome entity) {
		logHomeDao.save(entity);
	}

	public void saveLogCountClient(LogCountClient entity) {
		countClientDao.save(entity);
	}

	public List<LogCountContent> getAllContents() {
		return countContentDao.getAll();
	}

	public List<LogCountContent> getContentByThemeOrDate(String theme, String date) {
		return countContentDao.getByContentOrDate(theme, date);
	}

	public LogCountClient getLogClientCountByDate(String date) {
		return countClientDao.findUniqueBy("createTime", date);
	}

	public Page<LogCountClient> searchLogCountClient(final Page<LogCountClient> page, final List<PropertyFilter> filters) {
		return countClientDao.findPage(page, filters);
	}

	public Page<LogCountContent> searchLogCountContent(final Page<LogCountContent> page,
			final List<PropertyFilter> filters) {
		return countContentDao.findPage(page, filters);
	}

	public LogCountClient getLogCountClient(Long id) {
		return countClientDao.get(id);
	}

	public void createClientReport(IndexSearcher searcher, String sdate, String edate) throws Exception {
		LogCountClient client = new LogCountClient();
		long start = System.currentTimeMillis();
		long downTotal = logHomeDao.countClientDownload(METHOD_GETCLIENT, sdate, edate);
		long downByContent = logHomeDao.countClientDownByContent(METHOD_GETCLIENT, "%cv:%", sdate, edate);
		long downByShare = logSearch.downloadByShare(searcher, sdate, edate);
		long totalUser = countTotalUser(edate);
		long perTotalUser = 0L;

		LogCountClient perCount = getLogClientCountByDate(DateFormatUtils.getPerDate(sdate));
		if (perCount != null) {
			perTotalUser = perCount.getTotalUser();
		}
		client.setCreateTime(sdate);
		client.setDownByContent(downByContent);
		client.setDownByShare(downByShare);
		client.setTotalDownload(downTotal);
		client.setDownByOther(downTotal - downByContent - downByShare);
		client.setVisitStoreCount(logSearch.storeVisits(searcher, sdate, edate));
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
	 * 查询商店访问用户量
	 */
	private Long countVisitUser(String sdate, String edate) {
		return logHomeDao.countUserInHome(METHOD_EXECUTE, sdate, edate);
	}

	public void createContentReport(IndexSearcher searcher, String sdate, String edate) throws Exception {
		List<ThemeFile> themes = themeDao.getAll();
		List<Market> markets = marketDao.getAll();
		for (ThemeFile theme : themes) {
			LogCountContent lcct = new LogCountContent();

			String fid = String.valueOf(theme.getId());
			long totalVisit = logSearch.contentVisits(searcher, fid, sdate, edate);
			long visitByAD = logSearch.contentVisitByAD(searcher, fid, sdate, edate);
			long marketDown = logSearch.contentDownFromMarket(searcher, theme.getMarketURL(), sdate, edate);
			long selfDown = logSearch.contentDownFromStore(searcher, fid, sdate, edate);

			lcct.setLogDate(sdate);
			lcct.setThemeName(theme.getTitle());
			lcct.setTotalVisit(totalVisit);
			lcct.setTotalDown(marketDown + selfDown);
			lcct.setVisitByAd(visitByAD);
			lcct.setVisitByStore(totalVisit - visitByAD);
			lcct.setDownByStore(selfDown);
			countContentDao.save(lcct);
			perMarketDown(searcher, theme, lcct, markets, sdate, edate);
		}
	}

	private void perMarketDown(IndexSearcher searcher, ThemeFile theme, LogCountContent lcc, List<Market> markets,
			String sdate, String edate) throws Exception {

		for (Market market : markets) {
			if (market.getThemes().contains(theme)) {
				LogContentMarket ccMarket = new LogContentMarket();

				String marketKey = market.getMarketKey();
				marketKey = escape(marketKey);
				if (marketKey.equals("marketclient")) {
					marketKey = market.getPkName();
				}
				String fpack = theme.getMarketURL();
				long perMarketDown = logSearch.contentPerMarketDown(searcher, sdate, edate, marketKey, fpack);
				ccMarket.setMarketName(market.getName());
				ccMarket.setTotalDown(perMarketDown);
				ccMarket.setLogContent(lcc);
				ccMarketDao.save(ccMarket);
			}
		}
	}

	private String escape(String marketKey) {
		if (marketKey != null && !marketKey.isEmpty()) {
			String[] strs = StringUtils.split(marketKey, ":");
			if (strs.length > 0) {
				return strs[0];
			}
		}
		return "";

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

	@Autowired
	public void setThemeDao(ThemeFileDao themeDao) {
		this.themeDao = themeDao;
	}

	@Autowired
	public void setMarketDao(MarketDao marketDao) {
		this.marketDao = marketDao;
	}

	@Autowired
	public void setCcMarketDao(LogCountContentMarketDao ccMarketDao) {
		this.ccMarketDao = ccMarketDao;
	}

	@Autowired
	public void setCountContentDao(LogCountContentDao countContentDao) {
		this.countContentDao = countContentDao;
	}

	@Autowired
	public void setLogContentDao(LogForContentDao logContentDao) {
		this.logContentDao = logContentDao;
	}

	@Autowired
	public void setLogSearch(LogSearch logSearch) {
		this.logSearch = logSearch;
	}
}
