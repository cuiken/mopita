package com.tp.job;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.tp.service.LogService;
import com.tp.utils.DateFormatUtils;

public class ReportCreateJob {

	private LogService logService;

	public void createReport() {
		String currDate = DateFormatUtils.convertReportDate(new Date());
		String perDate = DateFormatUtils.getPerDate(currDate);
		logService.createClientReport(perDate, currDate);
	}

	@Autowired
	public void setLogService(LogService logService) {
		this.logService = logService;
	}
}
