package com.feature.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;

import com.feature.log.Log;

public class DateUtil {

	public void localDateTest() {
		LocalDate history1 = LocalDate.of(1944, 5, 3);
		Log.log(history1);

		LocalDate history2 = LocalDate.of(1944, Month.MAY, 3);
		Log.log(history2);

		LocalDate now = LocalDate.now();
		Log.log(now);

		Log.log("Comp", now.isAfter(history1));

		LocalDate modifyDate = now.withMonth(3).withDayOfMonth(2);
		Log.log(modifyDate);

		modifyDate = now.with(ChronoField.YEAR, 1944);
		Log.log(modifyDate);
		modifyDate = modifyDate.with(ChronoField.MONTH_OF_YEAR, 5);
		Log.log(modifyDate);
		modifyDate = modifyDate.with(ChronoField.DAY_OF_MONTH, 3);
		Log.log(modifyDate);

		Log.log("------");
		modifyDate = now.plusYears(3);
		Log.log(modifyDate);

		modifyDate = now.minusYears(3);
		Log.log(modifyDate);
	}

	public void localTimeTest() {
		LocalTime localTime = LocalTime.now();
		Log.log("Now:", localTime);

		LocalTime localFixedTime = LocalTime.of(19, 11, 25);
		Log.log("Fixed:", localFixedTime);

		LocalTime localModifyTime = LocalTime.parse("19:11:25");
		Log.log("Modify:", localModifyTime);

		localModifyTime = localTime.plusSeconds(45);
		Log.log("Modify:", localModifyTime);

	}

	private void zoneIdTest() {
		// Set<String> zones = ZoneId.getAvailableZoneIds();
		// zones.stream().sorted().forEach(System.out::println);
		Log.log("Default ZoneId: ", ZoneId.systemDefault());

		ZoneId istanbul = ZoneId.of("Asia/Istanbul");
		Log.log(ZonedDateTime.now(istanbul));

		LocalDateTime localDateTime = ZonedDateTime.now(istanbul).toLocalDateTime();
		Log.log(localDateTime);

		Log.log(localDateTime.toLocalTime());

		Log.log(localDateTime.toLocalDate());

	}

	public static void main(String[] args) {
		DateUtil dateUtilTest = new DateUtil();
		// dateUtilTest.localDateTest();
		// dateUtilTest.localTimeTest();
		dateUtilTest.zoneIdTest();

	}

}
