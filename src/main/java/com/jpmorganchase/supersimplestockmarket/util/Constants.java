package com.jpmorganchase.supersimplestockmarket.util;

import static com.jpmorganchase.supersimplestockmarket.enums.StockType.COMMON;
import static com.jpmorganchase.supersimplestockmarket.enums.StockType.PREFERRED;

import com.jpmorganchase.supersimplestockmarket.models.Stock;

/**
 * Class defining constants.
 * 
 * @author Jat
 *
 */
public class Constants {

	// Defining Stocks in one place.  This is convenient for Unit Tests.
	public static class Stocks {

		public static final Stock TEA = new Stock("TEA", COMMON, 0, null, 100);
		public static final Stock POP = new Stock("POP", COMMON, 8, null, 100);
		public static final Stock ALE = new Stock("ALE", COMMON, 23, null, 60);
		public static final Stock GIN = new Stock("GIN", PREFERRED, 8, 2.0, 100);
		public static final Stock JOE = new Stock("JOE", COMMON, 13, null, 250);
	}

}
