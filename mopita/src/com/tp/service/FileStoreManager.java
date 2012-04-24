package com.tp.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.tp.dao.FileInfoDao;
import com.tp.dao.FileStoreDao;
import com.tp.dao.PreviewDao;
import com.tp.entity.FileInfo;
import com.tp.entity.FileStore;
import com.tp.entity.Preview;
import com.tp.orm.Page;
import com.tp.orm.PropertyFilter;
import com.tp.utils.FileUtils;

@Component
@Transactional
public class FileStoreManager {

	private FileInfoDao fileDao;
	private PreviewDao previewDao;
	private FileStoreDao fileStoreDao;

	public FileInfo getFile(Long id) {
		return fileDao.get(id);
	}

	public Preview getPreview(Long id) {
		return previewDao.get(id);
	}

	public Page<FileInfo> searchFile(final Page<FileInfo> page,
			final List<PropertyFilter> filters) {
		return fileDao.findPage(page, filters);
	}

	public Page<FileInfo> searchFile(final Page<FileInfo> page, Long categoryId) {

		return fileDao.searchFileByCategory(page, categoryId);
	}

	public void saveFiles(List<File> files, FileInfo info, FileStore fs) {

		List<File> previews = Lists.newArrayList();
		for (File file : files) {
			String fname = FileUtils.getFileName(file.getName());
			String extension = FileUtils.getExtension(file.getName());
			if (FileUtils.isPreview(fname)) {
				previews.add(file);
			} else if (FileUtils.isIcon(fname)) {
				info.setIconPath(file.getPath());
			} else if (FileUtils.isApk(extension)) {

				fs.setApkSize(FileUtils.getFileSize(file.getPath()));
				fs.setApkPath(file.getPath());
			} else if (FileUtils.isUx(extension)) {

				fs.setUxPath(file.getPath());
				fs.setUxSize(FileUtils.getFileSize(file.getPath()));
			}
		}
		saveFileStore(fs);
		info.setFile(fs);
		saveFile(info);
		savePreview(previews, info);
	}

	private void savePreview(List<File> previews, FileInfo theme) {
		for (File pre : previews) {
			Preview preview = new Preview();
			preview.setPrePath(pre.getPath());
			preview.setTheme(theme);
			savePreview(preview);
		}
	}

	public void saveFile(FileInfo file) {
		fileDao.save(file);
	}

	public void saveFileStore(FileStore entity) {
		fileStoreDao.save(entity);
	}

	public void savePreview(Preview p) {
		previewDao.save(p);
	}

	public void deleteFile(Long id) {
		fileDao.delete(id);
	}

	@Autowired
	public void setFileDao(FileInfoDao fileDao) {
		this.fileDao = fileDao;
	}

	@Autowired
	public void setPreviewDao(PreviewDao previewDao) {
		this.previewDao = previewDao;
	}

	@Autowired
	public void setFileStoreDao(FileStoreDao fileStoreDao) {
		this.fileStoreDao = fileStoreDao;
	}
}
