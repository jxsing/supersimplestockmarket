package com.jpmorganchase.supersimplestockmarket.services.managers.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpmorganchase.supersimplestockmarket.models.Stock;
import com.jpmorganchase.supersimplestockmarket.services.managers.StockManager;
import com.jpmorganchase.supersimplestockmarket.validators.StockValidator;

/**
 * Default Stock Manager implementation.
 * 
 * 
 * @author Jat
 *
 */
@Service
public class StockManagerImpl implements StockManager {

	@Autowired
	private StockValidator stockValidator;

	// use stock symbol for key.
	private Map<String, Stock> stocksMap = new HashMap<String, Stock>();

	public Stock getStockBySymbol(String symbol) {
		return stocksMap.get(symbol);
	}

	public Collection<Stock> getAllStocks() {
		return stocksMap.values();
	}

	public void addStock(Stock stock) {

		stockValidator.validate(stock);

		final String symbol = stock.getSymbol();
		stocksMap.put(symbol, stock);
	}
}
