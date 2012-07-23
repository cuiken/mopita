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

	@Test
	@Rollback
	@Transactional
	public void stNewestVersion() {
		ClientFile client = new ClientFile();
		client.setName("test-client-st");
		client.setVersion("2.10.5");
		client.setDtype("st");
		clientManager.save(client);

		String maxVersion = clientManager.getNewestVersionCode(client.getDtype());
		String dtype = client.getDtype();

		String cv = "";
		String returnVersion = clientManager.compareVersion(cv, maxVersion, dtype);
		assertEquals("", returnVersion);

		cv = "1.10.5";
		returnVersion = clientManager.compareVersion(cv, maxVersion, dtype);
		assertEquals(client.getVersion(), returnVersion);

		cv = "1.11.5";
		returnVersion = clientManager.compareVersion(cv, maxVersion, dtype);
		assertEquals(client.getVersion(), returnVersion);

		cv = "2.9.5";
		returnVersion = clientManager.compareVersion(cv, maxVersion, dtype);
		assertEquals(client.getVersion(), returnVersion);

		cv = "2.10.1";
		returnVersion = clientManager.compareVersion(cv, maxVersion, dtype);
		assertEquals(client.getVersion(), returnVersion);

		cv = "2.11.5";
		returnVersion = clientManager.compareVersion(cv, maxVersion, dtype);
		assertEquals("", returnVersion);

		cv = "2.10.5";
		returnVersion = clientManager.compareVersion(cv, maxVersion, dtype);
		assertEquals("", returnVersion);

		cv = "2.10.7";
		returnVersion = clientManager.compareVersion(cv, maxVersion, dtype);
		assertEquals("", returnVersion);

		cv = "3.10.7";
		returnVersion = clientManager.compareVersion(cv, maxVersion, dtype);
		assertEquals("", returnVersion);
		
		cv = "3.11.7";
		returnVersion = clientManager.compareVersion(cv, maxVersion, dtype);
		assertEquals("", returnVersion);
		
		cv = "3.1.7";
		returnVersion = clientManager.compareVersion(cv, maxVersion, dtype);
		assertEquals("", returnVersion);
	}

	@Test
	@Rollback
	@Transactional
	public void jpNewestVersion() {
		ClientFile client = new ClientFile();
		client.setName("test-client-jp");
		client.setVersion("2.10.5g");
		client.setDtype("jp");
		clientManager.save(client);

		String maxVersion = clientManager.getNewestVersionCode(client.getDtype());
		String dtype = client.getDtype();

		String cv = "";
		String returnVersion = clientManager.compareVersion(cv, maxVersion, dtype);
		assertEquals("", returnVersion);

		cv = "1.10.5g";
		returnVersion = clientManager.compareVersion(cv, maxVersion, dtype);
		assertEquals(client.getVersion(), returnVersion);

		cv = "2.9.5g";
		returnVersion = clientManager.compareVersion(cv, maxVersion, dtype);
		assertEquals(client.getVersion(), returnVersion);

		cv = "2.10.1g";
		returnVersion = clientManager.compareVersion(cv, maxVersion, dtype);
		assertEquals(client.getVersion(), returnVersion);

		cv = "2.10.7g";
		returnVersion = clientManager.compareVersion(cv, maxVersion, dtype);
		assertEquals("", returnVersion);
	}

	@Autowired
	public void setClientManager(ClientFileManager clientManager) {
		this.clientManager = clientManager;
	}
}
