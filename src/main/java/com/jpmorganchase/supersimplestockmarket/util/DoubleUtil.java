package com.jpmorganchase.supersimplestockmarket.util;

import java.math.BigDecimal;

public class DoubleUtil {
	
	/**
	 * Converts double value so that only two decimal places are returned.
	 * 
	 * For example:
	 * 
	 * 100.22212341234 will be returned as 100.22
	 * 
	 * 
	 * @param value the value to convert
	 * @return new double value in two decimal places.
	 */
	public static double toTwoDecimalPlaces(double value) {
		
		if (!Double.isInfinite(value) && !Double.isNaN(value)) {
			return new BigDecimal(value).setScale(2,
					BigDecimal.ROUND_DOWN).doubleValue();
		}
		return value;
	}

}
