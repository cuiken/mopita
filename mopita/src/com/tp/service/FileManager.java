package com.tp.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.tp.dao.FileMultipleInfoDao;
import com.tp.dao.FileStoreInfoDao;
import com.tp.dao.ThemeFileDao;
import com.tp.dto.FileDTO;
import com.tp.entity.FileMultipleInfo;
import com.tp.entity.FileStoreInfo;
import com.tp.entity.Shelf;
import com.tp.entity.ThemeFile;
import com.tp.mapper.JsonMapper;
import com.tp.orm.Page;
import com.tp.orm.PropertyFilter;
import com.tp.utils.FileUtils;

@Component
@Transactional
public class FileManager {

	private FileMultipleInfoDao fileMultipleDao;
	private ThemeFileDao themeFileDao;
	private FileStoreInfoDao storeInfoDao;

	public FileMultipleInfo getFileInfo(Long id) {
		return fileMultipleDao.get(id);
	}

	public List<ThemeFile> getAllThemeFile() {
		return themeFileDao.getAll();
	}

	public Page<ThemeFile> searchFileByShelf(final Page<ThemeFile> page, Shelf.Type stype, Long sid) {
		return themeFileDao.searchFileByShelf(page, stype.getValue(), sid);
	}

//	public Page<ThemeFile> searchFileByStoreAndCategory(final Page<ThemeFile> page, Long sid, Long cid,String lang) {
//		return themeFileDao.searchFileByStoreAndCategory(page, sid, cid,lang);
//	}

	public Page<FileStoreInfo> searchInfoByCategoryAndStore(final Page<FileStoreInfo> page, Long cid, Long sid,
			String lang) {
		return storeInfoDao.searchByCategoryAndStore(page, cid, sid, lang);
	}

	/**
	 * 判断该条语言信息是否存在于商店中
	 * @param fiId 
	 * @return
	 */
	public boolean isInfoInStore(Long fiId) {
		return !getStoreInfoByFiId(fiId).isEmpty();
	}

	public List<FileStoreInfo> getStoreInfoByFiId(Long fiId) {
		return storeInfoDao.getByFileInfo(fiId);
	}

	public FileStoreInfo getStoreInfoBy(Long sid, Long fid, String language) {
		return storeInfoDao.get(sid, fid, language);
	}

	public Page<ThemeFile> searchThemeFile(final Page<ThemeFile> page, final List<PropertyFilter> filters) {
		return themeFileDao.findPage(page, filters);
	}

	public Page<ThemeFile> searchThemeFile(final Page<ThemeFile> page, Long categoryId) {
		return themeFileDao.searchFileByCategory(page, categoryId);
	}

	public Page<FileMultipleInfo> searchFileInfo(final Page<FileMultipleInfo> page, final List<PropertyFilter> filters) {
		return fileMultipleDao.findPage(page, filters);
	}

	public Page<FileStoreInfo> searchStoreInfoInShelf(final Page<FileStoreInfo> page, Shelf.Type newest, Long sid,
			String language) {
		return storeInfoDao.searchStoreInfoInShelf(page, newest.getValue(), sid, language);
	}

	public ThemeFile saveFiles(List<File> files, ThemeFile fs, FileMultipleInfo info) {

		for (File file : files) {
			String fname = FileUtils.getFileName(file.getName());
			String extension = FileUtils.getExtension(file.getName());
			if (FileUtils.isPreClient(fname)) {
				fs.setPreClientPath(file.getPath());
			} else if (FileUtils.isPreWeb(fname)) {
				fs.setPreWebPath(file.getPath());
			} else if (FileUtils.isAd(fname)) {
				fs.setAdPath(file.getPath());
			} else if (FileUtils.isIcon(fname)) {
				fs.setIconPath(file.getPath());
			} else if (FileUtils.isApk(extension)) {

				fs.setApkSize(FileUtils.getFileSize(file.getPath()));
				fs.setApkPath(file.getPath());
			} else if (FileUtils.isUx(extension)) {

				fs.setUxPath(file.getPath());
				fs.setUxSize(FileUtils.getFileSize(file.getPath()));
			}
		}
		saveThemeFile(fs);
		saveFileinfo(fs, info);
		return fs;
	}

	private void saveFileinfo(ThemeFile f, FileMultipleInfo info) {
		info.setTheme(f);
		saveFileInfo(info);
	}

	public void saveFileInfo(FileMultipleInfo file) {
		fileMultipleDao.save(file);
	}

	public ThemeFile getThemeFile(Long id) {
		return themeFileDao.get(id);
	}

	public void saveThemeFile(ThemeFile entity) {
		themeFileDao.save(entity);
	}

	public void deleteFileInfo(Long id) {
		fileMultipleDao.delete(id);
	}

	public void deleteThemeFile(Long id) {
		themeFileDao.delete(id);
	}

	public List<ThemeFile> getRemainFiles(List<ThemeFile> allFiles, List<ThemeFile> fileOnShelf) {
		List<ThemeFile> remainFile = allFiles;
		for (ThemeFile fi : fileOnShelf) {

			remainFile.remove(fi);
		}
		return remainFile;
	}

	public FileStoreInfo getStoreInfo(Long id) {
		return storeInfoDao.get(id);
	}

	public void saveStoreInfo(FileStoreInfo entity) {
		storeInfoDao.save(entity);
	}

	public void deleteStoreInfo(Long id) {
		storeInfoDao.delete(id);
	}

	public void deleteStoreInfoByFmId(Long fid) {
		storeInfoDao.deleteByFileInfo(fid);
	}

	public void deleteStoreInfoByThemeAndStore(Long fid, Long sid) {
		storeInfoDao.deleteByThemeAndStore(fid, sid);
	}

	public List<FileStoreInfo> getThemeInfoByStore(Long tid, Long sid) {
		return storeInfoDao.getInfoByTheme(tid, sid);
	}

	public String jsonString(List<ThemeFile> themeFiles) {
		List<FileDTO> fileDtos = Lists.newArrayList();

		for (ThemeFile f : themeFiles) {
			FileDTO dto = new FileDTO();
			dto.setId(f.getId());
			dto.setName(f.getTitle());
			fileDtos.add(dto);
		}
		JsonMapper mapper = JsonMapper.buildNormalMapper();
		return mapper.toJson(fileDtos);

	}

	public boolean isFileTitleUnique(String newTitle, String oldTitle) {
		return themeFileDao.isPropertyUnique("title", newTitle, oldTitle);
	}

	public String adXml(List<ThemeFile> themes) {
		StringBuilder buffer = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		if (themes.size() > 5) {
			themes = themes.subList(0, 5);
		}
		buffer.append("<ads>");
		for (ThemeFile theme : themes) {
			buffer.append("<ad>");
			buffer.append("<fid>mopita/home!details.action?id=" + theme.getId() + "</fid>");
			buffer.append("<path>mopita/image.action?path=" + theme.getAdPath() + "</path>");
			buffer.append("</ad>");
		}
		buffer.append("</ads>");
		return buffer.toString();
	}

	@Autowired
	public void setFileMultipleDao(FileMultipleInfoDao fileMultipleDao) {
		this.fileMultipleDao = fileMultipleDao;
	}

	@Autowired
	public void setThemeFileDao(ThemeFileDao themeFileDao) {
		this.themeFileDao = themeFileDao;
	}

	@Autowired
	public void setStoreInfoDao(FileStoreInfoDao storeInfoDao) {
		this.storeInfoDao = storeInfoDao;
	}

}
