package com.jpmorganchase.supersimplestockmarket.services.calculators;

import java.util.Date;

import com.jpmorganchase.supersimplestockmarket.models.Stock;

public interface VolumeWeightedStockPriceCalculator {
	
	/**
	 * Calculate volume weighted stock price given a stock and a date-time.
	 * 
	 * @param stock the stock
	 * @param date the date and time
	 * @return volumne weighted stock price
	 */
	double calculate(Stock stock, Date date);

}
