package lhc.constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QqNums {
	public static final String[] FDS = { "W1", "W2", "W3", "W4", "W5", "W6", "W7" };
	public static final Map<String, String> TXT_MAP = new HashMap<String, String>();
	static {
		TXT_MAP.put(FDS[0], "位1");
		TXT_MAP.put(FDS[1], "位2");
		TXT_MAP.put(FDS[2], "位3");
		TXT_MAP.put(FDS[3], "位4");
		TXT_MAP.put(FDS[4], "位5");
		TXT_MAP.put(FDS[5], "位6");
		TXT_MAP.put(FDS[6], "位7");
	}

	public static final List<Integer> W1 = new ArrayList<Integer>();
	public static final List<Integer> W2 = new ArrayList<Integer>();
	public static final List<Integer> W3 = new ArrayList<Integer>();
	public static final List<Integer> W4 = new ArrayList<Integer>();
	public static final List<Integer> W5 = new ArrayList<Integer>();
	public static final List<Integer> W6 = new ArrayList<Integer>();
	public static final List<Integer> W7 = new ArrayList<Integer>();
	@SuppressWarnings("unchecked")
	public static final List<Integer>[] NUMS = new List[7];
	static {
		NUMS[0] = W1;
		NUMS[1] = W2;
		NUMS[2] = W3;
		NUMS[3] = W4;
		NUMS[4] = W5;
		NUMS[5] = W6;
		NUMS[6] = W7;
		
		W1.add(1);
		W1.add(2);
		W1.add(3);
		W1.add(4);
		W1.add(5);
		W1.add(6);
		W1.add(7);

		W2.add(8);
		W2.add(9);
		W2.add(10);
		W2.add(11);
		W2.add(12);
		W2.add(13);
		W2.add(14);

		W3.add(15);
		W3.add(16);
		W3.add(17);
		W3.add(18);
		W3.add(19);
		W3.add(20);
		W3.add(21);

		W4.add(22);
		W4.add(23);
		W4.add(24);
		W4.add(25);
		W4.add(26);
		W4.add(27);
		W4.add(28);

		W5.add(29);
		W5.add(30);
		W5.add(31);
		W5.add(32);
		W5.add(33);
		W5.add(34);
		W5.add(35);

		W6.add(36);
		W6.add(37);
		W6.add(38);
		W6.add(39);
		W6.add(40);
		W6.add(41);
		W6.add(42);

		W7.add(43);
		W7.add(44);
		W7.add(45);
		W7.add(46);
		W7.add(47);
		W7.add(48);
		W7.add(49);

	}
}
