package com.jpmorganchase.supersimplestockmarket.enums;

import static org.junit.Assert.*;

import org.junit.Test;

public class StockTypeTest {
	
	@Test
	public void testExpectedValues() {
		
		StockType[] stockTypes = StockType.values();
		
		assertEquals(2, stockTypes.length);
		assertEquals(StockType.COMMON, stockTypes[0]);
		assertEquals(StockType.PREFERRED, stockTypes[1]);
	}
}
