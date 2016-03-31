package com.jpmorganchase.supersimplestockmarket.enums;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TradeActionTest {
	
	@Test
	public void testTradeAction() {
		
		TradeAction[] actions = TradeAction.values();
		assertEquals(2, actions.length);
		assertEquals(TradeAction.BUY, actions[0]);
		assertEquals(TradeAction.SELL, actions[1]);
	}

}
