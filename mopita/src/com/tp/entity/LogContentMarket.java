package com.tp.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "log_cc_market")
public class LogContentMarket extends IdEntity {

	private String marketName;
	private Long totalDown;
	private LogCountContent logContent;

	public String getMarketName() {
		return marketName;
	}

	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}

	public Long getTotalDown() {
		return totalDown;
	}

	public void setTotalDown(Long totalDown) {
		this.totalDown = totalDown;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "log_c_id")
	public LogCountContent getLogContent() {
		return logContent;
	}

	public void setLogContent(LogCountContent logContent) {
		this.logContent = logContent;
	}

	@Override
	public String toString() {

		return ToStringBuilder.reflectionToString(this);
	}
}
