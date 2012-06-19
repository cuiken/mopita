package com.tp.service;

import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tp.dao.CategoryInfoDao;
import com.tp.dao.FileStoreInfoDao;
import com.tp.dao.StoreDao;
import com.tp.entity.CategoryInfo;
import com.tp.entity.FileStoreInfo;
import com.tp.entity.Store;
import com.tp.utils.Encodes;

@Component
@Transactional
public class XmlService {

	private StoreDao storeDao;
	private FileStoreInfoDao fileInfoDao;
	private CategoryInfoDao categoryInfoDao;

	public String getXml(String stroreValue, String language, String domain) throws Exception {
		if (stroreValue == null || stroreValue.isEmpty()) {
			return "";
		}
		if (language == null || language.isEmpty()) {
			language = "zh";
		}
		Store store = storeDao.findUniqueBy("value", stroreValue);
		List<FileStoreInfo> fileInfos = fileInfoDao.getFileInfoByStoreAndLanguage(store.getId(), language);

		String imageURL = domain + "/image.action?path=";
		String fileURL = domain + "/file-download.action?inputPath=";

		StringBuilder buffer = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		buffer.append("<themes>");
		buffer.append("<storeName>" + store.getName() + "</storeName>");
		buffer.append("<storeValue>" + store.getValue() + "</storeValue>");
		for (FileStoreInfo info : fileInfos) {
			List<Long> checkedIds = info.getTheme().getCheckedCategoryIds();
			CategoryInfo cate = new CategoryInfo();
			if (!checkedIds.isEmpty()) {
				cate = categoryInfoDao.findByCategoryAndLanguage(checkedIds.get(0), language);

			}
			buffer.append("<theme>");
			buffer.append("<id>" + info.getTheme().getId() + "</id>");
			buffer.append("<name>" + info.getTitle() + "</name>");
			buffer.append("<author>" + info.getAuthor() + "</author>");
			buffer.append("<shortDesc>" + Encodes.escapeXml(info.getShortDescription()) + "</shortDesc>");
			buffer.append("<longDesc>" + Encodes.escapeXml(info.getLongDescription()) + "</longDesc>");
			buffer.append("<price>" + info.getPrice() + "</price>");
			buffer.append("<category>" + cate.getName() + "</category>");
			buffer.append("<icon>" + imageURL + URLEncoder.encode(info.getTheme().getIconPath(), "UTF-8") + "</icon>");
			buffer.append("<webPreview>" + imageURL + URLEncoder.encode(info.getTheme().getPreWebPath(), "UTF-8")
					+ "</webPreview>");
			buffer.append("<clientPreview>" + imageURL + URLEncoder.encode(info.getTheme().getPreClientPath(), "UTF-8")
					+ "</clientPreview>");
			buffer.append("<uxURL>" + fileURL + URLEncoder.encode(info.getTheme().getUxPath(), "UTF-8") + "</uxURL>");
			buffer.append("<uxSize>" + info.getTheme().getUxSize() + "</uxSize>");
			buffer.append("<apkURL>" + fileURL + URLEncoder.encode(info.getTheme().getApkPath(), "UTF-8") + "</apkURL>");
			buffer.append("<apkSize>" + info.getTheme().getApkSize() + "</apkSize>");
			buffer.append("<createTime>" + info.getTheme().getCreateTime() + "</createTime>");
			buffer.append("<modifyTime>"+info.getTheme().getModifyTime()+"</modifyTime>");
			buffer.append("</theme>");
		}
		buffer.append("</themes>");
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

	@Autowired
	public void setCategoryInfoDao(CategoryInfoDao categoryInfoDao) {
		this.categoryInfoDao = categoryInfoDao;
	}
}
