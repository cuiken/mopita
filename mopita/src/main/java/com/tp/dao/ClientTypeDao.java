package com.tp.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tp.entity.ClientType;
import com.tp.orm.hibernate.HibernateDao;

@Component
@Transactional
public class ClientTypeDao extends HibernateDao<ClientType, Long> {

}
