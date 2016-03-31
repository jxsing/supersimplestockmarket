package com.jpmorganchase.supersimplestockmarket.services.managers;

import java.util.Date;
import java.util.Set;

import com.jpmorganchase.supersimplestockmarket.models.Stock;
import com.jpmorganchase.supersimplestockmarket.models.Trade;


public interface TradeManager {
	
	/**
	 * Get trades for stock in last fifteen minutes.
	 * 
	 * @param stock the stock
	 * @param now date to use to back track fifteen minutes from.
	 * @return set of trades within fifteen minutes period.
	 */
	Set<Trade> getTradesForStockInLastFifteenMinutes(Stock stock, Date now);
	
	/**
	 * Get all trades for a given stock.
	 * 
	 * @param stock the stock
	 * @return set of trades or null if none found.
	 */
	Set<Trade> getAllTradesForStock(Stock stock);
	
	/**
	 * Record the trade.
	 * 
	 * @param trade the trade object to save.
	 */
	void saveTrade(Trade trade);
}
