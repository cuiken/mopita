package com.tp.dao;

import org.springframework.stereotype.Component;

import com.tp.entity.FileStoreInfo;
import com.tp.orm.hibernate.HibernateDao;

@Component
public class FileStoreInfoDao extends HibernateDao<FileStoreInfo, Long> {

}
