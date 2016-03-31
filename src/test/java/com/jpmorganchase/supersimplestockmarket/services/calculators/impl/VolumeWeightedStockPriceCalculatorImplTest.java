package com.jpmorganchase.supersimplestockmarket.services.calculators.impl;

import static com.jpmorganchase.supersimplestockmarket.util.Constants.Stocks.TEA;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.jpmorganchase.supersimplestockmarket.enums.TradeAction;
import com.jpmorganchase.supersimplestockmarket.models.Trade;
import com.jpmorganchase.supersimplestockmarket.services.managers.TradeManager;
import com.jpmorganchase.supersimplestockmarket.util.TimestampBuilder;
import com.jpmorganchase.supersimplestockmarket.validators.StockValidator;

public class VolumeWeightedStockPriceCalculatorImplTest {

	@InjectMocks
	private VolumeWeightedStockPriceCalculatorImpl calculator = new VolumeWeightedStockPriceCalculatorImpl();

	@Mock
	private TradeManager tradeManager;

	@Mock(answer = Answers.CALLS_REAL_METHODS)
	private StockValidator stockValidator;

	public VolumeWeightedStockPriceCalculatorImplTest() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test() {

		Trade tradeOne = new Trade(TimestampBuilder.buildTimestamp(1, 1, 2016),
				TEA, 10, TradeAction.BUY, 35.0);

		Trade tradeTwo = new Trade(TimestampBuilder.buildTimestamp(2, 2, 2016),
				TEA, 7, TradeAction.SELL, 36.0);

		Trade tradeThree = new Trade(
				TimestampBuilder.buildTimestamp(3, 3, 2016), TEA, 25,
				TradeAction.BUY, 56.23);

		Set<Trade> trades = new HashSet<Trade>();
		trades.add(tradeOne);
		trades.add(tradeTwo);
		trades.add(tradeThree);

		Date now = new Date();

		doReturn(trades).when(tradeManager)
				.getTradesForStockInLastFifteenMinutes(TEA, now);

		assertEquals(47.80, calculator.calculate(TEA, now), 0.0);
	}

}
