package com.tp.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.common.collect.Lists;
import com.tp.utils.ConvertUtils;

@Entity
@DiscriminatorValue("Store")
public class Store extends CateItem {

	private List<FileStoreInfo> fileStoreInfo = Lists.newArrayList();
	private List<Shelf> shelfs = Lists.newArrayList();

	private List<ThemeFile> themes = Lists.newArrayList();

	@OneToMany(mappedBy = "store", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY, orphanRemoval = true)
	public List<Shelf> getShelfs() {
		return shelfs;
	}

	public void setShelfs(List<Shelf> shelfs) {
		this.shelfs = shelfs;
	}

	@OneToMany(mappedBy = "store", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY, orphanRemoval = true)
	public List<FileStoreInfo> getFileStoreInfo() {
		return fileStoreInfo;
	}

	public void setFileStoreInfo(List<FileStoreInfo> fileStoreInfo) {
		this.fileStoreInfo = fileStoreInfo;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "f_file_store", joinColumns = { @JoinColumn(name = "s_id") }, inverseJoinColumns = { @JoinColumn(name = "f_id") })
	public List<ThemeFile> getThemes() {
		return themes;
	}
	
	public void setThemes(List<ThemeFile> themes) {
		this.themes = themes;
	}

	@Transient
	public String getCategoryNames() {
		return ConvertUtils.convertElementPropertyToString(shelfs, "name", ",");
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		Store that = (Store) obj;
		return that.getName().equals(this.getName());
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
