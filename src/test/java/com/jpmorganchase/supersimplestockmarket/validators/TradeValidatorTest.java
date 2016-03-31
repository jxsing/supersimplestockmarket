package com.jpmorganchase.supersimplestockmarket.validators;

import java.util.Date;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.jpmorganchase.supersimplestockmarket.enums.StockType;
import com.jpmorganchase.supersimplestockmarket.enums.TradeAction;
import com.jpmorganchase.supersimplestockmarket.exceptions.InvalidStockException;
import com.jpmorganchase.supersimplestockmarket.exceptions.InvalidTradeException;
import com.jpmorganchase.supersimplestockmarket.models.Stock;
import com.jpmorganchase.supersimplestockmarket.models.Trade;

public class TradeValidatorTest {

	@InjectMocks
	private TradeValidator tradeValidator = new TradeValidator();

	@Mock(answer = Answers.CALLS_REAL_METHODS)
	private StockValidator stockValidator;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	private Trade trade;
	private Stock stock;

	public TradeValidatorTest() {

		MockitoAnnotations.initMocks(this);
	}

	@Before
	public void onSetUp() {

		stock = new Stock();
		stock.setSymbol("TST");
		stock.setType(StockType.COMMON);

		trade = new Trade();
		trade.setStock(stock);
		trade.setTimestamp(new Date());
		trade.setAction(TradeAction.BUY);
		trade.setQuantityOfShares(20);
		trade.setTradedPrice(20.0);
	}

	@Test
	public void testValidateForNull() {

		trade = null;

		expectedException.expect(InvalidTradeException.class);
		expectedException.expectMessage("No Trade set.");

		tradeValidator.validate(trade);
	}

	@Test
	public void testValidateForNoStock() {

		trade.setStock(null);

		expectedException.expect(InvalidStockException.class);
		expectedException.expectMessage("No stock set.");

		tradeValidator.validate(trade);
	}

	@Test
	public void testValidateForNullStockSymbol() {

		stock.setSymbol(null);

		expectedException.expect(InvalidStockException.class);
		expectedException.expectMessage("Invalid stock symbol.");

		tradeValidator.validate(trade);
	}

	@Test
	public void testValidateForEmptyStockSymbol() {

		stock.setSymbol("");

		expectedException.expect(InvalidStockException.class);
		expectedException.expectMessage("Invalid stock symbol.");

		tradeValidator.validate(trade);
	}

	@Test
	public void testValidateForBlankStockSymbol() {

		stock.setSymbol("   ");

		expectedException.expect(InvalidStockException.class);
		expectedException.expectMessage("Invalid stock symbol.");

		tradeValidator.validate(trade);
	}

	@Test
	public void testValidateForNullStockType() {

		stock.setType(null);

		expectedException.expect(InvalidStockException.class);
		expectedException
				.expectMessage("No stock type set for stock with symbol TST");

		tradeValidator.validate(trade);
	}

	@Test
	public void testValidateForNullTimestamp() {

		trade.setTimestamp(null);

		expectedException.expect(InvalidTradeException.class);
		expectedException.expectMessage("No timestamp set for Trade.");

		tradeValidator.validate(trade);
	}

	@Test
	public void testValidateForNullTradeAction() {

		trade.setAction(null);

		expectedException.expect(InvalidTradeException.class);
		expectedException.expectMessage("No action set for Trade.");

		tradeValidator.validate(trade);
	}

	@Test
	public void testValidateForZeroQuantityOfShares() {

		trade.setQuantityOfShares(0);

		expectedException.expect(InvalidTradeException.class);
		expectedException
				.expectMessage("Invalid quantity of shares value for Trade.");

		tradeValidator.validate(trade);
	}

	@Test
	public void testValidateForNegativeQuantityOfShares() {

		trade.setQuantityOfShares(-1);

		expectedException.expect(InvalidTradeException.class);
		expectedException
				.expectMessage("Invalid quantity of shares value for Trade.");

		tradeValidator.validate(trade);
	}

	@Test
	public void testValidateForZeroTradedPrice() {

		trade.setTradedPrice(0.0);

		expectedException.expect(InvalidTradeException.class);
		expectedException.expectMessage("Invalid traded price for Trade.");

		tradeValidator.validate(trade);
	}

	@Test
	public void testValidateForNegativeTradedPrice() {

		trade.setTradedPrice(-0.01);

		expectedException.expect(InvalidTradeException.class);
		expectedException.expectMessage("Invalid traded price for Trade.");

		tradeValidator.validate(trade);
	}

	@Test
	public void testValidateForSuccess() {

		tradeValidator.validate(trade);
	}
}
