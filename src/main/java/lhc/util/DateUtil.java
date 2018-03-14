package lhc.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import lhc.enums.SX;
import lhc.util.luna.LunarCalendar;

public class DateUtil {
	public static SX getSxByYear(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return getSxByYear(sdf.parse(date));
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public static SX getSxByYear(Date date) {
//		Calendar c = Calendar.getInstance();
//		try {
//			c.setTime(date);
//		} catch (Exception e) {
//			throw new RuntimeException(e.getMessage(), e);
//		}
//		Lunar lunar = LunarSolarConverter
//				.SolarToLunar(new Solar(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE)));
//		int year = lunar.getYear();
//		if (year < 1900) {
//			return null;
//		}
		Integer start = 1900;
		SX[] years = SX.seq();
		return years[(new LunarCalendar().getYear() - start) % years.length];
	}

}
