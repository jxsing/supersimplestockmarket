package com.jpmorganchase.supersimplestockmarket.exceptions;

public class InvalidTradeException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1726974693106625826L;

	public InvalidTradeException(String msg) {
		super(msg);
	}

}
