package com.tp.search;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.tp.spring.SpringTxTestCase;
import com.tp.utils.Constants;

@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class LogServiceTest extends SpringTxTestCase {

	private IndexSearcher searcher;
	private LogSearch logService;
	private String sdate = "";
	private String edate = "";

	@Test
	public void storeVisitTest() throws Exception {
		long count = logService.storeVisits(searcher, sdate, edate);
		assertEquals(23249, count);
	}

	@Before
	public void setUp() throws Exception {
		Directory dir = FSDirectory.open(new File(Constants.INDEX_STORAGE));
		IndexReader reader = IndexReader.open(dir);
		searcher = new IndexSearcher(reader);
		sdate = "2012-09-01";
		edate = "2012-09-02";
	}

	@Test
	public void contentVisitByADTest() throws Exception {
		String fid = "106";
		long count = logService.contentVisitByAD(searcher, fid, sdate, edate);
		assertEquals(492, count);
	}

	@Test
	public void contentdDwnloadByMarketTest() throws Exception {
		String filePackage = "com.tpad.change.unlock.content.mo2fa3shi1";
		long count = logService.contentDownFromMarket(searcher, filePackage, sdate, edate);
		assertEquals(107, count);
	}

	@Test
	public void contentDownloadBySelfTest() throws Exception {

		String fid = "53";

		long count = logService.contentDownFromStore(searcher, fid, sdate, edate);
		assertEquals(10, count);
	}

	@Test
	public void shareDownloadTest() throws Exception {

		long count = logService.downloadByShare(searcher, sdate, edate);
		assertEquals(4, count);
	}

	@Test
	public void downByContentTest() throws Exception {

		long count = logService.downloadByContent(searcher, sdate, edate);
		assertEquals(5240, count);
	}

	@Test
	public void contentDownPerMarket() throws Exception {
		String marketKey = "lemarketclient";
		String key1="leapp";
		String fpack = "com.tpad.change.unlock.content.ai4qing2niao3";
		long count = logService.contentPerMarketDown(searcher, sdate, edate, marketKey, fpack,key1);
		assertEquals(49, count);
	}

	@Autowired
	public void setLogService(LogSearch logService) {
		this.logService = logService;
	}
}
