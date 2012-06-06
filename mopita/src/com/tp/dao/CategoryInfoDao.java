package com.tp.dao;

import org.springframework.stereotype.Component;

import com.tp.entity.CategoryInfo;
import com.tp.orm.hibernate.HibernateDao;

@Component
public class CategoryInfoDao extends HibernateDao<CategoryInfo, Long> {

}
