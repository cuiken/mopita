package com.tp.dao;

import org.springframework.stereotype.Component;

import com.tp.entity.FileStore;
import com.tp.orm.hibernate.HibernateDao;

@Component
public class FileStoreDao extends HibernateDao<FileStore, Long>{

}
