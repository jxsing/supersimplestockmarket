package com.jpmorganchase.supersimplestockmarket.services.calculators.impl;

import static com.jpmorganchase.supersimplestockmarket.util.Constants.Stocks.ALE;
import static com.jpmorganchase.supersimplestockmarket.util.Constants.Stocks.GIN;
import static com.jpmorganchase.supersimplestockmarket.util.Constants.Stocks.POP;
import static com.jpmorganchase.supersimplestockmarket.util.Constants.Stocks.TEA;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.jpmorganchase.supersimplestockmarket.enums.TradeAction;
import com.jpmorganchase.supersimplestockmarket.models.Stock;
import com.jpmorganchase.supersimplestockmarket.models.Trade;
import com.jpmorganchase.supersimplestockmarket.services.managers.StockManager;
import com.jpmorganchase.supersimplestockmarket.services.managers.TradeManager;
import com.jpmorganchase.supersimplestockmarket.util.TimestampBuilder;

public class GeometricMeanCalculatorImplTest {

	@InjectMocks
	private GeometricMeanCalculatorImpl geometricMeanCalculator = new GeometricMeanCalculatorImpl();

	@Mock
	private StockManager stockManager;

	@Mock
	private TradeManager tradeManager;

	public GeometricMeanCalculatorImplTest() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testForTwoTrades() {

		List<Stock> stocks = new ArrayList<Stock>();
		stocks.add(TEA);
		stocks.add(POP);
		doReturn(stocks).when(stockManager).getAllStocks();

		Trade teaTradeOne = new Trade(TimestampBuilder.buildTimestamp(1, 1,
				2016), TEA, 10, TradeAction.BUY, 25.0);

		mockAllTradesForStock(TEA, teaTradeOne);

		Trade popTradeOne = new Trade(TimestampBuilder.buildTimestamp(3, 3,
				2016), POP, 25, TradeAction.BUY, 4.0);

		mockAllTradesForStock(POP, popTradeOne);

		assertEquals(10.0, geometricMeanCalculator.calculateForAllStocks(), 0.0);
	}

	@Test
	public void testForThreeTrades() {

		List<Stock> stocks = new ArrayList<Stock>();
		stocks.add(TEA);
		stocks.add(POP);
		doReturn(stocks).when(stockManager).getAllStocks();

		Trade teaTradeOne = new Trade(TimestampBuilder.buildTimestamp(1, 1,
				2016), TEA, 10, TradeAction.BUY, 35.0);

		Trade teaTradeTwo = new Trade(TimestampBuilder.buildTimestamp(2, 2,
				2016), TEA, 7, TradeAction.SELL, 36.0);

		mockAllTradesForStock(TEA, teaTradeOne, teaTradeTwo);

		Trade popTradeOne = new Trade(TimestampBuilder.buildTimestamp(3, 3,
				2016), POP, 25, TradeAction.BUY, 56.23);

		mockAllTradesForStock(POP, popTradeOne);

		assertEquals(41.37, geometricMeanCalculator.calculateForAllStocks(),
				0.0);
	}
	
	@Test
	public void testForFourTrades() {

		List<Stock> stocks = new ArrayList<Stock>();
		stocks.add(TEA);
		stocks.add(POP);
		stocks.add(ALE);
		stocks.add(GIN);
		doReturn(stocks).when(stockManager).getAllStocks();

		Trade teaTradeOne = new Trade(TimestampBuilder.buildTimestamp(1, 1,
				2016), TEA, 10, TradeAction.BUY, 12.22);

		Trade teaTradeTwo = new Trade(TimestampBuilder.buildTimestamp(2, 2,
				2016), TEA, 7, TradeAction.SELL, 14.35);

		mockAllTradesForStock(TEA, teaTradeOne, teaTradeTwo);

		Trade popTradeOne = new Trade(TimestampBuilder.buildTimestamp(3, 3,
				2016), POP, 25, TradeAction.BUY, 47.23);

		mockAllTradesForStock(POP, popTradeOne);
		
		Trade ginTradeOne = new Trade(TimestampBuilder.buildTimestamp(3, 3,
				2016), GIN, 25, TradeAction.BUY, 11.25);

		mockAllTradesForStock(GIN, ginTradeOne);

		assertEquals(17.47, geometricMeanCalculator.calculateForAllStocks(),
				0.0);
	}

	private void mockAllTradesForStock(Stock stock, Trade... trades) {

		Set<Trade> tradesSet = new HashSet<Trade>();
		for (Trade trade : trades) {
			tradesSet.add(trade);
		}

		doReturn(tradesSet).when(tradeManager).getAllTradesForStock(stock);
	}
}
