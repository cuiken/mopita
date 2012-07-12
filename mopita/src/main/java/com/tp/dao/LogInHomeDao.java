package com.tp.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.tp.entity.LogInHome;
import com.tp.orm.hibernate.HibernateDao;

@Component
public class LogInHomeDao extends HibernateDao<LogInHome, Long> {

	private static final String COUNT_BY_METHOD = "select count(*) from LogInHome l where l.requestMethod=? and l.requestParams like ? and l.createTime between ? and ?";
	private static final String COUNT_USER_HOME = "select count(distinct l.imei) from LogInHome l where l.requestMethod=? and l.createTime between ? and ?";

	private static final String COUNT_CONTENT_BY_METHOD = "select count(*) from LogInHome l where l.requestMethod=? and l.requestParams like ? and l.createTime between ? and ?";
	private static final String COUNT_CONTENT_NOT_IN_METHOD = "select count(*) from LogInHome l where l.requestMethod not in (:methods) and l.requestParams like :param and l.createTime between :sdate and :edate";
	private static final String COUNT_CONTENT_TOTAL_DOWN = "select count(*) from LogInHome l where l.requestMethod not in (:methods) and (l.requestParams like :fpack or l.requestParams like :fid) and l.createTime between :sdate and :edate";
	private static final String COUNT_CONTENT_PER_MARKET = "select count(*) from LogInHome l where l.requestParams like ? and l.createTime between ? and ?";

	public Long countByMethod(String method, String params, String sDate, String eDate) {
		return (Long) createQuery(COUNT_BY_METHOD, method, params, sDate, eDate).uniqueResult();
	}

	public Long countUserInHome(String method, String sdate, String edate) {
		return (Long) createQuery(COUNT_USER_HOME, method, sdate, edate).uniqueResult();
	}

	public Long countContentByMethod(String method, String param, String sdate, String edate) {
		return (Long) createQuery(COUNT_CONTENT_BY_METHOD, method, param, sdate, edate).uniqueResult();
	}

	public Long countContentNotIn(List<String> methods, String param, String sdate, String edate) {
		Query query = createQuery(COUNT_CONTENT_NOT_IN_METHOD);
		query.setParameterList("methods", methods);
		query.setParameter("param", param);
		query.setParameter("sdate", sdate);
		query.setParameter("edate", edate);
		return (Long) query.uniqueResult();
	}

	public Long countContentTotalDown(List<String> methods, String fpack, String fid, String sdate, String edate) {
		Query query = createQuery(COUNT_CONTENT_TOTAL_DOWN);
		query.setParameterList("methods", methods);
		query.setParameter("fpack", fpack);
		query.setParameter("fid", fid);
		query.setParameter("sdate", sdate);
		query.setParameter("edate", edate);
		return (Long) query.uniqueResult();
	}

	public Long countContentPerMarketDown(String marketKeyAndFpack, String sdate, String edate) {
		return (Long) createQuery(COUNT_CONTENT_PER_MARKET, marketKeyAndFpack, sdate, edate).uniqueResult();
	}
}
