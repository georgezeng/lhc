package lhc.constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SwNums {
	public static final String[] FDS = { "W0", "W1", "W2", "W3", "W4" };
	public static final Map<String, String> TXT_MAP = new HashMap<String, String>();
	static {
		TXT_MAP.put(FDS[0], "位0");
		TXT_MAP.put(FDS[1], "位1");
		TXT_MAP.put(FDS[2], "位2");
		TXT_MAP.put(FDS[3], "位3");
		TXT_MAP.put(FDS[4], "位4");
	}
	
	public static final List<Integer> W0 = new ArrayList<Integer>();
	public static final List<Integer> W1 = new ArrayList<Integer>();
	public static final List<Integer> W2 = new ArrayList<Integer>();
	public static final List<Integer> W3 = new ArrayList<Integer>();
	public static final List<Integer> W4 = new ArrayList<Integer>();
	
	@SuppressWarnings("unchecked")
	public static final List<Integer>[] NUMS = new List[5];
	static {
		NUMS[0] = W0;
		NUMS[1] = W1;
		NUMS[2] = W2;
		NUMS[3] = W3;
		NUMS[4] = W4;
		
		W0.add(1);
		W0.add(2);
		W0.add(3);
		W0.add(4);
		W0.add(5);
		W0.add(6);
		W0.add(7);
		W0.add(8);
		W0.add(9);

		W1.add(10);
		W1.add(11);
		W1.add(12);
		W1.add(13);
		W1.add(14);
		W1.add(15);
		W1.add(16);
		W1.add(17);
		W1.add(18);
		W1.add(19);

		W2.add(20);
		W2.add(21);
		W2.add(22);
		W2.add(23);
		W2.add(24);
		W2.add(25);
		W2.add(26);
		W2.add(27);
		W2.add(28);
		W2.add(29);

		W3.add(30);
		W3.add(31);
		W3.add(32);
		W3.add(33);
		W3.add(34);
		W3.add(35);
		W3.add(36);
		W3.add(37);
		W3.add(38);
		W3.add(39);

		W4.add(40);
		W4.add(41);
		W4.add(42);
		W4.add(43);
		W4.add(44);
		W4.add(45);
		W4.add(46);
		W4.add(47);
		W4.add(48);
		W4.add(49);
	}
}
