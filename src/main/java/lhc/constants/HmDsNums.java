package lhc.constants;

import java.util.ArrayList;
import java.util.List;

public class HmDsNums {
	public static final String[] FDS = { "Odd", "Even" };

	public static final List<Integer> ODD = new ArrayList<Integer>();
	public static final List<Integer> EVEN = new ArrayList<Integer>();
	static {
		for (int i = 1; i < 50; i++) {
			if (i % 2 != 0) {
				ODD.add(i);
			}
		}
		for (int i = 1; i < 50; i++) {
			if (i % 2 == 0) {
				EVEN.add(i);
			}
		}
	}
}
