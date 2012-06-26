package com.tp.entity;

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
import com.tp.utils.ConvertUtils;

@Entity
@Table(name = "f_client")
public class ClientFile extends IdEntity {

	private String name;
	private String pkName;
	private String path;
	private Long size;
	private String version;
	private String createTime;
	private String modifyTime;

	private List<Market> markets = Lists.newArrayList();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPkName() {
		return pkName;
	}

	public void setPkName(String pkName) {
		this.pkName = pkName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "f_client_market", joinColumns = { @JoinColumn(name = "client_id") }, inverseJoinColumns = { @JoinColumn(name = "market_id") })
	public List<Market> getMarkets() {
		return markets;
	}

	public void setMarkets(List<Market> markets) {
		this.markets = markets;
	}

	@SuppressWarnings("unchecked")
	@Transient
	public List<Long> getCheckedMarketIds() {
		return ConvertUtils.convertElementPropertyToList(markets, "id");
	}

	@Override
	public String toString() {

		return ToStringBuilder.reflectionToString(this);
	}
}
