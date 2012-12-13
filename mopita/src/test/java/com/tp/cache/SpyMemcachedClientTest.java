package com.tp.cache;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.google.common.collect.Lists;
import com.tp.entity.LogForContent;
import com.tp.entity.LogInHome;
import com.tp.service.LogService;
import com.tp.spring.SpringContextTestCase;
import com.tp.utils.Threads;

@Category(UnStable.class)
@ContextConfiguration(locations = { "/applicationContext-memcached.xml", "/applicationContext-test.xml" })
public class SpyMemcachedClientTest extends SpringContextTestCase {
	
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SpyMemcachedClient client;

	@Autowired
	private LogService logService;

	@Test
	public void db() {
		List<LogInHome> lists = logService.queryLogInHomeByDate("2012-07-21", "2012-07-22");
		logger.info("size=>"+ lists.size());

		String prefix = "log:";
		String count_key = "counter";
		List<LogForContent> homes = Lists.newArrayList();

		for (LogInHome log : lists) {
			if (log.getRequestParams() == null || log.getRequestParams().isEmpty())
				continue;
			long cont = client.incr(count_key, 1, 1);
			client.set(prefix + cont, 60 * 60 * 4, log.getRequestParams());

			if (client.get(count_key).equals("1000")) {
				logger.info("1000=>"+client.get(prefix+1000));
				for (int j = 1; j <= 1000; j++) {
					String value = client.get(prefix + j);
					LogForContent content = new LogForContent();
					content.setFromMarket(value);
					homes.add(content);

				}
				logService.saveLogContents(homes);
				client.decr(count_key, 1000, 1);
				homes.clear();
			}
		}
	}

	@Test
	public void normal() {

		String key = "consumer:1";
		String value = "admin";

		String key2 = "consumer:2";
		String value2 = "user";

		//get/set
		client.set(key, 60 * 60 * 1, value);
		Threads.sleep(1000);
		String result = client.get(key);
		assertEquals(value, result);

		//safeSet
		client.safeSet(key2, 60 * 60 * 1, value2);
		result = client.get(key2);
		assertEquals(value2, result);

		//bulk
		Map<String, Object> bulkResult = client.getBulk(Lists.newArrayList(key, key2));
		assertEquals(2, bulkResult.size());
		assertEquals(value, bulkResult.get(key));

		//delete
		client.delete(key);
		Threads.sleep(1000);
		result = client.get(key);
		assertNull(result);

		client.safeDelete(key);
		result = client.get(key);
		assertNull(result);
	}

	@Test
	public void incr() {
		String key = "counter";

		assertEquals(1, client.incr(key, 1, 1));
		//注意counter的实际类型是String
		assertEquals("1", client.get(key));

		assertEquals(2, client.incr(key, 1, 1));
		assertEquals("2", client.get(key));

		assertEquals(0, client.decr(key, 2, 1));
		assertEquals("0", client.get(key));

	}
}
