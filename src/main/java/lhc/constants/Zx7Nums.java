package lhc.constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Zx7Nums {
	public static final String[] FDS = {"W1", "W2", "W3", "W4", "W5", "W6", "W7", "W8", "W9", "W10"};
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
	@SuppressWarnings("unchecked")
	public static final List<Integer>[] NUMS = new List[10];
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
		W1.add(1);
		W1.add(19);
		W1.add(21);
		W1.add(39);
		W1.add(40);
		
		W2.add(2);
		W2.add(18);
		W2.add(22);
		W2.add(38);
		W2.add(41);
		
		W3.add(3);
		W3.add(17);
		W3.add(23);
		W3.add(37);
		W3.add(42);
		
		W4.add(4);
		W4.add(16);
		W4.add(24);
		W4.add(36);
		W4.add(43);
		
		W5.add(5);
		W5.add(15);
		W5.add(25);
		W5.add(35);
		
		W6.add(6);
		W6.add(14);
		W6.add(26);
		W6.add(34);
		W6.add(46);
		
		W7.add(7);
		W7.add(13);
		W7.add(27);
		W7.add(33);
		W7.add(47);
		
		W8.add(8);
		W8.add(12);
		W8.add(28);
		W8.add(32);
		W8.add(48);
		
		W9.add(9);
		W9.add(11);
		W9.add(29);
		W9.add(31);
		W9.add(45);
		
		W10.add(10);
		W10.add(20);
		W10.add(30);
		W10.add(44);
		W10.add(49);
	}
}
