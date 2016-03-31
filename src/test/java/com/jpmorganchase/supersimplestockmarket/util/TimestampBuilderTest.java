package com.jpmorganchase.supersimplestockmarket.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.jpmorganchase.supersimplestockmarket.util.TimestampBuilder;

public class TimestampBuilderTest {
	
	private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public void testBuildTimestampForDateOnly() {
		assertDateTime("2016-01-01 00:00:00.000", TimestampBuilder.buildTimestamp(1, 1, 2016));
	}
	
	@Test
	public void testBuildTimestampForDateAndTime() {
		assertDateTime("2016-01-01 13:34:21.200", TimestampBuilder.buildTimestamp(1, 1, 2016, 13, 34, 21, 200));
	}
	
	
	@Test
	public void testBuildTimestampForLeapYear() {
		assertDateTime("2016-02-29 00:00:00.000", TimestampBuilder.buildTimestamp(29, 2, 2016));
	}
	
	@Test
	public void testBuildTimestampForNonLeapYear() {
		assertDateTime("2015-03-01 00:00:00.000", TimestampBuilder.buildTimestamp(29, 2, 2015));
	}
	
	@Test
	public void testDefault() {
		assertDateTime("1970-01-01 00:00:00.000", new TimestampBuilder().buildTimestamp());
	}
	
	@Test
	public void testSetYear() {
		assertDateTime("2000-01-01 00:00:00.000", new TimestampBuilder().setYear(2000).buildTimestamp());
	}
	
	@Test
	public void testSetMonthAsZero() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("Month must be between 1 and 12.");
		new TimestampBuilder().setMonth(0);
	}
	
	@Test
	public void testSetMonthAsOne() {
		assertDateTime("1970-01-01 00:00:00.000", new TimestampBuilder().setMonth(1).buildTimestamp());
	}
	
	@Test
	public void testSetMonthAsTwelve() {
		assertDateTime("1970-12-01 00:00:00.000", new TimestampBuilder().setMonth(12).buildTimestamp());
	}
	
	@Test
	public void testSetMonthAsThirteen() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("Month must be between 1 and 12.");
		new TimestampBuilder().setMonth(13);
	}
	
	@Test
	public void testSetDay() {
		assertDateTime("1970-01-30 00:00:00.000", new TimestampBuilder().setDay(30).buildTimestamp());
	}
	
	@Test
	public void testSetHour() {
		assertDateTime("1970-01-01 13:00:00.000", new TimestampBuilder().setHour(13).buildTimestamp());
	}
	
	@Test
	public void testSetMinutes() {
		assertDateTime("1970-01-01 00:59:00.000", new TimestampBuilder().setMinutes(59).buildTimestamp());
	}
	
	@Test
	public void testSetSeconds() {
		assertDateTime("1970-01-01 00:00:59.000", new TimestampBuilder().setSeconds(59).buildTimestamp());
	}
	
	@Test
	public void testSetMilliseconds() {
		assertDateTime("1970-01-01 00:00:00.999", new TimestampBuilder().setMilliseconds(999).buildTimestamp());
	}
	
	private void assertDateTime(final String expectedDateTime, final Date actualDate) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_TIME_FORMAT);
		assertEquals(expectedDateTime, dateFormat.format(actualDate));
	}
}
