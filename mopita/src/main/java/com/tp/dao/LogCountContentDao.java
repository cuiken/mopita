package com.tp.dao;

import org.springframework.stereotype.Component;

import com.tp.entity.LogCountContent;
import com.tp.orm.hibernate.HibernateDao;

@Component
public class LogCountContentDao extends HibernateDao<LogCountContent, Long> {

}
