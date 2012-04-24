package com.tp.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "f_file")
public class FileStore extends IdEntity {

	private String name;
	private Long uxSize;
	private Long apkSize;
	private String uxPath;
	private String apkPath;
	private String availMachine;
	private String unavailMachine;
	private String marketURL;
	private String iconPath;
	private Long sortBy;
	private Date createTime;

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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {

		return ToStringBuilder.reflectionToString(this);
	}

}
