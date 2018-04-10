package lhc.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
		Integer start = 1900;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		SX[] years = SX.seq();
		return years[(new LunarCalendar(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), false, false).getYear() - start)
				% years.length];
	}

}
