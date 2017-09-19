package lhc.constants;

import java.util.ArrayList;
import java.util.List;

public class HmSLDsNums {
	public static final String[] FDS = { "SmallOdd", "SmallEven", "LargeOdd", "LargeEven" };

	public static final List<Integer> SMALLODD = new ArrayList<Integer>();
	public static final List<Integer> SMALLEVEN = new ArrayList<Integer>();
	public static final List<Integer> LARGEODD = new ArrayList<Integer>();
	public static final List<Integer> LARGEEVEN = new ArrayList<Integer>();
	static {
		for (int i = 1; i < 26; i++) {
			if (i % 2 != 0) {
				SMALLODD.add(i);
			}
		}
		for (int i = 1; i < 26; i++) {
			if (i % 2 == 0) {
				SMALLEVEN.add(i);
			}
		}
		for (int i = 26; i < 50; i++) {
			if (i % 2 != 0) {
				LARGEODD.add(i);
			}
		}
		for (int i = 26; i < 50; i++) {
			if (i % 2 == 0) {
				LARGEEVEN.add(i);
			}
		}
	}
}
