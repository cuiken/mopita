package com.tp.dao;

import org.springframework.stereotype.Component;

import com.tp.entity.Preview;
import com.tp.orm.hibernate.HibernateDao;

@Component
public class PreviewDao extends HibernateDao<Preview, Long> {

}
