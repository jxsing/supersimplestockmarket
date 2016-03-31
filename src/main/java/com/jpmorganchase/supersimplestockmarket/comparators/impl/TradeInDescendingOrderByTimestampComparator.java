package com.jpmorganchase.supersimplestockmarket.comparators.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.jpmorganchase.supersimplestockmarket.comparators.TradeComparator;
import com.jpmorganchase.supersimplestockmarket.models.Trade;

/**
 * Comparator implementation for sorting of Trade object.
 * 
 * This implementation will ensure that Trades are sorted in descending order of
 * time stamp. This means the latest trade will appear first in the set.
 * 
 * @author Jat
 *
 */
@Component
@Qualifier("TradeInDescendingOrderByTimestampComparator")
public class TradeInDescendingOrderByTimestampComparator implements
		TradeComparator {

	public int compare(Trade o1, Trade o2) {

		final Date o1Timestamp = o1.getTimestamp();
		final Date o2Timestamp = o2.getTimestamp();

		final long o1Time = o1Timestamp.getTime();
		final long o2Time = o2Timestamp.getTime();

		if (o1Time < o2Time) {
			return 1;
		} else if(o1Time > o2Time) {
			return -1;
		} else {
			return 0;
		}
	}

}
