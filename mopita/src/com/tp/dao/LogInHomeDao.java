package com.tp.dao;

import org.springframework.stereotype.Component;

import com.tp.entity.LogInHome;
import com.tp.orm.hibernate.HibernateDao;

@Component
public class LogInHomeDao extends HibernateDao<LogInHome, Long> {

	public static final String COUNT_BY_METHOD = "select count(*) from LogInHome l where l.requestMethod=? and l.requestParams like ? and l.createTime between ? and ?";
	public static final String COUNT_USER_HOME = "select count(distinct l.imei) from LogInHome l where l.requestMethod=? and l.createTime between ? and ?";

	public Long countByMethod(String method, String params, String sDate, String eDate) {
		return (Long) createQuery(COUNT_BY_METHOD, method, params, sDate, eDate).uniqueResult();
	}

	public Long countUserInHome(String method,String sdate, String edate) {
		return (Long) createQuery(COUNT_USER_HOME, method, sdate, edate).uniqueResult();
	}
}
