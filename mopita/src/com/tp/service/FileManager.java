package com.tp.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.tp.dao.FileMultipleInfoDao;
import com.tp.dao.PreviewDao;
import com.tp.dao.ThemeFileDao;
import com.tp.entity.FileMultipleInfo;
import com.tp.entity.Preview;
import com.tp.entity.ThemeFile;
import com.tp.orm.Page;
import com.tp.orm.PropertyFilter;
import com.tp.utils.FileUtils;

@Component
@Transactional
public class FileManager {

	private FileMultipleInfoDao fileMultipleDao;
	private PreviewDao previewDao;
	private ThemeFileDao themeFileDao;

	public FileMultipleInfo getFileInfo(Long id) {
		return fileMultipleDao.get(id);
	}

	public Preview getPreview(Long id) {
		return previewDao.get(id);
	}

	public List<ThemeFile> getAllThemeFile() {
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
	
	public Page<FileMultipleInfo> searchFileInfo(final Page<FileMultipleInfo> page,final List<PropertyFilter> filters){
		return fileMultipleDao.findPage(page, filters);
	}

	public ThemeFile saveFiles(List<File> files, ThemeFile fs,FileMultipleInfo info) {

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
		saveFileinfo(fs,info);
		savePreview(previews, fs);
		return fs;
	}

	private void saveFileinfo(ThemeFile f,FileMultipleInfo info){
		info.setTheme(f);
		saveFileInfo(info);
	}
	
	private void savePreview(List<File> previews, ThemeFile theme) {
		for (File pre : previews) {
			Preview preview = new Preview();
			preview.setPrePath(pre.getPath());
			preview.setTheme(theme);
			savePreview(preview);
		}
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

	public void savePreview(Preview p) {
		previewDao.save(p);
	}

	public void deleteFileInfo(Long id) {
		fileMultipleDao.delete(id);
	}

	public void deleteThemeFile(Long id) {
		themeFileDao.delete(id);
	}

	public List<ThemeFile> getRemainFiles(List<ThemeFile> allFiles,List<ThemeFile> fileOnShelf) {
		List<ThemeFile> remainFile = allFiles;
		for (ThemeFile fi : fileOnShelf) {

			remainFile.remove(fi);
		}
		return remainFile;
	}

	public boolean isFileNameUnique(String newValue,String oldValue){
		return themeFileDao.isPropertyUnique("name", newValue, oldValue);
	}
	
	@Autowired
	public void setFileMultipleDao(FileMultipleInfoDao fileMultipleDao) {
		this.fileMultipleDao = fileMultipleDao;
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
