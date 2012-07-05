package com.tp.service;

import java.net.URLEncoder;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tp.dao.FileStoreInfoDao;
import com.tp.dao.StoreDao;
import com.tp.entity.FileStoreInfo;
import com.tp.entity.Preview;
import com.tp.entity.Store;
import com.tp.utils.Constants;
import com.tp.utils.Digests;
import com.tp.utils.Encodes;

@Component
@Transactional
public class XmlService {

	private static final String FILE_DOWNLOAD = Constants.getDomain() + "/file-download.action?inputPath=";
	private static final String IMAGE_DOWNLOAD = Constants.getDomain() + "/image.action?path=";

	private static final int INTERATIONS = 1024;

	private StoreDao storeDao;
	private FileStoreInfoDao fileInfoDao;

	public String getXml(String storeValue, String language) throws Exception {
		if (storeValue == null || storeValue.isEmpty()) {
			return "";
		}
		if (language == null || language.isEmpty()) {
			language = "zh";
		}
		Store store = storeDao.findUniqueBy("value", storeValue);
		List<FileStoreInfo> fileInfos = fileInfoDao.getFileInfoByStoreAndLanguage(store.getId(), language);
		String themeContent = getFileInfo(fileInfos);
		StringBuilder buffer = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");

		buffer.append("<themes>");
		buffer.append("<storeName>" + store.getName() + "</storeName>");
		buffer.append("<storeValue>" + store.getValue() + "</storeValue>");
		buffer.append("<UUID>" + UUID(themeContent) + "</UUID>");
		buffer.append(themeContent);
		buffer.append("</themes>");
		return buffer.toString();
	}

	private String UUID(String themeContent) {

		byte[] hashPassword = Digests.sha1(themeContent.getBytes(), null, INTERATIONS);
		return Encodes.encodeHex(hashPassword);
	}

	private String getFileInfo(List<FileStoreInfo> fileInfos) throws Exception {
		StringBuilder buffer = new StringBuilder();
		String pres = "";
		String wux = "";
		String hux = "";
		for (FileStoreInfo info : fileInfos) {

			List<Preview> previews = info.getTheme().getPreviews();

			for (Preview p : previews) {
				pres += IMAGE_DOWNLOAD + URLEncoder.encode(p.getPath(), "UTF-8") + ",";
			}
			int lastComma = StringUtils.lastIndexOf(pres, ",");
			pres = StringUtils.substring(pres, 0, lastComma);
			if (info.getTheme().getUxWvga() != null) {
				wux = URLEncoder.encode(info.getTheme().getUxWvga(), "UTF-8");
			}
			if (info.getTheme().getUxHvga() != null) {
				hux = URLEncoder.encode(info.getTheme().getUxHvga(), "UTF-8");
			}
			buffer.append("<theme>");
			buffer.append("<id>" + info.getTheme().getId() + "</id>");
			buffer.append("<name>" + info.getTitle() + "</name>");
			buffer.append("<author>" + info.getAuthor() + "</author>");
			buffer.append("<shortDesc>" + Encodes.escapeXml(info.getShortDescription()) + "</shortDesc>");
			buffer.append("<longDesc>" + Encodes.escapeXml(info.getLongDescription()) + "</longDesc>");
			buffer.append("<ux_w>" + FILE_DOWNLOAD + wux + "</ux_w>");
			buffer.append("<ux_h>" + IMAGE_DOWNLOAD + hux + "</ux_h>");
			buffer.append("<preview>").append(pres).append("</preview>");

			buffer.append("<createTime>" + info.getTheme().getCreateTime() + "</createTime>");
			buffer.append("<modifyTime>" + info.getTheme().getModifyTime() + "</modifyTime>");
			buffer.append("</theme>");
		}
		return buffer.toString();
	}

	@Autowired
	public void setStoreDao(StoreDao storeDao) {
		this.storeDao = storeDao;
	}

	@Autowired
	public void setFileInfoDao(FileStoreInfoDao fileInfoDao) {
		this.fileInfoDao = fileInfoDao;
	}

}
