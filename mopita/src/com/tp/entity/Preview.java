package com.tp.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "f_preview")
public class Preview extends IdEntity {

	private String name;
	private String prePath;
	private ThemeFile theme;

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPrePath() {
		return prePath;
	}

	public void setPrePath(String prePath) {
		this.prePath = prePath;
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
	public String toString() {

		return ToStringBuilder.reflectionToString(this);
	}

}
