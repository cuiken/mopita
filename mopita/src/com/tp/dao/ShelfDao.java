package com.tp.dao;

import org.springframework.stereotype.Component;

import com.tp.entity.Shelf;
import com.tp.orm.hibernate.HibernateDao;

@Component
public class ShelfDao extends HibernateDao<Shelf, Long> {

}
