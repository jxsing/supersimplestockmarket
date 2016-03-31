package com.jpmorganchase.supersimplestockmarket.services.calculators.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpmorganchase.supersimplestockmarket.enums.StockType;
import com.jpmorganchase.supersimplestockmarket.models.Stock;
import com.jpmorganchase.supersimplestockmarket.services.calculators.DividendYieldCalculator;
import com.jpmorganchase.supersimplestockmarket.util.DoubleUtil;
import com.jpmorganchase.supersimplestockmarket.validators.StockValidator;

/**
 * Calculator implementation that calculates dividend yield.
 * 
 * @author Jat
 *
 */
@Service
public class DividendYieldCalculatorImpl implements DividendYieldCalculator {

	@Autowired
	private StockValidator stockValidator;

	public double calculate(Stock stock, double price) {

		stockValidator.validate(stock);

		double dividendYield = Double.NaN;

		final StockType stockType = stock.getType();
		if (StockType.COMMON.equals(stockType)) {

			final long lastDividend = stock.getLastDividend();

			dividendYield = lastDividend / price;

		} else if (StockType.PREFERRED.equals(stockType)) {

			final double fixedDividend = stock.getFixedDividend();
			final long parValue = stock.getParValue();

			dividendYield = (fixedDividend * parValue) / price;

		} else {
			throw new UnsupportedOperationException(
					"Unknown stock type encountered " + stockType);
		}
		
		return DoubleUtil.toTwoDecimalPlaces(dividendYield);
	}
}
