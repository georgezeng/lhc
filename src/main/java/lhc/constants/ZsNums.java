package lhc.constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZsNums {
	public static final String[] FDS = { "Fd1", "Fd2", "Fd3", "Fd4", "Fd5", "Fd6", "Fd7", "Fd8", "Fd9" };
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
	}
	
	public static final List<Integer> FD1 = new ArrayList<Integer>();
	public static final List<Integer> FD2 = new ArrayList<Integer>();
	public static final List<Integer> FD3 = new ArrayList<Integer>();
	public static final List<Integer> FD4 = new ArrayList<Integer>();
	public static final List<Integer> FD5 = new ArrayList<Integer>();
	public static final List<Integer> FD6 = new ArrayList<Integer>();
	public static final List<Integer> FD7 = new ArrayList<Integer>();
	public static final List<Integer> FD8 = new ArrayList<Integer>();
	public static final List<Integer> FD9 = new ArrayList<Integer>();
	@SuppressWarnings("unchecked")
	public static final List<Integer>[] NUMS = new List[9];
	static {
		NUMS[0] = FD1;
		NUMS[1] = FD2;
		NUMS[2] = FD3;
		NUMS[3] = FD4;
		NUMS[4] = FD5;
		NUMS[5] = FD6;
		NUMS[6] = FD7;
		NUMS[7] = FD8;
		NUMS[8] = FD9;
		
		FD1.add(2);
		FD1.add(3);
		FD1.add(5);
		FD1.add(7);
		FD1.add(11);
		
		FD2.add(13);
		FD2.add(17);
		FD2.add(19);
		FD2.add(23);
		FD2.add(29);
		
		FD3.add(31);
		FD3.add(37);
		FD3.add(41);
		FD3.add(43);
		FD3.add(47);

		FD4.add(1);
		FD4.add(9);
		FD4.add(15);
		FD4.add(21);
		FD4.add(25);

		FD5.add(27);
		FD5.add(33);
		FD5.add(35);
		FD5.add(39);
		FD5.add(45);
		FD5.add(49);

		FD6.add(4);
		FD6.add(6);
		FD6.add(8);
		FD6.add(10);
		FD6.add(12);
		FD6.add(14);

		FD7.add(16);
		FD7.add(18);
		FD7.add(20);
		FD7.add(22);
		FD7.add(24);
		FD7.add(26);

		FD8.add(28);
		FD8.add(30);
		FD8.add(32);
		FD8.add(34);
		FD8.add(36);
		FD8.add(38);

		FD9.add(40);
		FD9.add(42);
		FD9.add(44);
		FD9.add(46);
		FD9.add(48);
	}
}
