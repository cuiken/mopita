package com.tp.entity.nav;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.google.common.collect.Lists;
import com.tp.entity.IdEntity;
import com.tp.utils.ConvertUtils;

@Entity
@Table(name = "nav_item")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Navigator extends IdEntity {

	private String name;
	private String value;
	private String navAddr;
	private Long uuid;
	private List<Board> boards = Lists.newArrayList();
	private List<Tag> tags = Lists.newArrayList();
	private List<NavigatorIcon> icons = Lists.newArrayList();

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

	public Long getUuid() {
		return uuid;
	}

	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}

	@ManyToMany
	@JoinTable(name = "nav_board_navigator", joinColumns = { @JoinColumn(name = "n_id") }, inverseJoinColumns = { @JoinColumn(name = "b_id") })
	@Fetch(FetchMode.SUBSELECT)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public List<Board> getBoards() {
		return boards;
	}

	public void setBoards(List<Board> boards) {
		this.boards = boards;
	}

	@ManyToMany
	@JoinTable(name = "nav_tag_navigator", joinColumns = { @JoinColumn(name = "n_id") }, inverseJoinColumns = { @JoinColumn(name = "t_id") })
	@Fetch(FetchMode.SUBSELECT)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	@OneToMany(mappedBy = "navigator", fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE }, orphanRemoval = true)
	public List<NavigatorIcon> getIcons() {
		return icons;
	}

	public void setIcons(List<NavigatorIcon> icons) {
		this.icons = icons;
	}

	@SuppressWarnings("unchecked")
	@Transient
	public List<Long> getCheckedTagIds() {
		return ConvertUtils.convertElementPropertyToList(tags, "id");
	}

	@SuppressWarnings("unchecked")
	@Transient
	public List<Long> getCheckedBoardIds() {
		return ConvertUtils.convertElementPropertyToList(boards, "id");
	}

	@Transient
	public String getTagNames() {
		return ConvertUtils.convertElementPropertyToString(tags, "name", ",");
	}

	@Transient
	public String getBoardNames() {
		return ConvertUtils.convertElementPropertyToString(boards, "name", ",");
	}

	@Override
	public String toString() {

		return ToStringBuilder.reflectionToString(this);
	}
}
