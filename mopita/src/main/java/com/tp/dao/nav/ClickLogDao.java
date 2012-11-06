package com.tp.dao.nav;

import org.springframework.stereotype.Component;

import com.tp.entity.nav.ClickLog;
import com.tp.orm.hibernate.HibernateDao;

@Component
public class ClickLogDao extends HibernateDao<ClickLog, Long> {

}
