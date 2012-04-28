package com.tp.entity;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.common.collect.Lists;
import com.tp.utils.ConvertUtils;

@Entity
@DiscriminatorValue("shelf")
public class Shelf extends BaseCategory {

	private List<ThemeFile> themes = Lists.newArrayList();

	private Store store;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "store_id")
	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "f_file_shelf", joinColumns = { @JoinColumn(name = "s_id") }, inverseJoinColumns = { @JoinColumn(name = "f_id") })
	public List<ThemeFile> getThemes() {
		return themes;
	}

	public void setThemes(List<ThemeFile> themes) {
		this.themes = themes;
	}
	
	@Transient
	@SuppressWarnings("unchecked")
	public List<Long> getCheckedFileIds(){
		return ConvertUtils.convertElementPropertyToList(themes, "id");
	}

	@Transient
	public Long getCheckedId() {
		if (store == null)
			return 0L;
		else
			return store.getId();
	}

	@Override
	public String toString() {

		return ToStringBuilder.reflectionToString(this);
	}
}
