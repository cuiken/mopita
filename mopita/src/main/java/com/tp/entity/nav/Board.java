package com.tp.entity.nav;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.common.collect.Lists;

@Entity
@DiscriminatorValue("board")
public class Board extends TagItem {

	private List<NavTag> tags = Lists.newArrayList();
	private List<Navigator> navigators = Lists.newArrayList();

	@OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE }, orphanRemoval = true)
	public List<NavTag> getTags() {
		return tags;
	}

	public void setTags(List<NavTag> tags) {
		this.tags = tags;
	}

	@OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE }, orphanRemoval = true)
	public List<Navigator> getNavigators() {
		return navigators;
	}

	public void setNavigators(List<Navigator> navigators) {
		this.navigators = navigators;
	}

	@Override
	public String toString() {

		return ToStringBuilder.reflectionToString(this);
	}

}
