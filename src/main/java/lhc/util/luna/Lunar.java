package lhc.util.luna;

public class Lunar {
	private boolean leap;
	private int day;
	private int month;
	private int year;

	public boolean isLeap() {
		return leap;
	}

	public void setLeap(boolean leap) {
		this.leap = leap;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
}
