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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.common.collect.Lists;
import com.tp.utils.ConvertUtils;

@Entity
@Table(name = "f_file_info")
public class FileInfo extends IdEntity {

	private String title;
	private String description;
	private Long price;

	private FileStore file;

	private List<Preview> previews = Lists.newArrayList();

	private Shelf shelf;
	private List<Category> categories = Lists.newArrayList();

	private String previewURL;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "market_url")
	public String getMarketURL() {
		return marketURL;
	}

	public void setMarketURL(String marketURL) {
		this.marketURL = marketURL;
	}

	public String getAvailable() {
		return available;
	}

	public void setAvailable(String available) {
		this.available = available;
	}

	public String getIconPath() {
		return iconPath;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}

	public Long getSortBy() {
		return sortBy;
	}

	public void setSortBy(Long sortBy) {
		this.sortBy = sortBy;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fs_id")
	public FileStore getFile() {
		return file;
	}

	public void setFile(FileStore file) {
		this.file = file;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "shelf_id")
	public Shelf getShelf() {
		return shelf;
	}

	public void setShelf(Shelf shelf) {
		this.shelf = shelf;
	}

	@OneToMany(mappedBy = "theme", fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE }, orphanRemoval = true)
	public List<Preview> getPreviews() {
		return previews;
	}

	public void setPreviews(List<Preview> previews) {
		this.previews = previews;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "f_category_file", joinColumns = { @JoinColumn(name = "file_id") }, inverseJoinColumns = { @JoinColumn(name = "category_id") })
	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	@SuppressWarnings("unchecked")
	@Transient
	public List<Long> getCategoryIds() {
		return ConvertUtils.convertElementPropertyToList(categories, "id");
	}

	@Transient
	public String getCategoryNames() {
		return ConvertUtils.convertElementPropertyToString(categories, "name",
				",");
	}

	@Transient
	public String getPreviewURL() {
		return previewURL;
	}

	public void setPreviewURL(String previewURL) {
		this.previewURL = previewURL;
	}

	@Override
	public String toString() {

		return ToStringBuilder.reflectionToString(this);
	}

}
