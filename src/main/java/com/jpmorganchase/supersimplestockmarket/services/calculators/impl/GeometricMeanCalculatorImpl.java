package com.jpmorganchase.supersimplestockmarket.services.calculators.impl;

import java.util.Collection;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpmorganchase.supersimplestockmarket.models.Stock;
import com.jpmorganchase.supersimplestockmarket.models.Trade;
import com.jpmorganchase.supersimplestockmarket.services.calculators.GeometricMeanCalculator;
import com.jpmorganchase.supersimplestockmarket.services.managers.StockManager;
import com.jpmorganchase.supersimplestockmarket.services.managers.TradeManager;
import com.jpmorganchase.supersimplestockmarket.util.DoubleUtil;

/**
 * Implemenation for Geometric mean calculation.
 * 
 * 
 * @author Jat
 *
 */
@Service
public class GeometricMeanCalculatorImpl implements GeometricMeanCalculator {

	@Autowired
	private StockManager stockManager;

	@Autowired
	private TradeManager tradeManager;

	public double calculateForAllStocks() {

		Collection<Stock> allStocks = stockManager.getAllStocks();

		int totalTrades = 0;
		double totalTradePriceMultiply = 1.0;

		for (Stock stock : allStocks) {

			Set<Trade> trades = tradeManager.getAllTradesForStock(stock);
			if (trades != null && !trades.isEmpty()) {

				for (Trade trade : trades) {

					totalTradePriceMultiply *= trade.getTradedPrice();
					++totalTrades;
				}
			}
		}

		final double geometricMean = Math.pow(totalTradePriceMultiply,
				1.0 / (double) totalTrades);

		return DoubleUtil.toTwoDecimalPlaces(geometricMean);
	}
}
