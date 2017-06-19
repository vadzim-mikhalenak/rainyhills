package com.rainyhills.domain.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author Vadzim Mikhalenak.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class RainyHillsServiceTests {

	@Autowired
	private RainyHillsService rainyHillsService;

	@Test
	public void testNullInput() {
		Integer waterCount = rainyHillsService.calculateRemainingWater(null);
		Assert.assertNull(waterCount);
	}

	@Test
	public void testEmptyInput() {
		Integer waterCount = rainyHillsService.calculateRemainingWater();

		Assert.assertNotNull(waterCount);
		Assert.assertEquals(0, (int) waterCount);
	}

	@Test
	public void testNegativeInput() {
		Integer waterCount = rainyHillsService.calculateRemainingWater(1, 2, -1, -2);

		Assert.assertNull(waterCount);
	}

	@Test
	public void testData1() {
		Integer waterCount = rainyHillsService.calculateRemainingWater(3, 2, 4, 1, 2);

		Assert.assertNotNull(waterCount);
		Assert.assertEquals(2, (int) waterCount);
	}

	@Test
	public void testData2() {
		Integer waterCount = rainyHillsService.calculateRemainingWater(4, 1, 1, 0, 2, 3);

		Assert.assertNotNull(waterCount);
		Assert.assertEquals(8, (int) waterCount);
	}
}
