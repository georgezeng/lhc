package lhc.constants;

import java.util.ArrayList;
import java.util.List;

public class HmDxNums {
	public static final String[] FDS = { "Small", "Large" };

	public static final List<Integer> SMALL = new ArrayList<Integer>();
	public static final List<Integer> LARGE = new ArrayList<Integer>();
	static {
		for (int i = 1; i < 26; i++) {
			SMALL.add(i);
		}
		for (int i = 26; i < 50; i++) {
			LARGE.add(i);
		}
	}
}
