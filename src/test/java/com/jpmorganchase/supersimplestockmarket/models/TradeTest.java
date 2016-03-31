package com.jpmorganchase.supersimplestockmarket.models;

import static com.jpmorganchase.supersimplestockmarket.enums.StockType.COMMON;
import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.jpmorganchase.supersimplestockmarket.enums.TradeAction;
import com.jpmorganchase.supersimplestockmarket.util.TimestampBuilder;

public class TradeTest {

	private static final Date DEFAULT_TIMESTAMP = TimestampBuilder
			.buildTimestamp(18, 3, 2016);
	private static final Stock DEFAULT_STOCK = new Stock("OLD", COMMON, 20,
			null, 100);
	private static final int DEFAULT_QUANTITY_OF_SHARES = 10;
	private static final TradeAction DEFAULT_TRADE_ACTION = TradeAction.BUY;
	private static final double DEFAULT_TRADED_PRICE = 22.0;

	private static final Date NEW_TIMESTAMP = TimestampBuilder.buildTimestamp(
			20, 3, 2016);
	private static final Stock NEW_STOCK = new Stock("NEW", COMMON, 20, null,
			100);
	private static final int NEW_QUANTITY_OF_SHARES = 20;
	private static final TradeAction NEW_TRADE_ACTION = TradeAction.SELL;
	private static final double NEW_TRADED_PRICE = 60.0;

	private Trade trade;

	@Before
	public void onSetup() {

		trade = new Trade();
		trade.setTimestamp(DEFAULT_TIMESTAMP);
		trade.setStock(DEFAULT_STOCK);
		trade.setQuantityOfShares(DEFAULT_QUANTITY_OF_SHARES);
		trade.setAction(DEFAULT_TRADE_ACTION);
		trade.setTradedPrice(DEFAULT_TRADED_PRICE);

	}

	@Test
	public void testTimestampProperty() {

		trade.setTimestamp(NEW_TIMESTAMP);

		assertEquals(NEW_TIMESTAMP, trade.getTimestamp());

		assertEquals(DEFAULT_STOCK, trade.getStock());
		assertEquals(DEFAULT_QUANTITY_OF_SHARES, trade.getQuantityOfShares());
		assertEquals(DEFAULT_TRADE_ACTION, trade.getAction());
		assertEquals(DEFAULT_TRADED_PRICE, trade.getTradedPrice(), 0.0);

	}

	@Test
	public void testStockProperty() {

		trade.setStock(NEW_STOCK);
		assertEquals(NEW_STOCK, trade.getStock());

		assertEquals(DEFAULT_TIMESTAMP, trade.getTimestamp());
		assertEquals(DEFAULT_QUANTITY_OF_SHARES, trade.getQuantityOfShares());
		assertEquals(DEFAULT_TRADE_ACTION, trade.getAction());
		assertEquals(DEFAULT_TRADED_PRICE, trade.getTradedPrice(), 0.0);

	}

	@Test
	public void testQuantityOfSharesProperty() {

		trade.setQuantityOfShares(NEW_QUANTITY_OF_SHARES);
		assertEquals(NEW_QUANTITY_OF_SHARES, trade.getQuantityOfShares());

		assertEquals(DEFAULT_TIMESTAMP, trade.getTimestamp());
		assertEquals(DEFAULT_STOCK, trade.getStock());
		assertEquals(DEFAULT_TRADE_ACTION, trade.getAction());
		assertEquals(DEFAULT_TRADED_PRICE, trade.getTradedPrice(), 0.0);

	}

	@Test
	public void testActionProperty() {

		trade.setAction(NEW_TRADE_ACTION);
		assertEquals(NEW_TRADE_ACTION, trade.getAction());

		assertEquals(DEFAULT_TIMESTAMP, trade.getTimestamp());
		assertEquals(DEFAULT_STOCK, trade.getStock());
		assertEquals(DEFAULT_QUANTITY_OF_SHARES, trade.getQuantityOfShares());
		assertEquals(DEFAULT_TRADED_PRICE, trade.getTradedPrice(), 0.0);
	}

	@Test
	public void testTradedPrice() {

		trade.setTradedPrice(NEW_TRADED_PRICE);
		assertEquals(NEW_TRADED_PRICE, trade.getTradedPrice(), 0.0);

		assertEquals(DEFAULT_TIMESTAMP, trade.getTimestamp());
		assertEquals(DEFAULT_STOCK, trade.getStock());
		assertEquals(DEFAULT_QUANTITY_OF_SHARES, trade.getQuantityOfShares());
		assertEquals(DEFAULT_TRADE_ACTION, trade.getAction());
	}
}
