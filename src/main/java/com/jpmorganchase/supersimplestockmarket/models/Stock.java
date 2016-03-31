package com.jpmorganchase.supersimplestockmarket.models;

import java.io.Serializable;

import com.jpmorganchase.supersimplestockmarket.enums.StockType;

/**
 * Class which holds details for a Stock.
 * 
 * @author Jat
 *
 */
public class Stock implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5619495163482296727L;
	private String symbol;
	private StockType type;
	private long lastDividend;
	private Double fixedDividend;
	private long parValue;
	
	/**
	 * Default constructor.  Sets up an empty Stock object.
	 */
	public Stock() {
	}
	
	/**
	 * 
	 * @param symbol the symbol to set.
	 * @param type the stock type
	 * @param lastDividend the last dividend value
	 * @param fixedDividend the fixed dividend value, can be null.
	 * @param parValue the par value
	 */
	public Stock(String symbol, StockType type, long lastDividend, Double fixedDividend, long parValue) {
		this.symbol = symbol;
		this.type = type;
		this.lastDividend = lastDividend;
		this.fixedDividend = fixedDividend;
		this.parValue = parValue;
	}
	
	/**
	 * Get the symbol.
	 * 
	 * @return the symbol
	 */
	public String getSymbol() {
		return symbol;
	}
	
	/**
	 * Set the symbol.
	 * 
	 * @param symbol the symbol to set
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	/**
	 * Get type of stock.
	 * 
	 * @return type of stock.
	 */
	public StockType getType() {
		return type;
	}
	
	/**
	 * Set the type of stock.
	 * 
	 * @param type the type of stock to set.
	 */
	public void setType(StockType type) {
		this.type = type;
	}
	
	/**
	 * Get last dividend.
	 * 
	 * @return the last dividend value.
	 */
	public long getLastDividend() {
		return lastDividend;
	}
	
	/**
	 * Set last dividend.
	 * 
	 * @param lastDividend the last dividend to set.
	 */
	public void setLastDividend(long lastDividend) {
		this.lastDividend = lastDividend;
	}
	
	/**
	 * Get fixed dividend.
	 * 
	 * @return the fixed dividend value.
	 */
	public Double getFixedDividend() {
		return fixedDividend;
	}
	
	/**
	 * Set fixed dividend value.
	 * 
	 * @param fixedDividend the fixed dividend value to set.
	 */
	public void setFixedDividend(Double fixedDividend) {
		this.fixedDividend = fixedDividend;
	}
	
	/**
	 * Get par value.
	 * 
	 * @return the par value.
	 */
	public long getParValue() {
		return parValue;
	}
	
	/**
	 * Set the par value.
	 * 
	 * @param parValue the par value to set.
	 */
	public void setParValue(long parValue) {
		this.parValue = parValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((fixedDividend == null) ? 0 : fixedDividend.hashCode());
		result = prime * result + (int) (lastDividend ^ (lastDividend >>> 32));
		result = prime * result + (int) (parValue ^ (parValue >>> 32));
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stock other = (Stock) obj;
		if (fixedDividend == null) {
			if (other.fixedDividend != null)
				return false;
		} else if (!fixedDividend.equals(other.fixedDividend))
			return false;
		if (lastDividend != other.lastDividend)
			return false;
		if (parValue != other.parValue)
			return false;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Stock [symbol=" + symbol + ", type=" + type + ", lastDividend="
				+ lastDividend + ", fixedDividend=" + fixedDividend
				+ ", parValue=" + parValue + "]";
	}
}
