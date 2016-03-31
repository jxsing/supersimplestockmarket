package com.jpmorganchase.supersimplestockmarket.validators;

import org.springframework.stereotype.Component;

import com.jpmorganchase.supersimplestockmarket.enums.StockType;
import com.jpmorganchase.supersimplestockmarket.exceptions.InvalidStockException;
import com.jpmorganchase.supersimplestockmarket.models.Stock;

/**
 * Class for validating a Stock object.
 * 
 * @author Jat
 *
 */
@Component
public class StockValidator {

	/**
	 * Validate a stock object.
	 * 
	 * @param stock
	 *            the object to validate.
	 */
	public void validate(Stock stock) {

		if (stock == null) {
			throw new InvalidStockException("No stock set.");
		}

		final String stockSymbol = stock.getSymbol();
		if (stockSymbol == null || stockSymbol.trim().equals("")) {
			throw new InvalidStockException("Invalid stock symbol.");
		}

		final StockType stockType = stock.getType();
		if (stockType == null) {
			throw new InvalidStockException("No stock type set for stock with symbol "
					+ stockSymbol);
		}
	}
}
