package com.tp.dao;

import org.springframework.stereotype.Component;

import com.tp.entity.LogInHome;
import com.tp.orm.hibernate.HibernateDao;

@Component
public class LogInHomeDao extends HibernateDao<LogInHome, Long> {

	public static final String COUNT_BY_METHOD = "select count(*) from LogInHome l where l.requestMethod=? and l.createTime between ? and ?";

	public Long countBYMethod(String method, String sDate, String eDate) {
		return (Long) createQuery(method, sDate, eDate).uniqueResult();
	}
}
