package com.tp.mapper.nav;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Button {
	
	public Button(){
		super();
	}
	
	@XmlAttribute
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@XmlAttribute
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@XmlAttribute(name = "class")
	public Integer getClazz() {
		return clazz;
	}

	public void setClazz(Integer clazz) {
		this.clazz = clazz;
	}

	@XmlAttribute
	public Boolean getFocus() {
		return focus;
	}

	public void setFocus(Boolean focus) {
		this.focus = focus;
	}

	@XmlAttribute
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@XmlAttribute
	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	@XmlAttribute
	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	@XmlAttribute
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@XmlAttribute
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Button(Integer id, String action, Integer clazz, Boolean focus,
			String position, String size, String tips, String title,
			String picture) {
		super();
		this.id = id;
		this.action = action;
		this.clazz = clazz;
		this.focus = focus;
		this.position = position;
		this.size = size;
		this.tips = tips;
		this.title = title;
		this.picture = picture;
	}

	private Integer id;
	private String action;
	private Integer clazz;
	private Boolean focus;
	private String position;
	private String size;
	private String tips;
	private String title;
	private String picture;
}
