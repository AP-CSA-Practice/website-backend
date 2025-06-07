package com.apcsa.practice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ApCsaPracticeApplicationTests {

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	void contextLoads() {
		// 測試應用程序上下文是否成功加載
		assertNotNull(applicationContext, "Application context should not be null");
	}

	@Test
	void simpleStringTest() {
		// 一個簡單的字符串比較測試
		String expected = "Hello, AP CSA Practice!";
		String actual = "Hello, AP CSA Practice!";
		
		assertEquals(expected, actual, "Strings should be equal");
		
		// 測試字符串包含關係
		String message = "Welcome to AP Computer Science A Practice";
		assertTrue(message.contains("AP Computer Science"), "Message should contain 'AP Computer Science'");
		
		// 測試字符串長度
		assertTrue(message.length() > 10, "Message length should be greater than 10");
}

	@Test
	void simpleCalculationTest() {
		// 簡單的數學計算測試
		int result = 5 + 3;
		assertEquals(8, result, "5 + 3 should equal 8");

		// 測試除法
		double divisionResult = 10.0 / 2.0;
		assertEquals(5.0, divisionResult, 0.001, "10.0 / 2.0 should equal 5.0");
		
		// 測試餘數
		int remainder = 10 % 3;
		assertEquals(1, remainder, "10 % 3 should equal 1");
	}
}