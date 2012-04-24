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
public class Store extends BaseCategory {

	private List<Shelf> shelfs = Lists.newArrayList();

	@OneToMany(mappedBy = "store", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY, orphanRemoval = true)
	public List<Shelf> getShelfs() {
		return shelfs;
	}

	public void setShelfs(List<Shelf> shelfs) {
		this.shelfs = shelfs;
	}

	@Transient
	public String getCategoryNames() {
		return ConvertUtils.convertElementPropertyToString(shelfs, "name", ",");
	}

	@Override
	public String toString() {

		return ToStringBuilder.reflectionToString(this);
	}
}
