package com.tp.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.tp.entity.ClientFile;
import com.tp.spring.SpringTxTestCase;

@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class ClientManagerTest extends SpringTxTestCase {

	private ClientFileManager clientManager;

	/**
	 * 目前该测试仅仅正对个位数的版本比较，由于sql max(2.10.4)<max(2.5.0)
	 */
	@Test
	@Rollback
	@Transactional
	public void getNestVersion() {
		ClientFile client = new ClientFile();
		client.setName("test-client-st");
		client.setVersion("2.2.5");
		client.setDtype("st");
		clientManager.save(client);

		String maxVersion = clientManager.getNewestVersionCode(client.getDtype());
		String cv = "";
		String result = clientManager.compareVersion(cv, maxVersion);
		assertEquals("", result);
		cv = "1.1.0";
		result = clientManager.compareVersion(cv, maxVersion);
//		assertEquals(client.getVersion(), result);
		cv = "2.2.5";
		result = clientManager.compareVersion(cv, maxVersion);
		assertEquals("", result);
		cv = "2.2.6";
		result = clientManager.compareVersion(cv, maxVersion);
		assertEquals("", result);

		ClientFile c1 = new ClientFile();
		c1.setName("test-client-dm");
		c1.setVersion("2.2.5dm");
		c1.setDtype("dm");
		clientManager.save(c1);

		String c1max = clientManager.getNewestVersionCode(c1.getDtype());
		String cv1 = "1.1.0dm";
		String r1 = clientManager.compareVersion(cv1, c1max);
		assertEquals(c1.getVersion(), r1);

	}

	@Autowired
	public void setClientManager(ClientFileManager clientManager) {
		this.clientManager = clientManager;
	}
}
