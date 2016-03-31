package com.jpmorganchase.supersimplestockmarket.services.calculators.impl;

import static com.jpmorganchase.supersimplestockmarket.util.Constants.Stocks.ALE;
import static com.jpmorganchase.supersimplestockmarket.util.Constants.Stocks.GIN;
import static com.jpmorganchase.supersimplestockmarket.util.Constants.Stocks.JOE;
import static com.jpmorganchase.supersimplestockmarket.util.Constants.Stocks.POP;
import static com.jpmorganchase.supersimplestockmarket.util.Constants.Stocks.TEA;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.jpmorganchase.supersimplestockmarket.models.Stock;
import com.jpmorganchase.supersimplestockmarket.validators.StockValidator;

public class DividendYieldCalculatorImplTest {

	private static final double[] TEST_VALUES = { 25.00, 50.50, 123.45 };

	@InjectMocks
	private DividendYieldCalculatorImpl dividendYieldCalcualtorImpl = new DividendYieldCalculatorImpl();

	@Mock(answer = Answers.CALLS_REAL_METHODS)
	private StockValidator stockValidator;

	public DividendYieldCalculatorImplTest() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testStockTea() {

		double[] expectedValues = { 0.0, 0.0, 0.0 };

		testStock(TEA, expectedValues);

	}

	@Test
	public void testStockPop() {

		double[] expectedValues = { 0.32, 0.15, 0.06 };

		testStock(POP, expectedValues);

	}

	@Test
	public void testStockAle() {

		double[] expectedValues = { 0.92, 0.45, 0.18 };

		testStock(ALE, expectedValues);

	}

	@Test
	public void testStockGin() {

		double[] expectedValues = { 8.0, 3.96, 1.62 };

		testStock(GIN, expectedValues);

	}

	@Test
	public void testStockJoe() {

		double[] expectedValues = { 0.52, 0.25, 0.10 };

		testStock(JOE, expectedValues);

	}

	private void testStock(Stock stock, double[] expectedValues) {

		for (int i = 0; i < TEST_VALUES.length; i++) {

			assertEquals("Failed on test case " + i, expectedValues[i],
					dividendYieldCalcualtorImpl
							.calculate(stock, TEST_VALUES[i]), 0.0);
		}
	}
}
