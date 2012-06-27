package com.tp.dao;

import org.springframework.stereotype.Component;

import com.tp.entity.LogFromClient;
import com.tp.orm.hibernate.HibernateDao;

@Component
public class LogFromClientDao extends HibernateDao<LogFromClient, Long> {

	private static final String COUNT_USER = "select count(distinct(c.imei)) from LogFromClient c where c.createTime between ? and ?";
	private static final String COUNT_OPEN_USE = "select count(c.imei) from LogFromClient c where c.createTime between ? and ?";

	public Long countUserByDate(String sDate, String eDate) {
		return (Long) createQuery(COUNT_USER, sDate, eDate).uniqueResult();
	}

	public Long countOpenUseByDate(String sDate, String eDate) {
		return (Long) createQuery(COUNT_OPEN_USE, sDate, eDate).uniqueResult();
	}
}
