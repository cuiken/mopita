package com.tp.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.google.common.collect.Lists;
import com.tp.utils.ConvertUtils;

/**
 * 文件基础关系实体
 * 
 * @author ken.cui
 * 
 */
@Entity
@Table(name = "f_file")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ThemeFile extends IdEntity {

	private String name;
	private String title;
	private Long uxSize;
	private Long apkSize;
	private String uxPath;
	private String apkPath;
	private String availMachine;
	private String unavailMachine;
	private String marketURL;
	private String version;
	private String iconPath;
	private String adPath;
	private String preWebPath;
	private String preClientPath;
	private String createTime;
	private String modifyTime;

	private String downloadURL;

	private List<Category> categories = Lists.newArrayList();
	private List<FileInfo> fileInfo = Lists.newArrayList();
	private List<FileStoreInfo> infoStore = Lists.newArrayList();
	private List<ShelfFileLink> shelfFiles = Lists.newArrayList();
	private List<FileMarketValue> marketValues = Lists.newArrayList();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getUxSize() {
		return uxSize;
	}

	public void setUxSize(Long uxSize) {
		this.uxSize = uxSize;
	}

	public Long getApkSize() {
		return apkSize;
	}

	public void setApkSize(Long apkSize) {
		this.apkSize = apkSize;
	}

	public String getUxPath() {
		return uxPath;
	}

	public void setUxPath(String uxPath) {
		this.uxPath = uxPath;
	}

	public String getApkPath() {
		return apkPath;
	}

	public void setApkPath(String apkPath) {
		this.apkPath = apkPath;
	}

	public String getAvailMachine() {
		return availMachine;
	}

	public void setAvailMachine(String availMachine) {
		this.availMachine = availMachine;
	}

	public String getUnavailMachine() {
		return unavailMachine;
	}

	public void setUnavailMachine(String unavailMachine) {
		this.unavailMachine = unavailMachine;
	}

	public String getIconPath() {
		return iconPath;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}

	public String getAdPath() {
		return adPath;
	}

	public void setAdPath(String adPath) {
		this.adPath = adPath;
	}

	public String getPreWebPath() {
		return preWebPath;
	}

	public void setPreWebPath(String preWebPath) {
		this.preWebPath = preWebPath;
	}

	public String getPreClientPath() {
		return preClientPath;
	}

	public void setPreClientPath(String preClientPath) {
		this.preClientPath = preClientPath;
	}

	@Column(name = "market_url")
	public String getMarketURL() {
		return marketURL;
	}

	public void setMarketURL(String marketURL) {
		this.marketURL = marketURL;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Transient
	public String getDownloadURL() {
		return downloadURL;
	}

	public void setDownloadURL(String downloadURL) {
		this.downloadURL = downloadURL;
	}

	@OneToMany(mappedBy = "theme", fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE }, orphanRemoval = true)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public List<FileInfo> getFileInfo() {
		return fileInfo;
	}

	public void setFileInfo(List<FileInfo> fileInfo) {
		this.fileInfo = fileInfo;
	}

	@OneToMany(mappedBy = "theme", fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE }, orphanRemoval = true)
	public List<FileStoreInfo> getInfoStore() {
		return infoStore;
	}

	public void setInfoStore(List<FileStoreInfo> infoStore) {
		this.infoStore = infoStore;
	}

	@OneToMany(mappedBy = "theme", fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE }, orphanRemoval = true)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public List<ShelfFileLink> getShelfFiles() {
		return shelfFiles;
	}

	public void setShelfFiles(List<ShelfFileLink> shelfFiles) {
		this.shelfFiles = shelfFiles;
	}

	@OneToMany(mappedBy = "theme", fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE }, orphanRemoval = true)
	public List<FileMarketValue> getMarketValues() {
		return marketValues;
	}

	public void setMarketValues(List<FileMarketValue> marketValues) {
		this.marketValues = marketValues;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "f_category_file", joinColumns = { @JoinColumn(name = "file_id") }, inverseJoinColumns = { @JoinColumn(name = "category_id") })
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	@SuppressWarnings("unchecked")
	@Transient
	public List<Long> getCheckedCategoryIds() {
		return ConvertUtils.convertElementPropertyToList(categories, "id");
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;

		ThemeFile that = (ThemeFile) obj;
		return that.getId().equals(this.getId());
	}

	@Override
	public int hashCode() {
		assert false : "hashCode not designed";
		return 42; // any arbitrary constant will do
	}

	@Override
	public String toString() {

		return ToStringBuilder.reflectionToString(this);
	}

}
