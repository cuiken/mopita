package com.tp.dao;

import org.springframework.stereotype.Component;

import com.tp.entity.LogInHome;
import com.tp.orm.hibernate.HibernateDao;

@Component
public class LogInHomeDao extends HibernateDao<LogInHome, Long> {

}
