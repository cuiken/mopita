package com.tp.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 文件多语言描述
 * 
 * @author ken
 * 
 */
@Entity
@Table(name = "f_file_info")
public class FileMultipleInfo extends IdEntity {

	private String title;
	private String description;
	private String language;
	private Long price;

	private ThemeFile theme;

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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "f_id")
	public ThemeFile getTheme() {
		return theme;
	}

	public void setTheme(ThemeFile theme) {
		this.theme = theme;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj==null)
			return false;
		if(this.getClass()!=obj.getClass())
			return false;
		FileMultipleInfo that=(FileMultipleInfo)obj;
		return that.getLanguage().equals(this.getLanguage());
	}

	@Override
	public String toString() {

		return ToStringBuilder.reflectionToString(this);
	}
}
