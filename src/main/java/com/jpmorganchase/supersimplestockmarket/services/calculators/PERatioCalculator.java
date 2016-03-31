package com.jpmorganchase.supersimplestockmarket.services.calculators;

import com.jpmorganchase.supersimplestockmarket.models.Stock;

public interface PERatioCalculator {

	/**
	 * Calculate PE Ratio given a stock and price.
	 * 
	 * @param stock the stock
	 * @param price the price
	 * @return the PE Ratio.
	 */
	double calculate(Stock stock, double price);
}
