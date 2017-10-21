package lhc.util;

import lhc.enums.SX;

public class DateUtil {
	public static SX getYear(Integer year) {
		if (year < 1900) {
			return null;
		}
		Integer start = 1900;
		SX[] years = SX.seq();
		return years[(year - start) % years.length];
	}

}
