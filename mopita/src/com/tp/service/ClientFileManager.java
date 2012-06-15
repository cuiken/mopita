package com.tp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tp.dao.ClientFileDao;
import com.tp.entity.ClientFile;

@Component
@Transactional
public class ClientFileManager {

	private ClientFileDao clientFileDao;

	public List<ClientFile> getAll() {
		return clientFileDao.getAll();
	}

	public void save(ClientFile entity) {
		clientFileDao.save(entity);
	}

	public ClientFile get(Long id) {
		return clientFileDao.get(id);
	}

	public ClientFile getClientByVersion(String version) {
		return clientFileDao.findUniqueBy("version", version);
	}

	public String getMaxByVersion(String v) {
		return clientFileDao.getMaxByVersion(v);
	}

	public String getNewestVersionCode() {
		return clientFileDao.getNewestVersionCode();
	}

	@Autowired
	public void setClientFileDao(ClientFileDao clientFileDao) {
		this.clientFileDao = clientFileDao;
	}
}
