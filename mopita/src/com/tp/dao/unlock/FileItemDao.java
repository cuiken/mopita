package com.tp.dao.unlock;

import org.springframework.stereotype.Component;

import com.tp.entity.unlock.FileItem;
import com.tp.orm.hibernate.HibernateDao;

@Component
public class FileItemDao extends HibernateDao<FileItem, Long> {

}
