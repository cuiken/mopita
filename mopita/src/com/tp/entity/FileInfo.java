package com.tp.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "f_file_info")
public class FileInfo extends IdEntity {

	private String title;
	private String description;
	private Long price;
	private Long sortBy;
	private FileStore file;

	private Shelf shelf;

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
