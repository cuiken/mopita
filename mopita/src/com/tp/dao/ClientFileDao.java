package com.tp.dao;

import org.springframework.stereotype.Component;

import com.tp.entity.ClientFile;
import com.tp.orm.hibernate.HibernateDao;

@Component
public class ClientFileDao extends HibernateDao<ClientFile, Long> {

	public static final String QUERY_MAX_VERSION = "select max(cf.version) from ClientFile cf where cf.version like ? and cf.dtype=?";
	public static final String QUERY_NEWEST = "select max(cf.version) from ClientFile cf where cf.dtype=?";

	public String getMaxByVersion(String version, String dtype) {
		return (String) createQuery(QUERY_MAX_VERSION, version + "%", dtype).uniqueResult();
	}

	public String getNewestVersionCode(String dtype) {
		return (String) createQuery(QUERY_NEWEST, dtype).uniqueResult();
	}
}
