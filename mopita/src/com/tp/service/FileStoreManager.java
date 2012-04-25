package com.tp.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.tp.dao.FileInfoDao;
import com.tp.dao.PreviewDao;
import com.tp.dao.ThemeFileDao;
import com.tp.entity.FileInfo;
import com.tp.entity.Preview;
import com.tp.entity.ThemeFile;
import com.tp.orm.Page;
import com.tp.orm.PropertyFilter;
import com.tp.utils.FileUtils;

@Component
@Transactional
public class FileStoreManager {

	private FileInfoDao fileInfoDao;
	private PreviewDao previewDao;
	private ThemeFileDao themeFileDao;

	public FileInfo getFileInfo(Long id) {
		return fileInfoDao.get(id);
	}

	public Preview getPreview(Long id) {
		return previewDao.get(id);
	}
	
	public List<ThemeFile> getAllThemeFile(){
		return themeFileDao.getAll();
	}

	public Page<ThemeFile> searchThemeFile(final Page<ThemeFile> page,
			final List<PropertyFilter> filters) {
		return themeFileDao.findPage(page, filters);
	}

	public Page<ThemeFile> searchThemeFile(final Page<ThemeFile> page,
			Long categoryId) {
		return themeFileDao.searchFileByCategory(page, categoryId);
	}

	public void saveFiles(List<File> files, ThemeFile fs) {

		List<File> previews = Lists.newArrayList();
		for (File file : files) {
			String fname = FileUtils.getFileName(file.getName());
			String extension = FileUtils.getExtension(file.getName());
			if (FileUtils.isPreview(fname)) {
				previews.add(file);
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
		savePreview(previews, fs);
	}

	private void savePreview(List<File> previews, ThemeFile theme) {
		for (File pre : previews) {
			Preview preview = new Preview();
			preview.setPrePath(pre.getPath());
			preview.setTheme(theme);
			savePreview(preview);
		}
	}

	public void saveFileInfo(FileInfo file) {
		fileInfoDao.save(file);
	}

	public ThemeFile getThemeFile(Long id) {
		return themeFileDao.get(id);
	}

	public void saveThemeFile(ThemeFile entity) {
		themeFileDao.save(entity);
	}

	public void savePreview(Preview p) {
		previewDao.save(p);
	}

	public void deleteFileInfo(Long id) {
		fileInfoDao.delete(id);
	}
	
	public void deleteThemeFile(Long id){
		themeFileDao.delete(id);
	}
	
	public List<ThemeFile> getRemainFiles(List<ThemeFile> allFiles,List<FileInfo> fileOnShelf){
		List<ThemeFile> remainFile=allFiles;
		for(FileInfo fi:fileOnShelf){
			ThemeFile theme=fi.getFile();
			remainFile.remove(theme);
		}
		return remainFile;
	}

	@Autowired
	public void setFileInfoDao(FileInfoDao fileInfoDao) {
		this.fileInfoDao = fileInfoDao;
	}

	@Autowired
	public void setPreviewDao(PreviewDao previewDao) {
		this.previewDao = previewDao;
	}

	@Autowired
	public void setThemeFileDao(ThemeFileDao themeFileDao) {
		this.themeFileDao = themeFileDao;
	}

}
