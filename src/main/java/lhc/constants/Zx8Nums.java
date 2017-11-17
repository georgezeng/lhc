package lhc.constants;

import java.util.ArrayList;
import java.util.List;

public class Zx8Nums {
	public static final String[] FDS = {"W1", "W2", "W3", "W4", "W5", "W6", "W7", "W8", "W9", "W10"};
	
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
		W1.add(2);
		W1.add(24);
		W1.add(26);
		W1.add(40);
		
		W2.add(3);
		W2.add(4);
		W2.add(23);
		W2.add(27);
		W2.add(41);
		
		W3.add(5);
		W3.add(6);
		W3.add(22);
		W3.add(20);
		W3.add(42);
		
		W4.add(7);
		W4.add(8);
		W4.add(21);
		W4.add(28);
		W4.add(43);
		
		W5.add(9);
		W5.add(10);
		W5.add(18);
		W5.add(16);
		W5.add(44);
		
		W6.add(11);
		W6.add(12);
		W6.add(19);
		W6.add(29);
		W6.add(45);
		
		W7.add(13);
		W7.add(14);
		W7.add(30);
		W7.add(38);
		W7.add(46);
		
		W8.add(15);
		W8.add(17);
		W8.add(31);
		W8.add(37);
		W8.add(47);
		
		W9.add(25);
		W9.add(32);
		W9.add(34);
		W9.add(48);
		W9.add(36);
		
		W10.add(33);
		W10.add(35);
		W10.add(39);
		W10.add(49);
	}
}
