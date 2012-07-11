package test.com.tp.unit.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;

import test.com.tp.spring.SpringTxTestCase;

import com.tp.service.LogService;

@ContextConfiguration(locations = { "/applicationContext.xml" })
public class LogServiceTest extends SpringTxTestCase {

	private LogService logService;

	@Test
	public void countDownFromMarket() {
		String param = "com.tpad.change.unlock.content.jian3kou4zi1";
		String fid = "57";
		String sdate = "2012-07-08";
		String edate = "2012-07-09";
		Long marketDown = logService.countContentMarketDown(param, sdate, edate);
		Long totalDown = logService.countContentTotalDown(fid, param, sdate, edate);
		assertEquals(Long.valueOf(180), marketDown);
		assertEquals(Long.valueOf(334), totalDown);
	}

	@Test
	@Rollback(value = false)
	public void reportContent() {
		String sdate = "2012-07-06";
		String edate = "2012-07-07";
		long start = System.currentTimeMillis();
		logService.createContentReport(sdate, edate);
		long end = System.currentTimeMillis();
		assertTrue(end - start < 30000);
	}

	@Autowired
	public void setLogService(LogService logService) {
		this.logService = logService;
	}
}
