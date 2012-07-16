package com.tp.orm;

import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class PageTest {

	private Page<Object> page;

	@Before
	public void setUp() {
		page = new Page<Object>();
	}

	@Test
	public void testSlider() {
		page.setTotalItems(1000);
		page.setPageSize(20);

		page.setPageNo(40);
		List<Integer> results = page.getSlider(10);
		assertEquals(11, results.size());
	}

}
