package com.jpmorganchase.supersimplestockmarket.services.managers;

import java.util.Collection;

import com.jpmorganchase.supersimplestockmarket.models.Stock;

public interface StockManager {

	/**
	 * Retrieve a stock by its symbol value.
	 * 
	 * @param symbol the symbol value
	 * @return the stock object or null if not found.
	 */
	Stock getStockBySymbol(String symbol);

	/**
	 * Get all stocks.
	 * 
	 * 
	 * @return collection of all stocks.
	 */
	Collection<Stock> getAllStocks();

	
	/**
	 * Add a stock.
	 * 
	 * @param stock the stock to add.
	 */
	void addStock(Stock stock);

}
