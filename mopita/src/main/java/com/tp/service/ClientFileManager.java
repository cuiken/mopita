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

	public void delete(Long id) {
		clientFileDao.delete(id);
	}

	public ClientFile getClientByVersion(String version) {
		return clientFileDao.findUniqueBy("version", version);
	}

	public String getNewestVersionCode(String dtype) {
		return clientFileDao.getNewestVersionCode(dtype);
	}

	public ClientFile getByVersion(String version) {
		return clientFileDao.findUniqueBy("version", version);
	}

	public String getNewestClient(String versionFromClient, String dtype) {
		if (versionFromClient == null || versionFromClient.isEmpty()) {
			return "";
		}

		String maxVersion = getNewestVersionCode(dtype);

		return compareVersion(versionFromClient, maxVersion, dtype);
	}

	public String compareVersion(String vsclient, String maxVersion, String dtype) {
		if (maxVersion != null && !maxVersion.isEmpty()) {
			String[] maxvs = StringUtils.split(maxVersion, Constants.DOT_SEPARATOR);
			String[] vs = StringUtils.split(vsclient, Constants.DOT_SEPARATOR);
			if (maxvs.length > 2 && vs.length > 2) {
				if (Integer.parseInt(maxvs[0]) < Integer.parseInt(vs[0])) {
					return "";
				}
				if (Integer.parseInt(maxvs[0]) > Integer.parseInt(vs[0])
						|| Integer.parseInt(maxvs[1]) > Integer.parseInt(vs[1])) {
					return maxVersion;
				}

				if (!dtype.equals(Constants.LOCKER_STANDARD)) {
					String maxlast = StringUtils.substring(maxvs[2], 0, maxvs[2].length() - 1);
					String vslast = StringUtils.substring(vs[2], 0, vs[2].length() - 1);
					if (Integer.parseInt(maxlast) > Integer.parseInt(vslast)) {
						return maxVersion;
					}
				} else {
					if (Integer.parseInt(maxvs[2]) > Integer.parseInt(vs[2])) {
						return maxVersion;
					}
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
