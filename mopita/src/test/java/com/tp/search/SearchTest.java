package com.tp.search;

import java.io.File;
import java.util.List;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.tp.entity.LogInHome;
import com.tp.service.LogService;
import com.tp.spring.SpringTxTestCase;
import com.tp.utils.Constants;

@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class SearchTest extends SpringTxTestCase {
	private Search search;

	private LogService logService;
	private String sdate = "2012-09-01";
	private String edate = "2012-09-02";

//	@Test
//	public void createIndexTest() throws Exception {
//
//		List<LogInHome> logs = logService.queryLogInHomeByDate(sdate, edate);
//
//		long a = System.currentTimeMillis();
//		search.createIndex(logs, sdate, edate);
//		long e = System.currentTimeMillis();
//		System.out.println("lucene create index take : " + (e - a) + "ms");
//
//	}
	
	@Test
	public void cmccTest() throws Exception{
		Directory dir = FSDirectory.open(new File(Constants.INDEX_STORAGE));
		IndexReader reader = IndexReader.open(dir);
		IndexSearcher searcher = new IndexSearcher(reader);
		long count=search.countByParam(searcher, "2012-10-01 00:00:00", "2012-10-31 23:59:59", "移");
		System.out.println("=====中国移动===== : "+count);
	}

	@Test
	public void netTest() throws Exception{
		Directory dir = FSDirectory.open(new File(Constants.INDEX_STORAGE));
		IndexReader reader = IndexReader.open(dir);
		IndexSearcher searcher = new IndexSearcher(reader);
		long count=search.countByParam(searcher, "2012-10-01 00:00:00", "2012-10-31 23:59:59", "net:wifi");
		System.out.println("=====wifi===== :"+count);
	}
	
	@Autowired
	public void setSearch(Search search) {
		this.search = search;
	}

	@Autowired
	public void setLogService(LogService logService) {
		this.logService = logService;
	}
}
