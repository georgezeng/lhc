package lhc.constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LhNums {
	public static final String[] FDS = {"W0", "W1", "W2", "W3", "W4", "W5", "W6", "W7", "W8", "W9"};
	public static final Map<String, String> TXT_MAP = new HashMap<String, String>();
	static {
		TXT_MAP.put(FDS[0], "位0");
		TXT_MAP.put(FDS[1], "位1");
		TXT_MAP.put(FDS[2], "位2");
		TXT_MAP.put(FDS[3], "位3");
		TXT_MAP.put(FDS[4], "位4");
		TXT_MAP.put(FDS[5], "位5");
		TXT_MAP.put(FDS[6], "位6");
		TXT_MAP.put(FDS[7], "位7");
		TXT_MAP.put(FDS[8], "位8");
		TXT_MAP.put(FDS[9], "位9");
	}
	
	public static final List<Integer> W0 = new ArrayList<Integer>();
	public static final List<Integer> W1 = new ArrayList<Integer>();
	public static final List<Integer> W2 = new ArrayList<Integer>();
	public static final List<Integer> W3 = new ArrayList<Integer>();
	public static final List<Integer> W4 = new ArrayList<Integer>();
	public static final List<Integer> W5 = new ArrayList<Integer>();
	public static final List<Integer> W6 = new ArrayList<Integer>();
	public static final List<Integer> W7 = new ArrayList<Integer>();
	public static final List<Integer> W8 = new ArrayList<Integer>();
	public static final List<Integer> W9 = new ArrayList<Integer>();
	@SuppressWarnings("unchecked")
	public static final List<Integer>[] NUMS = new List[10];
	static {
		NUMS[0] = W0;
		NUMS[1] = W1;
		NUMS[2] = W2;
		NUMS[3] = W3;
		NUMS[4] = W4;
		NUMS[5] = W5;
		NUMS[6] = W6;
		NUMS[7] = W7;
		NUMS[8] = W8;
		NUMS[9] = W9;
		
		W0.add(19);
		W0.add(28);
		W0.add(37);
		W0.add(46);
		
		W1.add(1);
		W1.add(10);
		W1.add(29);
		W1.add(38);
		W1.add(47);
		
		W2.add(2);
		W2.add(11);
		W2.add(20);
		W2.add(39);
		W2.add(48);
		
		W3.add(3);
		W3.add(12);
		W3.add(21);
		W3.add(30);
		W3.add(49);
		
		W4.add(4);
		W4.add(13);
		W4.add(22);
		W4.add(31);
		W4.add(40);
		
		W5.add(5);
		W5.add(14);
		W5.add(23);
		W5.add(32);
		W5.add(41);
		
		W6.add(6);
		W6.add(15);
		W6.add(24);
		W6.add(33);
		W6.add(42);
		
		W7.add(7);
		W7.add(16);
		W7.add(25);
		W7.add(34);
		W7.add(43);
		
		W8.add(8);
		W8.add(17);
		W8.add(26);
		W8.add(35);
		W8.add(44);
		
		W9.add(9);
		W9.add(18);
		W9.add(27);
		W9.add(36);
		W9.add(45);
	}
}
