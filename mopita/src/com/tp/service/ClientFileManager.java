package com.tp.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tp.dao.ClientFileDao;
import com.tp.entity.ClientFile;
import com.tp.utils.Constants;

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

	public String getNewestClient(String versionFromClient) {
		if (versionFromClient == null || versionFromClient.isEmpty()) {
			return "";
		}
		String[] versions = StringUtils.split(versionFromClient, Constants.DOT_SEPARATOR);
		if (versions.length > 2) {
			String oldHeader = versions[0];
			String newVersion = this.getMaxByVersion(oldHeader);
			if (newVersion == null)
				return "";
			String[] newvs = StringUtils.split(newVersion, Constants.DOT_SEPARATOR);
			String newHeader = newvs[0];
			String newUse = newvs[1];
			String oldUse = versions[1];
			if (oldHeader.equals(newHeader)) {
				if (Integer.valueOf(oldUse) < Integer.valueOf(newUse)) {

					return newVersion;
				}
			}
		}
		return "";
	}

	@Autowired
	public void setClientFileDao(ClientFileDao clientFileDao) {
		this.clientFileDao = clientFileDao;
	}
}
