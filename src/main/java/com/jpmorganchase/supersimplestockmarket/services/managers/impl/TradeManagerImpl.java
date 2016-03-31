package com.jpmorganchase.supersimplestockmarket.services.managers.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.jpmorganchase.supersimplestockmarket.comparators.TradeComparator;
import com.jpmorganchase.supersimplestockmarket.models.Stock;
import com.jpmorganchase.supersimplestockmarket.models.Trade;
import com.jpmorganchase.supersimplestockmarket.services.managers.TradeManager;
import com.jpmorganchase.supersimplestockmarket.validators.TradeValidator;

/**
 * Default Trade Manager implementation.
 * 
 * This class will record trades and retrieve trades for a given stock.
 * 
 * @author Jat
 *
 */
@Service
public class TradeManagerImpl implements TradeManager {

	/*
	 * This comparator is used to ensure the Set of trades for a 
	 * stock is ordered in descending order by timestamp.
	 */
	@Autowired
	@Qualifier("TradeInDescendingOrderByTimestampComparator")
	private TradeComparator comparator;

	@Autowired
	private TradeValidator tradeValidator;

	private Map<Stock, Set<Trade>> trades;

	public TradeManagerImpl() {
		trades = new HashMap<Stock, Set<Trade>>();
	}

	public void saveTrade(Trade trade) {

		tradeValidator.validate(trade);

		final Stock stock = trade.getStock();

		// verif if there is already a set for trades defined for this stock.
		Set<Trade> tradesForStockSet = getAllTradesForStock(stock);
		if (tradesForStockSet == null) {
			
			// no set defined, so create one and apply the comparator to 
			// ensure it is sorted in descending order by timestamp.
			// TreeSet is used to allow automatic sorting of elements.
			tradesForStockSet = new TreeSet<Trade>(comparator);
			
			// link up tree set with stock in hash map.
			trades.put(stock, tradesForStockSet);
		}

		// add trade to set.
		tradesForStockSet.add(trade);
	}

	public Set<Trade> getTradesForStockInLastFifteenMinutes(Stock stock,
			Date now) {
		
		// Calculate time in milli-seconds which was 15 minutes from now.
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		calendar.add(Calendar.MINUTE, -15);
		Date fifteenMinutesAgoDate = calendar.getTime();
		long fifteenMinutesAgoTime = fifteenMinutesAgoDate.getTime();
		
		
		// define a set to hold all the trades that we are interested in.
		Set<Trade> stockTradesInLastFifteenMinsSet = new LinkedHashSet<Trade>();

		
		// get all trades for stock.  this set should be sorted in descending order
		// by timestamp.  So this means the latest trades should appear first.
		Set<Trade> allStockTradesSet = getAllTradesForStock(stock);

		// iterate over element in set.
		Iterator<Trade> tradesIter = allStockTradesSet.iterator();
		while (tradesIter.hasNext()) {

			Trade trade = tradesIter.next();
			if (trade != null) {

				final long tradeTime = trade.getTimestamp().getTime();
				
				// if trade time is before fifteen minutes ago, then add 
				// trade to set.
				if (tradeTime >= fifteenMinutesAgoTime) {
					stockTradesInLastFifteenMinsSet.add(trade);
				} else {
					// Encountered a trade older than fifteen minutes.  
					// Since this set is ordered by timestamp, we can 
					// assume that all further trades are past 15 minutes too.
					// Therefore we are breaking out of this iteration.
					break;
				}
			}
		}
		return stockTradesInLastFifteenMinsSet;
	}

	public Set<Trade> getAllTradesForStock(Stock stock) {
		return trades.get(stock);
	}
}
