package com.tp.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.tp.entity.Preview;
import com.tp.orm.hibernate.HibernateDao;

@Component
public class PreviewDao extends HibernateDao<Preview, Long> {

	private static final String Q_AD = "select p from Preview p where p.theme.id=? and p.name LIKE ?";

	@SuppressWarnings("unchecked")
	public Preview get_ad_preview(Long fid) {
		List<Preview> ps = createQuery(Q_AD, fid, "%ad_%").list();

		if (!ps.isEmpty())
			return ps.get(0);
		return null;
	}
}
