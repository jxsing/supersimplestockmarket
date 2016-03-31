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

public class PERatioCalculatorImplTest {

	private static final double[] TEST_VALUES = { 25.00, 50.50, 123.45 };

	@InjectMocks
	private PERatioCalculatorImpl peRatioCalculator = new PERatioCalculatorImpl();

	@Mock(answer = Answers.CALLS_REAL_METHODS)
	private StockValidator stockValidator;

	public PERatioCalculatorImplTest() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testStockTea() {

		double[] expectedValues = { Double.POSITIVE_INFINITY,
				Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY };

		testStock(TEA, expectedValues);

	}

	@Test
	public void testStockPop() {

		double[] expectedValues = { 3.12, 6.31, 15.43 };

		testStock(POP, expectedValues);

	}

	@Test
	public void testStockAle() {

		double[] expectedValues = { 1.08, 2.19, 5.36 };

		testStock(ALE, expectedValues);

	}

	@Test
	public void testStockGin() {

		double[] expectedValues = { 3.12, 6.31, 15.43 };

		testStock(GIN, expectedValues);

	}

	@Test
	public void testStockJoe() {

		double[] expectedValues = { 1.92, 3.88, 9.49 };

		testStock(JOE, expectedValues);

	}

	private void testStock(Stock stock, double[] expectedValues) {

		for (int i = 0; i < TEST_VALUES.length; i++) {

			assertEquals("Failed on test case " + i, expectedValues[i],
					peRatioCalculator.calculate(stock, TEST_VALUES[i]), 0.0);
		}
	}
}
