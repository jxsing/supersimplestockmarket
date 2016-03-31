package com.jpmorganchase.supersimplestockmarket.models;

import java.io.Serializable;
import java.util.Date;

import com.jpmorganchase.supersimplestockmarket.enums.TradeAction;

/**
 * Class that represents a trade made against a Stock.
 * 
 * @author Jat
 *
 */
public class Trade implements Serializable, Comparable<Trade> {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 6355672145866227218L;
	private Date timestamp;
	private Stock stock;
	private int quantityOfShares;
	private TradeAction action;
	private double tradedPrice;
	
	public Trade() {
	}
	
	public Trade(Date timestamp, Stock stock, int quantityOfShares, TradeAction action, double tradedPrice) {
		this.timestamp = timestamp;
		this.stock = stock;
		this.quantityOfShares = quantityOfShares;
		this.action = action;
		this.tradedPrice = tradedPrice;
	}
	
	/**
	 * Get timestamp of when this trade occured.
	 * 
	 * @return the timestamp.
	 */
	public Date getTimestamp() {
		return timestamp;
	}
	
	/**
	 * Set time stamp for when this trade occured.
	 * @param timestamp the time stamp to set.
	 */
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * Get stock that is being traded.
	 * 
	 * @return the stock that is being traded.
	 */
	public Stock getStock() {
		return stock;
	}

	/**
	 * Set stock that is being traded.
	 * 
	 * @param stock the stock to set.
	 */
	public void setStock(Stock stock) {
		this.stock = stock;
	}

	/**
	 * Quantity of shares involved in this trade.
	 * 
	 * @return the quantity of shares.
	 */
	public int getQuantityOfShares() {
		return quantityOfShares;
	}
	
	/**
	 * Set the quantity of shares involved in this trade.
	 * 
	 * @param quantityOfShares the quantity of shares to set.
	 */
	public void setQuantityOfShares(int quantityOfShares) {
		this.quantityOfShares = quantityOfShares;
	}
	
	/**
	 * Get the trade action i.e. BUY or SELL.
	 * 
	 * @return get the trade action.
	 */
	public TradeAction getAction() {
		return action;
	}
	
	/**
	 * Set the trade action i.e. BUY or SELL.
	 * 
	 * @param action the action to set.
	 */
	public void setAction(TradeAction action) {
		this.action = action;
	}
	
	/**
	 * Get traded price that stock was sold or bought for 
	 * as part of this trade.
	 * 
	 * @return the traded price.
	 */
	public double getTradedPrice() {
		return tradedPrice;
	}
	
	/**
	 * Set traded price that stock was sold/bought for.
	 * @param tradedPrice the traded price.
	 */
	public void setTradedPrice(double tradedPrice) {
		this.tradedPrice = tradedPrice;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + quantityOfShares;
		result = prime * result + ((stock == null) ? 0 : stock.hashCode());
		result = prime * result
				+ ((timestamp == null) ? 0 : timestamp.hashCode());
		long temp;
		temp = Double.doubleToLongBits(tradedPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Trade other = (Trade) obj;
		if (action != other.action)
			return false;
		if (quantityOfShares != other.quantityOfShares)
			return false;
		if (stock == null) {
			if (other.stock != null)
				return false;
		} else if (!stock.equals(other.stock))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		if (Double.doubleToLongBits(tradedPrice) != Double
				.doubleToLongBits(other.tradedPrice))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Trade [timestamp=" + timestamp + ", stock=" + stock
				+ ", quantityOfShares=" + quantityOfShares + ", action="
				+ action + ", tradedPrice=" + tradedPrice + "]";
	}

	public int compareTo(Trade o) {
		
		// default sorting logic sorts Trade in ascending order by timestamp.
		return timestamp.compareTo(o.getTimestamp());
	}
}
