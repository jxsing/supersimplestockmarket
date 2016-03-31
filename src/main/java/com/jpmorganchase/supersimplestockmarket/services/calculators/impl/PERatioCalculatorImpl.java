package com.jpmorganchase.supersimplestockmarket.services.calculators.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpmorganchase.supersimplestockmarket.models.Stock;
import com.jpmorganchase.supersimplestockmarket.services.calculators.PERatioCalculator;
import com.jpmorganchase.supersimplestockmarket.util.DoubleUtil;
import com.jpmorganchase.supersimplestockmarket.validators.StockValidator;

/**
 * PE Ratio Calculator implementation.
 * @author Jat
 *
 */
@Service
public class PERatioCalculatorImpl implements PERatioCalculator {

	@Autowired
	private StockValidator stockValidator;

	public double calculate(Stock stock, double price) {

		stockValidator.validate(stock);

		final double dividend = stock.getLastDividend();
		final double peRatio = price / dividend;

		return DoubleUtil.toTwoDecimalPlaces(peRatio);
	}

}
