package com.tp.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.tp.entity.FileType;

public class FileUtils {

	private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

	private static final String FILE_STORAGE = Constants.FILE_STORAGE;

	public static List<File> unZip(File srcFile) {
		try {
			ZipFile zipFile = new ZipFile(srcFile, "GBK", true);

			Enumeration<ZipArchiveEntry> files = zipFile.getEntries();
			String folderName = UUID.randomUUID().toString();
			File baseDir = new File(FILE_STORAGE + folderName);
			baseDir.mkdirs();
			List<ZipArchiveEntry> entries = Lists.newArrayList();
			while (files.hasMoreElements()) {
				ZipArchiveEntry file = files.nextElement();
				if (file.isDirectory()) {
					new File(baseDir, file.getName()).mkdirs();

				} else {
					entries.add(file);
				}
			}
			return outputFile(entries, zipFile, baseDir);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Collections.emptyList();
		}

	}

	public static String getExtension(String fname) {
		int index = StringUtils.lastIndexOf(fname, Constants.DOT_SEPARATOR);
		return StringUtils.substring(fname, index + 1);
	}

	public static String getFileName(String fullName) {
		int index = StringUtils.lastIndexOf(fullName, Constants.DOT_SEPARATOR);
		return StringUtils.substring(fullName, 0, index);
	}

	public static List<String> imgExtensions() {
		return Arrays.asList(Constants.IMG_EXTENSION);

	}

	public static long getFileSize(String child) {
		return new File(Constants.FILE_STORAGE, child).length();
	}

	public static boolean isPreClient(String fname) {

		return StringUtils.containsIgnoreCase(fname, FileType.PREVIEW_CLIENT.getValue());

	}

	public static boolean isAd(String fname) {
		return StringUtils.containsIgnoreCase(fname, FileType.AD.getValue());
	}

	public static boolean isPreWeb(String fname) {
		return StringUtils.containsIgnoreCase(fname, FileType.PREVIEW_WEB.getValue());
	}

	public static boolean isIcon(String fname) {
		return StringUtils.containsIgnoreCase(fname, FileType.ICON.getValue());
	}

	public static boolean isUx(String ext) {
		return StringUtils.equalsIgnoreCase(ext, FileType.UX.getValue());
	}

	public static boolean isApk(String ext) {
		return StringUtils.equalsIgnoreCase(ext, FileType.APK.getValue());
	}

	private static List<File> outputFile(List<ZipArchiveEntry> entries, ZipFile zipFile, File baseDir) throws Exception {
		List<File> files = Lists.newArrayList();
		for (ZipArchiveEntry entry : entries) {
			File file = new File(baseDir, entry.getName());

			String relativePath = StringUtils.substring(file.getPath(), FILE_STORAGE.length());
			files.add(new File(relativePath));
			BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
			InputStream inputStream = zipFile.getInputStream(entry);
			byte[] data = new byte[1024];
			int len = 0;
			while ((len = inputStream.read(data)) != -1) {
				outputStream.write(data, 0, len);
			}
			outputStream.close();
		}

		zipFile.close();
		return files;
	}
}
