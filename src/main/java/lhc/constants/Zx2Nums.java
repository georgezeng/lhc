package lhc.constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Zx2Nums {
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
		W1.add(1);
		W1.add(47);
		W1.add(27);
		W1.add(23);
		
		W2.add(2);
		W2.add(46);
		W2.add(28);
		W2.add(22);
		
		W3.add(3);
		W3.add(45);
		W3.add(29);
		W3.add(21);
		
		W4.add(4);
		W4.add(44);
		W4.add(30);
		W4.add(20);
		
		W5.add(5);
		W5.add(43);
		W5.add(31);
		W5.add(19);
		
		W6.add(6);
		W6.add(42);
		W6.add(32);
		W6.add(18);
		
		W7.add(7);
		W7.add(41);
		W7.add(33);
		W7.add(17);
		
		W8.add(8);
		W8.add(40);
		W8.add(34);
		W8.add(16);
		
		W9.add(9);
		W9.add(39);
		W9.add(35);
		W9.add(15);
		
		W10.add(10);
		W10.add(38);
		W10.add(36);
		W10.add(14);
		
		W11.add(11);
		W11.add(37);
		W11.add(48);
		W11.add(26);
		
		W12.add(12);
		W12.add(49);
		W12.add(13);
		W12.add(25);
		W12.add(24);
	}
}
