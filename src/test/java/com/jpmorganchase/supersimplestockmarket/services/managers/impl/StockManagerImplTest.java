package com.jpmorganchase.supersimplestockmarket.services.managers.impl;

import static com.jpmorganchase.supersimplestockmarket.enums.StockType.COMMON;
import static com.jpmorganchase.supersimplestockmarket.util.Constants.Stocks.ALE;
import static com.jpmorganchase.supersimplestockmarket.util.Constants.Stocks.GIN;
import static com.jpmorganchase.supersimplestockmarket.util.Constants.Stocks.JOE;
import static com.jpmorganchase.supersimplestockmarket.util.Constants.Stocks.POP;
import static com.jpmorganchase.supersimplestockmarket.util.Constants.Stocks.TEA;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.jpmorganchase.supersimplestockmarket.models.Stock;
import com.jpmorganchase.supersimplestockmarket.validators.StockValidator;

public class StockManagerImplTest {

	@InjectMocks
	private StockManagerImpl stockManager = new StockManagerImpl();

	@Spy
	private StockValidator stockValidator = new StockValidator();

	public StockManagerImplTest() {
		MockitoAnnotations.initMocks(this);

		// Initialise stock manager with some stock data.
		stockManager.addStock(TEA);
		stockManager.addStock(POP);
		stockManager.addStock(ALE);
		stockManager.addStock(GIN);
		stockManager.addStock(JOE);
	}

	@Test
	public void testGetStockBySymbol() {

		assertEquals(TEA, stockManager.getStockBySymbol("TEA"));
		assertEquals(POP, stockManager.getStockBySymbol("POP"));
		assertEquals(ALE, stockManager.getStockBySymbol("ALE"));
		assertEquals(GIN, stockManager.getStockBySymbol("GIN"));
		assertEquals(JOE, stockManager.getStockBySymbol("JOE"));

		assertNull(stockManager.getStockBySymbol("ZZZ"));
	}

	@Test
	public void testGetAllStocks() {

		Collection<Stock> allStocks = stockManager.getAllStocks();

		assertTrue(allStocks.contains(TEA));
		assertTrue(allStocks.contains(POP));
		assertTrue(allStocks.contains(ALE));
		assertTrue(allStocks.contains(GIN));
		assertTrue(allStocks.contains(JOE));
		assertEquals(5, allStocks.size());
	}

	@Test
	public void testAddStock() {

		Stock newStock = new Stock("NEW", COMMON, 3, null, 97);

		Collection<Stock> allStocks = stockManager.getAllStocks();
		assertFalse(allStocks.contains(newStock));

		stockManager.addStock(newStock);

		allStocks = stockManager.getAllStocks();
		assertTrue(allStocks.contains(newStock));

	}
}
