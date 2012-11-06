package com.tp.entity.nav;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.google.common.collect.Lists;

@Entity
@DiscriminatorValue("tag")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Tag extends TagItem {

	private Board board;
	private List<Navigator> navigators = Lists.newArrayList();
	private List<TagIcon> icons = Lists.newArrayList();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "nav_tag_navigator", joinColumns = { @JoinColumn(name = "t_id") }, inverseJoinColumns = { @JoinColumn(name = "n_id") })
	@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
	public List<Navigator> getNavigators() {
		return navigators;
	}

	public void setNavigators(List<Navigator> navigators) {
		this.navigators = navigators;
	}

	@OneToMany(mappedBy = "tag", fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE }, orphanRemoval = true)
	public List<TagIcon> getIcons() {
		return icons;
	}

	public void setIcons(List<TagIcon> icons) {
		this.icons = icons;
	}

	@Override
	public String toString() {

		return ToStringBuilder.reflectionToString(this);
	}
}
