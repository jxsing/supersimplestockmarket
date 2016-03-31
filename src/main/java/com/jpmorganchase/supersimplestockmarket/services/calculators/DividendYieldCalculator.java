package com.jpmorganchase.supersimplestockmarket.services.calculators;

import com.jpmorganchase.supersimplestockmarket.models.Stock;

public interface DividendYieldCalculator {

	/**
	 * Calculate dividend yeid for a given stock and price.
	 * 
	 * @param stock the stock
	 * @param price the price
	 * @return the dividend yield
	 */
	double calculate(Stock stock, double price);
}
