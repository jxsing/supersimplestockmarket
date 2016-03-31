package com.jpmorganchase.supersimplestockmarket.comparators;

import static com.jpmorganchase.supersimplestockmarket.util.TimestampBuilder.buildTimestamp;
import static org.junit.Assert.assertEquals;

import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

import com.jpmorganchase.supersimplestockmarket.comparators.impl.TradeInDescendingOrderByTimestampComparator;
import com.jpmorganchase.supersimplestockmarket.models.Trade;

public class TradeInDescendingOrderByTimestampComparatorTest {
	
	private TradeInDescendingOrderByTimestampComparator comparator = new TradeInDescendingOrderByTimestampComparator();
	
	@Test
	public void testComparatorOrdering() {
		
		Trade tradeOne = new Trade();
		tradeOne.setTimestamp(buildTimestamp(29, 3, 2016, 9, 0, 0, 0));
		
		Trade tradeTwo = new Trade();
		tradeTwo.setTimestamp(buildTimestamp(29, 3, 2016, 9, 0, 0, 1));
		
		Trade tradeThree = new Trade();
		tradeThree.setTimestamp(buildTimestamp(29, 3, 2016, 9, 0, 1, 0));
		
		Trade tradeFour = new Trade();
		tradeFour.setTimestamp(buildTimestamp(29, 3, 2016, 9, 1, 0, 0));
		
		Trade tradeFive = new Trade();
		tradeFive.setTimestamp(buildTimestamp(29, 3, 2016, 10, 0, 0, 0));
		
		Trade tradeSix = new Trade();
		tradeSix.setTimestamp(buildTimestamp(30, 3, 2016, 9, 0, 0, 0));
		
		Trade tradeSeven = new Trade();
		tradeSeven.setTimestamp(buildTimestamp(29, 4, 2016, 9, 0, 0, 0));
		
		Trade tradeEight = new Trade();
		tradeEight.setTimestamp(buildTimestamp(29, 3, 2017, 9, 0, 0, 0));
		

		Set<Trade> trades = new TreeSet<Trade>(comparator);
				
		trades.add(tradeTwo);
		trades.add(tradeFour);
		trades.add(tradeEight);
		trades.add(tradeOne);
		trades.add(tradeThree);
		trades.add(tradeSix);
		trades.add(tradeFive);
		trades.add(tradeSeven);
				
		
		// verify order.
		
		Trade[] expectedTradeOrder = {
				tradeEight,
				tradeSeven,
				tradeSix,
				tradeFive,
				tradeFour,
				tradeThree,
				tradeTwo,
				tradeOne				
		};
		
		int index = 0;
		for(Trade trade : trades) {
			assertEquals("Mismatch at index " + index, expectedTradeOrder[index++], trade);
		}
	}
}
