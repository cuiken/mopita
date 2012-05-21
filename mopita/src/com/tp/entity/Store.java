package com.tp.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.common.collect.Lists;
import com.tp.utils.ConvertUtils;

@Entity
@DiscriminatorValue("Store")
public class Store extends CateItem {

	private List<FileStoreInfo> fileStoreInfo = Lists.newArrayList();;

	private List<Shelf> shelfs = Lists.newArrayList();

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

	@Transient
	public String getCategoryNames() {
		return ConvertUtils.convertElementPropertyToString(shelfs, "name", ",");
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj==null)
			return false;
		Store that=(Store)obj;
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
