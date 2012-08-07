package com.tp.entity.nav;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.common.collect.Lists;
import com.tp.entity.IdEntity;
import com.tp.utils.ConvertUtils;

@Entity
@Table(name = "nav_item")
public class Navigator extends IdEntity {

	private String name;
	private String value;
	private String navAddr;
	private String picAddr;
	private List<Board> boards = Lists.newArrayList();
	private List<NavTag> tags = Lists.newArrayList();

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

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "nav_board_navigator", joinColumns = { @JoinColumn(name = "n_id") }, inverseJoinColumns = { @JoinColumn(name = "b_id") })
	public List<Board> getBoards() {
		return boards;
	}

	public void setBoards(List<Board> boards) {
		this.boards = boards;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "nav_tag_navigator", joinColumns = { @JoinColumn(name = "n_id") }, inverseJoinColumns = { @JoinColumn(name = "t_id") })
	public List<NavTag> getTags() {
		return tags;
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

	public void setTags(List<NavTag> tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {

		return ToStringBuilder.reflectionToString(this);
	}
}
