package lhc.constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Zx15Nums {
	public static final String[] FDS = {"W1", "W2", "W3", "W4", "W5", "W6", "W7", "W8", "W9", "W10", "W11", "W12"};
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
	
	@SuppressWarnings("unchecked")
	public static final List<Integer>[] NUMS = new List[12];
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
		
		W1.add(7);
		W1.add(24);
		W1.add(41);
		W1.add(31);
		
		W2.add(8);
		W2.add(25);
		W2.add(42);
		W2.add(32);
		W2.add(49);
		
		W3.add(9);
		W3.add(26);
		W3.add(43);
		W3.add(33);
		
		W4.add(1);
		W4.add(18);
		W4.add(35);
		W4.add(13);
		
		W5.add(2);
		W5.add(19);
		W5.add(36);
		W5.add(14);
		
		W6.add(3);
		W6.add(20);
		W6.add(37);
		W6.add(15);
		
		W7.add(10);
		W7.add(27);
		W7.add(44);
		W7.add(34);
		
		W8.add(11);
		W8.add(28);
		W8.add(45);
		W8.add(47);
		
		W9.add(12);
		W9.add(29);
		W9.add(46);
		W9.add(48);
		
		W10.add(4);
		W10.add(21);
		W10.add(38);
		W10.add(16);
		
		W11.add(5);
		W11.add(22);
		W11.add(39);
		W11.add(17);
		
		W12.add(6);
		W12.add(23);
		W12.add(40);
		W12.add(30);
	}
}
