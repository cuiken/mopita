package com.tp.entity.nav;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.tp.entity.IdEntity;

@Entity
@Table(name = "nav_item")
public class Navigator extends IdEntity {

	private String name;
	private String value;
	private String navAddr;
	private String picAddr;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getNavAddr() {
		return navAddr;
	}

	public void setNavAddr(String navAddr) {
		this.navAddr = navAddr;
	}

	public String getPicAddr() {
		return picAddr;
	}

	public void setPicAddr(String picAddr) {
		this.picAddr = picAddr;
	}

	@Override
	public String toString() {

		return ToStringBuilder.reflectionToString(this);
	}
}
