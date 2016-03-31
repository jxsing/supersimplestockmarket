package com.jpmorganchase.supersimplestockmarket.services.calculators.impl;

import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpmorganchase.supersimplestockmarket.models.Stock;
import com.jpmorganchase.supersimplestockmarket.models.Trade;
import com.jpmorganchase.supersimplestockmarket.services.calculators.VolumeWeightedStockPriceCalculator;
import com.jpmorganchase.supersimplestockmarket.services.managers.TradeManager;
import com.jpmorganchase.supersimplestockmarket.util.DoubleUtil;
import com.jpmorganchase.supersimplestockmarket.validators.StockValidator;

/**
 * Implementation for Volumne Weighted Stock Price Calculator.  This implementation
 * retrieves trades made within last fifteen minutes.
 * 
 * @author Jat
 *
 */
@Service
public class VolumeWeightedStockPriceCalculatorImpl implements
		VolumeWeightedStockPriceCalculator {

	@Autowired
	private TradeManager tradeManager;

	@Autowired
	private StockValidator stockValidator;

	public double calculate(Stock stock, Date date) {

		stockValidator.validate(stock);

		double sumOfTradedPriceByQuantity = 0.0;
		int sumOfQuantityOfShares = 0;

		Set<Trade> trades = tradeManager.getTradesForStockInLastFifteenMinutes(
				stock, date);

		if (trades != null) {

			for (final Trade trade : trades) {

				final double tradedPrice = trade.getTradedPrice();
				final int quantityOfShares = trade.getQuantityOfShares();

				sumOfTradedPriceByQuantity += (tradedPrice * quantityOfShares);
				sumOfQuantityOfShares += quantityOfShares;
			}
		}

		return DoubleUtil.toTwoDecimalPlaces(sumOfTradedPriceByQuantity
				/ sumOfQuantityOfShares);
	}

}
