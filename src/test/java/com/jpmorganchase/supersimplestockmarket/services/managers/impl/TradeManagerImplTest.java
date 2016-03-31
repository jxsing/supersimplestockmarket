package com.jpmorganchase.supersimplestockmarket.services.managers.impl;

import static com.jpmorganchase.supersimplestockmarket.enums.TradeAction.BUY;
import static com.jpmorganchase.supersimplestockmarket.util.Constants.Stocks.ALE;
import static com.jpmorganchase.supersimplestockmarket.util.Constants.Stocks.GIN;
import static com.jpmorganchase.supersimplestockmarket.util.Constants.Stocks.JOE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jpmorganchase.supersimplestockmarket.AppConfig;
import com.jpmorganchase.supersimplestockmarket.models.Stock;
import com.jpmorganchase.supersimplestockmarket.models.Trade;
import com.jpmorganchase.supersimplestockmarket.util.TimestampBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class TradeManagerImplTest {

	@Autowired
	private TradeManagerImpl tradeManager = new TradeManagerImpl();

	@Test
	public void testGetTradesForStockInLastFifteenMinutes() {

		Stock stock = ALE;

		// for purposes of this test. The time is 01/01/2016 09:15:00.000
		Date now = TimestampBuilder.buildTimestamp(1, 1, 2016, 9, 15, 0, 0);

		Trade tradeMadeFiveMinsAgo = newTrade();
		tradeMadeFiveMinsAgo.setStock(stock);
		tradeMadeFiveMinsAgo.setTimestamp(TimestampBuilder.buildTimestamp(1, 1,
				2016, 9, 10, 0, 0));

		Trade tradeMadeTenMinsAgo = newTrade();
		tradeMadeTenMinsAgo.setStock(stock);
		tradeMadeTenMinsAgo.setTimestamp(TimestampBuilder.buildTimestamp(1, 1,
				2016, 9, 5, 0, 0));

		Trade tradeMadeFifteenMinsAgo = newTrade();
		tradeMadeFifteenMinsAgo.setStock(stock);
		tradeMadeFifteenMinsAgo.setTimestamp(TimestampBuilder.buildTimestamp(1,
				1, 2016, 9, 0, 0, 0));

		Trade tradeMadeFifteenMinsAndOneSecondAgo = newTrade();
		tradeMadeFifteenMinsAndOneSecondAgo.setStock(stock);
		tradeMadeFifteenMinsAndOneSecondAgo.setTimestamp(TimestampBuilder
				.buildTimestamp(1, 1, 2016, 8, 59, 59, 0));

		Trade tradeMadeSixteenMinsAgo = newTrade();
		tradeMadeSixteenMinsAgo.setStock(stock);
		tradeMadeSixteenMinsAgo.setTimestamp(TimestampBuilder.buildTimestamp(1,
				1, 2016, 8, 59, 0, 0));

		tradeManager.saveTrade(tradeMadeSixteenMinsAgo);
		tradeManager.saveTrade(tradeMadeFifteenMinsAndOneSecondAgo);
		tradeManager.saveTrade(tradeMadeFiveMinsAgo);
		tradeManager.saveTrade(tradeMadeFifteenMinsAgo);
		tradeManager.saveTrade(tradeMadeTenMinsAgo);

		Set<Trade> tradesInLastFifteenMins = tradeManager
				.getTradesForStockInLastFifteenMinutes(stock, now);

		assertTrue(tradesInLastFifteenMins.contains(tradeMadeFiveMinsAgo));
		assertTrue(tradesInLastFifteenMins.contains(tradeMadeTenMinsAgo));
		assertTrue(tradesInLastFifteenMins.contains(tradeMadeFifteenMinsAgo));
		assertFalse(tradesInLastFifteenMins
				.contains(tradeMadeFifteenMinsAndOneSecondAgo));
		assertFalse(tradesInLastFifteenMins.contains(tradeMadeSixteenMinsAgo));

		assertEquals(3, tradesInLastFifteenMins.size());

	}

	@Test
	public void testGetAllTradesForStock() {

		Stock stock = JOE;

		Trade tradeOne = newTrade();
		tradeOne.setStock(stock);
		tradeOne.setTimestamp(TimestampBuilder.buildTimestamp(1, 1,
				2016, 9, 10, 0, 0));

		Trade tradeTwo = newTrade();
		tradeTwo.setStock(stock);
		tradeTwo.setTimestamp(TimestampBuilder.buildTimestamp(1, 1,
				2016, 9, 5, 0, 0));

		Trade tradeThree = newTrade();
		tradeThree.setStock(stock);
		tradeThree.setTimestamp(TimestampBuilder.buildTimestamp(1,
				1, 2016, 9, 0, 0, 0));

		Trade tradeFour = newTrade();
		tradeFour.setStock(stock);
		tradeFour.setTimestamp(TimestampBuilder
				.buildTimestamp(1, 1, 2016, 8, 59, 59, 0));

		Trade tradeFive = newTrade();
		tradeFive.setStock(stock);
		tradeFive.setTimestamp(TimestampBuilder.buildTimestamp(1,
				1, 2016, 8, 59, 0, 0));
		
		
		tradeManager.saveTrade(tradeOne);
		tradeManager.saveTrade(tradeTwo);
		tradeManager.saveTrade(tradeThree);
		tradeManager.saveTrade(tradeFour);
		tradeManager.saveTrade(tradeFive);
		
		
		Set<Trade> trades = tradeManager.getAllTradesForStock(stock);
		
		assertTrue(trades.contains(tradeOne));
		assertTrue(trades.contains(tradeTwo));
		assertTrue(trades.contains(tradeThree));
		assertTrue(trades.contains(tradeFour));
		assertTrue(trades.contains(tradeFive));
		
		assertEquals(5, trades.size());
	}

	@Test
	public void testSaveTrade() {

		final Stock stock = GIN;

		Trade trade = newTrade();
		trade.setTimestamp(TimestampBuilder.buildTimestamp(30, 3, 2016));
		trade.setStock(stock);
		trade.setQuantityOfShares(14);
		trade.setAction(BUY);
		trade.setTradedPrice(20.23);

		Set<Trade> allTradesForStock = tradeManager.getAllTradesForStock(stock);
		assertNull(allTradesForStock);

		tradeManager.saveTrade(trade);

		allTradesForStock = tradeManager.getAllTradesForStock(stock);
		assertTrue(allTradesForStock.contains(trade));
	}
	
	public static Trade newTrade() {
		
		Trade trade = new Trade();
		trade.setTimestamp(new Date());
		trade.setAction(BUY);
		trade.setQuantityOfShares(1);
		trade.setTradedPrice(0.1);
		return trade;
	}
}
