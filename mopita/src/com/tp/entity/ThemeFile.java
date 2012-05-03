package com.tp.entity;

import java.util.Date;
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

import com.google.common.collect.Lists;
import com.tp.utils.ConvertUtils;

/**
 * 文件基础关系实体
 * 
 * @author Administrator
 * 
 */
@Entity
@Table(name = "f_file")
public class ThemeFile extends IdEntity {

	private String name;

	private Long uxSize;
	private Long apkSize;
	private String uxPath;
	private String apkPath;
	private String availMachine;
	private String unavailMachine;
	private String marketURL;
	private String iconPath;
	private Date createTime;

	private List<Category> categories = Lists.newArrayList();
	private List<Preview> previews = Lists.newArrayList();
	private List<FileMultipleInfo> fileInfo = Lists.newArrayList();
	private List<Shelf> shelfs = Lists.newArrayList();

	private String previewURL;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Column(name = "market_url")
	public String getMarketURL() {
		return marketURL;
	}

	public void setMarketURL(String marketURL) {
		this.marketURL = marketURL;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@OneToMany(mappedBy = "theme", fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE }, orphanRemoval = true)
	public List<Preview> getPreviews() {
		return previews;
	}

	public void setPreviews(List<Preview> previews) {
		this.previews = previews;
	}

	@OneToMany(mappedBy = "theme", fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE }, orphanRemoval = true)
	public List<FileMultipleInfo> getFileInfo() {
		return fileInfo;
	}

	public void setFileInfo(List<FileMultipleInfo> fileInfo) {
		this.fileInfo = fileInfo;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "f_file_shelf", joinColumns = { @JoinColumn(name = "f_id") }, inverseJoinColumns = { @JoinColumn(name = "s_id") })
	public List<Shelf> getShelfs() {
		return shelfs;
	}

	public void setShelfs(List<Shelf> shelfs) {
		this.shelfs = shelfs;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "f_category_file", joinColumns = { @JoinColumn(name = "file_id") }, inverseJoinColumns = { @JoinColumn(name = "category_id") })
	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	@Transient
	public String getPreviewURL() {
		return previewURL;
	}

	public void setPreviewURL(String previewURL) {
		this.previewURL = previewURL;
	}

	@SuppressWarnings("unchecked")
	@Transient
	public List<Long> getCheckedCategoryIds() {
		return ConvertUtils.convertElementPropertyToList(categories, "id");
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj==null)
			return false;
		if(obj.getClass()!=this.getClass())
			return false;
		ThemeFile that=(ThemeFile)obj;
		return that.getId()==this.getId();
	}

	@Override
	public String toString() {

		return ToStringBuilder.reflectionToString(this);
	}

}
