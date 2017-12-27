package lhc.constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Zx17Nums {
	public static final String[] FDS = { "W1", "W2", "W3", "W4", "W5", "W6", "W7", "W8", "W9", "W10", "W11", "W12", "W13",
			"W14", "W15", "W16" };
	public static final Map<String, String> TXT_MAP = new HashMap<String, String>();
	static {
		TXT_MAP.put(FDS[0], "位1");
		TXT_MAP.put(FDS[1], "位2");
		TXT_MAP.put(FDS[2], "位3");
		TXT_MAP.put(FDS[3], "位4");
		TXT_MAP.put(FDS[4], "位5");
		TXT_MAP.put(FDS[5], "位6");
		TXT_MAP.put(FDS[6], "位7");
		TXT_MAP.put(FDS[7], "位8");
		TXT_MAP.put(FDS[8], "位9");
		TXT_MAP.put(FDS[9], "位10");
		TXT_MAP.put(FDS[10], "位11");
		TXT_MAP.put(FDS[11], "位12");
		TXT_MAP.put(FDS[12], "位13");
		TXT_MAP.put(FDS[13], "位14");
		TXT_MAP.put(FDS[14], "位15");
		TXT_MAP.put(FDS[15], "位16");
	}

	public static final List<Integer> W1 = new ArrayList<Integer>();
	public static final List<Integer> W2 = new ArrayList<Integer>();
	public static final List<Integer> W3 = new ArrayList<Integer>();
	public static final List<Integer> W4 = new ArrayList<Integer>();
	public static final List<Integer> W5 = new ArrayList<Integer>();
	public static final List<Integer> W6 = new ArrayList<Integer>();
	public static final List<Integer> W7 = new ArrayList<Integer>();
	public static final List<Integer> W8 = new ArrayList<Integer>();
	public static final List<Integer> W9 = new ArrayList<Integer>();
	public static final List<Integer> W10 = new ArrayList<Integer>();
	public static final List<Integer> W11 = new ArrayList<Integer>();
	public static final List<Integer> W12 = new ArrayList<Integer>();
	public static final List<Integer> W13 = new ArrayList<Integer>();
	public static final List<Integer> W14 = new ArrayList<Integer>();
	public static final List<Integer> W15 = new ArrayList<Integer>();
	public static final List<Integer> W16 = new ArrayList<Integer>();

	@SuppressWarnings("unchecked")
	public static final List<Integer>[] NUMS = new List[16];
	static {
		NUMS[0] = W1;
		NUMS[1] = W2;
		NUMS[2] = W3;
		NUMS[3] = W4;
		NUMS[4] = W5;
		NUMS[5] = W6;
		NUMS[6] = W7;
		NUMS[7] = W8;
		NUMS[8] = W9;
		NUMS[9] = W10;
		NUMS[10] = W11;
		NUMS[11] = W12;
		NUMS[12] = W13;
		NUMS[13] = W14;
		NUMS[14] = W15;
		NUMS[15] = W16;

		W1.add(1);
		W1.add(17);
		W1.add(33);
		W1.add(49);

		W2.add(9);
		W2.add(25);
		W2.add(41);

		W3.add(2);
		W3.add(18);
		W3.add(34);

		W4.add(10);
		W4.add(26);
		W4.add(42);

		W5.add(3);
		W5.add(19);
		W5.add(35);

		W6.add(11);
		W6.add(27);
		W6.add(43);

		W7.add(4);
		W7.add(20);
		W7.add(36);

		W8.add(12);
		W8.add(28);
		W8.add(44);

		W9.add(5);
		W9.add(21);
		W9.add(37);

		W10.add(13);
		W10.add(29);
		W10.add(45);

		W11.add(6);
		W11.add(22);
		W11.add(38);

		W12.add(14);
		W12.add(30);
		W12.add(46);

		W13.add(7);
		W13.add(23);
		W13.add(39);

		W14.add(15);
		W14.add(31);
		W14.add(47);

		W15.add(8);
		W15.add(24);
		W15.add(40);

		W16.add(16);
		W16.add(32);
		W16.add(48);
	}
}
