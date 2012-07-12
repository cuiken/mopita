package com.tp.dao;

import org.springframework.stereotype.Component;

import com.tp.entity.Preview;
import com.tp.orm.hibernate.HibernateDao;

@Component
public class PreviewDao extends HibernateDao<Preview, Long> {

	private static final String DELETE_BY_THEME = "delete from Preview p where p.theme.id=?";

	public void deleteByTheme(Long tid) {
		createQuery(DELETE_BY_THEME, tid).executeUpdate();
	}
}
