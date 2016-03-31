package com.jpmorganchase.supersimplestockmarket.validators;

import static com.jpmorganchase.supersimplestockmarket.enums.StockType.COMMON;
import static com.jpmorganchase.supersimplestockmarket.enums.StockType.PREFERRED;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.jpmorganchase.supersimplestockmarket.enums.StockType;
import com.jpmorganchase.supersimplestockmarket.exceptions.InvalidStockException;
import com.jpmorganchase.supersimplestockmarket.models.Stock;

public class StockValidatorTest {

	private StockValidator stockValidator = new StockValidator();

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	private Stock stock;

	@Before
	public void onSetUp() {

		stock = new Stock();
		stock.setSymbol("TST");
		stock.setType(StockType.COMMON);
	}

	@Test
	public void testValidateForNull() {

		stock = null;

		expectedException.expect(InvalidStockException.class);
		expectedException.expectMessage("No stock set.");

		stockValidator.validate(stock);
	}

	@Test
	public void testValidateForNullStockSymbol() {

		stock.setSymbol(null);

		expectedException.expect(InvalidStockException.class);
		expectedException.expectMessage("Invalid stock symbol.");

		stockValidator.validate(stock);
	}

	@Test
	public void testValidateForEmptyStockSymbol() {

		stock.setSymbol("");

		expectedException.expect(InvalidStockException.class);
		expectedException.expectMessage("Invalid stock symbol.");

		stockValidator.validate(stock);
	}

	@Test
	public void testValidateForBlankStockSymbol() {

		stock.setSymbol("    ");

		expectedException.expect(InvalidStockException.class);
		expectedException.expectMessage("Invalid stock symbol.");

		stockValidator.validate(stock);
	}

	@Test
	public void testValidateForNullType() {
		
		stock.setType(null);

		expectedException.expect(InvalidStockException.class);
		expectedException
				.expectMessage("No stock type set for stock with symbol TST");

		stockValidator.validate(new Stock("TST", null, 0, null, 0));
	}

	@Test
	public void testValidateForCommonStock() {
		stockValidator.validate(new Stock("TST", COMMON, 0, null, 0));
	}

	@Test
	public void testValidateForPreferredStock() {
		stockValidator.validate(new Stock("TST", PREFERRED, 0, null, 0));
	}

}
