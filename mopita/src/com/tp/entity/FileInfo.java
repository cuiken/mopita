package com.tp.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "f_file_info")
public class FileInfo extends IdEntity {

	private String title;
	private String description;
	private String language;
	private Long price;
	private Long sortBy;

	private ThemeFile file;

	private Shelf shelf;

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

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
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
	@JoinColumn(name = "f_id")
	public ThemeFile getFile() {
		return file;
	}

	public void setFile(ThemeFile file) {
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

	@Override
	public String toString() {

		return ToStringBuilder.reflectionToString(this);
	}

}
