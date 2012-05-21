package com.tp.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.tp.dao.FileInfoDao;
import com.tp.dao.FileStoreInfoDao;
import com.tp.dao.unlock.FileItemDao;
import com.tp.dao.unlock.UnlockFileDao;
import com.tp.dto.FileDTO;
import com.tp.entity.FileInfo;
import com.tp.entity.FileStoreInfo;
import com.tp.entity.Shelf;
import com.tp.entity.ThemeFile;
import com.tp.entity.unlock.FileItem;
import com.tp.entity.unlock.UnlockFile;
import com.tp.entity.unlock.FileType.Ad;
import com.tp.entity.unlock.FileType.Apk;
import com.tp.entity.unlock.FileType.Icon;
import com.tp.entity.unlock.FileType.PrevieWeb;
import com.tp.entity.unlock.FileType.PreviewClient;
import com.tp.entity.unlock.FileType.Ux;
import com.tp.mapper.JsonMapper;
import com.tp.orm.Page;
import com.tp.orm.PropertyFilter;
import com.tp.utils.FileUtils;

@Component
@Transactional
public class UnlockFileManager {
	private FileInfoDao fileInfoDao;
	private FileStoreInfoDao storeInfoDao;
	private UnlockFileDao unlockFileDao;
	private FileItemDao fileItemDao;

	public FileInfo getFileInfo(Long id) {
		return fileInfoDao.get(id);
	}

	public List<UnlockFile> getAllThemeFile() {
		return unlockFileDao.getAll();
	}

	public Page<UnlockFile> searchFileByShelf(final Page<UnlockFile> page, Shelf.Type stype, Long sid) {
		return unlockFileDao.searchFileByShelf(page, stype.getValue(), sid);
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

	public Page<UnlockFile> searchThemeFile(final Page<UnlockFile> page, final List<PropertyFilter> filters) {
		return unlockFileDao.findPage(page, filters);
	}

	public Page<UnlockFile> searchThemeFile(final Page<UnlockFile> page, Long categoryId) {
		return unlockFileDao.searchFileByCategory(page, categoryId);
	}

	public Page<FileInfo> searchFileInfo(final Page<FileInfo> page, final List<PropertyFilter> filters) {
		return fileInfoDao.findPage(page, filters);
	}

	public Page<FileStoreInfo> searchStoreInfoInShelf(final Page<FileStoreInfo> page, Shelf.Type newest, Long sid,
			String language) {
		return storeInfoDao.searchStoreInfoInShelf(page, newest.getValue(), sid, language);
	}

	public UnlockFile saveFiles(List<File> files, UnlockFile fs, FileInfo info) {
		saveThemeFile(fs);
		for (File file : files) {
			String fname = FileUtils.getFileName(file.getName());
			String extension = FileUtils.getExtension(file.getName());
			if (FileUtils.isPreClient(fname)) {
				saveItem(new PreviewClient(), file,fs);
			} else if (FileUtils.isPreWeb(fname)) {
				saveItem(new PrevieWeb(), file,fs);
			} else if (FileUtils.isAd(fname)) {
				saveItem(new Ad(), file,fs);
			} else if (FileUtils.isIcon(fname)) {
				saveItem(new Icon(), file,fs);
			} else if (FileUtils.isApk(extension)) {
				saveItem(new Apk(), file,fs);
			} else if (FileUtils.isUx(extension)) {
				saveItem(new Ux(), file,fs);
			}
		}

		saveFileinfo(fs, info);
		return fs;
	}

	private void saveItem(FileItem item, File file,UnlockFile fs) {
		item.setName(file.getName());
		item.setPath(file.getPath());
		item.setSize(FileUtils.getFileSize(file.getPath()));
		item.setUnlockFile(fs);
		fileItemDao.save(item);
	}

	private void saveFileinfo(UnlockFile file, FileInfo info) {
		info.setUnlockFile(file);
		saveFileInfo(info);
	}

	public void saveFileInfo(FileInfo file) {
		fileInfoDao.save(file);
	}

	public UnlockFile getThemeFile(Long id) {
		return unlockFileDao.get(id);
	}

	public void saveThemeFile(UnlockFile entity) {
		unlockFileDao.save(entity);
	}

	public void deleteFileInfo(Long id) {
		fileInfoDao.delete(id);
	}

	public void deleteThemeFile(Long id) {
		unlockFileDao.delete(id);
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
		return unlockFileDao.isPropertyUnique("title", newTitle, oldTitle);
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
	public void setFileInfoDao(FileInfoDao fileInfoDao) {
		this.fileInfoDao = fileInfoDao;
	}

	@Autowired
	public void setUnlockFileDao(UnlockFileDao unlockFileDao) {
		this.unlockFileDao = unlockFileDao;
	}

	@Autowired
	public void setStoreInfoDao(FileStoreInfoDao storeInfoDao) {
		this.storeInfoDao = storeInfoDao;
	}

	@Autowired
	public void setFileItemDao(FileItemDao fileItemDao) {
		this.fileItemDao = fileItemDao;
	}
}
