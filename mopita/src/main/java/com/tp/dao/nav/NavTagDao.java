package com.tp.dao.nav;

import org.springframework.stereotype.Component;

import com.tp.entity.nav.NavTag;
import com.tp.orm.hibernate.HibernateDao;

@Component
public class NavTagDao extends HibernateDao<NavTag, Long> {

}
