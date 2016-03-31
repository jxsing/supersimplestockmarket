package com.jpmorganchase.supersimplestockmarket.models;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.jpmorganchase.supersimplestockmarket.enums.StockType;

public class StockTest {

	public static final String DEFAULT_SYMBOL_VALUE = "DEF";
	public static final StockType DEFAULT_STOCK_TYPE_VALUE = StockType.COMMON;
	public static final long DEFAULT_LAST_DIVIDEND_VALUE = 100;
	public static final Double DEFAULT_FIXED_DIVIDEND_VALUE = 10.23;
	public static final long DEFAULT_PAR_VALUE = 90;

	private static final String NEW_SYMBOL_VALUE = "NewSymbol";
	private static final StockType NEW_STOCK_TYPE_VALUE = StockType.PREFERRED;
	private static final long NEW_LAST_DIVIDEND_VALUE = 70;
	private static final Double NEW_FIXED_DIVIDEND_VALUE = 30.25;
	private static final long NEW_PAR_VALUE = 120;

	private Stock stock;

	@Before
	public void onSetup() {
		stock = new Stock(DEFAULT_SYMBOL_VALUE, DEFAULT_STOCK_TYPE_VALUE,
				DEFAULT_LAST_DIVIDEND_VALUE, DEFAULT_FIXED_DIVIDEND_VALUE,
				DEFAULT_PAR_VALUE);
	}

	@Test
	public void testSymbolProperty() {
		stock.setSymbol(NEW_SYMBOL_VALUE);
		assertEquals(NEW_SYMBOL_VALUE, stock.getSymbol());

		// verify that other properties are not touched.
		assertEquals(DEFAULT_STOCK_TYPE_VALUE, stock.getType());
		assertEquals(DEFAULT_LAST_DIVIDEND_VALUE, stock.getLastDividend());
		assertEquals(DEFAULT_FIXED_DIVIDEND_VALUE, stock.getFixedDividend());
		assertEquals(DEFAULT_PAR_VALUE, stock.getParValue());
	}

	@Test
	public void testTypeProperty() {
		stock.setType(NEW_STOCK_TYPE_VALUE);
		assertEquals(NEW_STOCK_TYPE_VALUE, stock.getType());

		// verify that other properties are not touched.
		assertEquals(DEFAULT_SYMBOL_VALUE, stock.getSymbol());
		assertEquals(DEFAULT_LAST_DIVIDEND_VALUE, stock.getLastDividend());
		assertEquals(DEFAULT_FIXED_DIVIDEND_VALUE, stock.getFixedDividend());
		assertEquals(DEFAULT_PAR_VALUE, stock.getParValue());
	}

	@Test
	public void testLastDividendProperty() {
		stock.setLastDividend(NEW_LAST_DIVIDEND_VALUE);
		assertEquals(NEW_LAST_DIVIDEND_VALUE, stock.getLastDividend());

		// verify that other properties are not touched.
		assertEquals(DEFAULT_SYMBOL_VALUE, stock.getSymbol());
		assertEquals(DEFAULT_STOCK_TYPE_VALUE, stock.getType());
		assertEquals(DEFAULT_FIXED_DIVIDEND_VALUE, stock.getFixedDividend());
		assertEquals(DEFAULT_PAR_VALUE, stock.getParValue());
	}

	@Test
	public void testFixedDividendProperty() {
		stock.setFixedDividend(NEW_FIXED_DIVIDEND_VALUE);
		assertEquals(NEW_FIXED_DIVIDEND_VALUE, stock.getFixedDividend());

		// verify that other properties are not touched.
		assertEquals(DEFAULT_SYMBOL_VALUE, stock.getSymbol());
		assertEquals(DEFAULT_STOCK_TYPE_VALUE, stock.getType());
		assertEquals(DEFAULT_LAST_DIVIDEND_VALUE, stock.getLastDividend());
		assertEquals(DEFAULT_PAR_VALUE, stock.getParValue());
	}

	@Test
	public void testParValueProperty() {
		stock.setParValue(NEW_PAR_VALUE);
		assertEquals(NEW_PAR_VALUE, stock.getParValue());

		// verify that other properties are not touched.
		assertEquals(DEFAULT_SYMBOL_VALUE, stock.getSymbol());
		assertEquals(DEFAULT_STOCK_TYPE_VALUE, stock.getType());
		assertEquals(DEFAULT_LAST_DIVIDEND_VALUE, stock.getLastDividend());
		assertEquals(DEFAULT_FIXED_DIVIDEND_VALUE, stock.getFixedDividend());
	}

	@Test
	public void testConstructor() {

		stock = new Stock(NEW_SYMBOL_VALUE, NEW_STOCK_TYPE_VALUE,
				NEW_LAST_DIVIDEND_VALUE, NEW_FIXED_DIVIDEND_VALUE,
				NEW_PAR_VALUE);

		assertEquals(NEW_SYMBOL_VALUE, stock.getSymbol());
		assertEquals(NEW_STOCK_TYPE_VALUE, stock.getType());
		assertEquals(NEW_LAST_DIVIDEND_VALUE, stock.getLastDividend());
		assertEquals(NEW_FIXED_DIVIDEND_VALUE, stock.getFixedDividend());
		assertEquals(NEW_PAR_VALUE, stock.getParValue());

	}
}
