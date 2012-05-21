package com.tp.dao;

import org.springframework.stereotype.Component;

import com.tp.entity.FileInfo;
import com.tp.orm.hibernate.HibernateDao;

@Component
public class FileInfoDao extends HibernateDao<FileInfo, Long> {

}
