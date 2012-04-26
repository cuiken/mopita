package com.tp.dao;

import org.springframework.stereotype.Component;

import com.tp.entity.FileMultipleInfo;
import com.tp.orm.hibernate.HibernateDao;

@Component
public class FileMultipleInfoDao extends HibernateDao<FileMultipleInfo, Long> {

}
