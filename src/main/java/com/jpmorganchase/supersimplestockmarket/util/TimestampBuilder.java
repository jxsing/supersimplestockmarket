package com.jpmorganchase.supersimplestockmarket.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Class to build a timestamp.
 * 
 * 
 * @author Jat
 *
 */
public class TimestampBuilder {

	private int day = 1;
	private int month = 0;
	private int year = 1970;
	private int hour = 0;
	private int minutes = 0;
	private int seconds = 0;
	private int milliseconds = 0;

	/**
	 * Set the day value.
	 * 
	 * @param day the day value.
	 * @return builder instance.
	 */
	public TimestampBuilder setDay(int day) {
		this.day = day;
		return this;
	}

	/**
	 * Set the month value.  This must be between 1 and 12 inclusive.
	 * 
	 * @param month the month to set.
	 * @return builder instance.
	 */
	public TimestampBuilder setMonth(int month) {
		
		if(month >= 1 && month <= 12) {
			// subtract one because months are zero-based.
			this.month = month - 1;
		} else {
			throw new IllegalArgumentException("Month must be between 1 and 12.");
		}
		return this;
	}

	/**
	 * Set the year value.
	 * 
	 * @param year the year value to set.
	 * @return builder instance.
	 */
	public TimestampBuilder setYear(int year) {
		this.year = year;
		return this;
	}

	/**
	 * Set the hour value in 24 hour clock.
	 * 
	 * @param hour the hours to set.
	 * @return builder instance
	 */
	public TimestampBuilder setHour(int hour) {
		this.hour = hour;
		return this;
	}

	/**
	 * Set the minutes value.
	 * 
	 * @param minutes the minutes value to set.
	 * @return builder instance.
	 */
	public TimestampBuilder setMinutes(int minutes) {
		this.minutes = minutes;
		return this;
	}

	/**
	 * Set the seconds value.
	 * 
	 * @param seconds the seconds value to set.
	 * @return builder instance.
	 */
	public TimestampBuilder setSeconds(int seconds) {
		this.seconds = seconds;
		return this;
	}

	/**
	 * Set milli-seconds value.
	 * 
	 * @param milliseconds the value to set in milli-seconds.
	 * 
	 * @return the builder instance.
	 */
	public TimestampBuilder setMilliseconds(int milliseconds) {
		this.milliseconds = milliseconds;
		return this;
	}

	/**
	 * Build timestamp from properties.
	 * 
	 * @return new Date object containing date and time.
	 */
	public Date buildTimestamp() {

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, day);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minutes);
		calendar.set(Calendar.SECOND, seconds);
		calendar.set(Calendar.MILLISECOND, milliseconds);

		return calendar.getTime();
	}

	/**
	 * Convenient class for setting date only.  Time will be defaulted to midnight.
	 * 
	 * @param day the day of month to set
	 * @param month the month value to set (1 - 12)
	 * @param year the year value
	 * @return date
	 */
	public static Date buildTimestamp(int day, int month, int year) {

		return new TimestampBuilder().setDay(day).setMonth(month).setYear(year)
				.buildTimestamp();
	}

	/**
	 * Convenient class for setting date and time.
	 * 
	 * @param day the day of month to set
	 * @param month the month value to set (1 - 12)
	 * @param year the year value to set
	 * @param hour the hour value to set
	 * @param minutes the minute value to set
	 * @param seconds the seconds value to set
	 * @param milliseconds the milliseconds value to set
	 * @return date
	 */
	public static Date buildTimestamp(int day, int month, int year, int hour,
			int minutes, int seconds, int milliseconds) {

		return new TimestampBuilder().setDay(day).setMonth(month).setYear(year)
				.setHour(hour).setMinutes(minutes).setSeconds(seconds)
				.setMilliseconds(milliseconds).buildTimestamp();
	}
}
