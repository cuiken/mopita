package com.tp.dao;

import org.springframework.stereotype.Component;

import com.tp.entity.LogFromClient;
import com.tp.orm.hibernate.HibernateDao;

@Component
public class LogFromClientDao extends HibernateDao<LogFromClient, Long> {

}
