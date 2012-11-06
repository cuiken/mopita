package com.tp.job;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tp.entity.LogInHome;
import com.tp.search.Search;
import com.tp.service.LogService;
import com.tp.utils.Constants;
import com.tp.utils.DateFormatUtils;

public class ReportCreateJob {

	private LogService logService;
	private Search search;

	public void createReport() throws Exception {
		String currDate = DateFormatUtils.convertReportDate(new Date());
		String perDate = DateFormatUtils.getPerDate(currDate);

		List<LogInHome> logs = logService.queryLogInHomeByDate(perDate, currDate);
		search.createIndex(logs, perDate, currDate);

		Directory dir = FSDirectory.open(new File(Constants.INDEX_STORAGE));
		IndexReader reader = IndexReader.open(dir);
		IndexSearcher searcher = new IndexSearcher(reader);

		logService.createClientReport(searcher, perDate, currDate);
		logService.createContentReport(searcher, perDate, currDate);
	}

	@Autowired
	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	@Autowired
	public void setSearch(Search search) {
		this.search = search;
	}
}
