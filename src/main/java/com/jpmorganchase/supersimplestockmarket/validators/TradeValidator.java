package com.jpmorganchase.supersimplestockmarket.validators;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jpmorganchase.supersimplestockmarket.enums.TradeAction;
import com.jpmorganchase.supersimplestockmarket.exceptions.InvalidTradeException;
import com.jpmorganchase.supersimplestockmarket.models.Trade;

/**
 * Class for validating a trade object.
 * 
 * 
 * @author Jat
 *
 */
@Component
public class TradeValidator {

	@Autowired
	private StockValidator stockValidator;

	/**
	 * Validate trade object.
	 * 
	 * @param trade the trade object to validate.
	 */
	public void validate(Trade trade) {

		if (trade == null) {
			throw new InvalidTradeException(
					"No Trade set.");
		}

		stockValidator.validate(trade.getStock());

		final Date timestamp = trade.getTimestamp();
		if (timestamp == null) {
			throw new InvalidTradeException(
					"No timestamp set for Trade.");
		}

		final TradeAction action = trade.getAction();
		if (action == null) {
			throw new InvalidTradeException(
					"No action set for Trade.");
		}

		final int quantityOfShares = trade.getQuantityOfShares();
		if (quantityOfShares < 1) {
			throw new InvalidTradeException(
					"Invalid quantity of shares value for Trade.");
		}

		final double tradedPrice = trade.getTradedPrice();
		if (tradedPrice <= 0.0) {
			throw new InvalidTradeException(
					"Invalid traded price for Trade.");
		}
	}
}
