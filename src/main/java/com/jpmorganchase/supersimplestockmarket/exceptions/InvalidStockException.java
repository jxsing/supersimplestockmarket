package com.jpmorganchase.supersimplestockmarket.exceptions;

public class InvalidStockException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1425255854086189919L;

	public InvalidStockException(String msg) {
		super(msg);
	}

}
