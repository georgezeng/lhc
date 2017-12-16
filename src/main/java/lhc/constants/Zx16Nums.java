package lhc.constants;

import java.util.ArrayList;
import java.util.List;

public class Zx16Nums {
	public static final String[] FDS = {"W1", "W2", "W3", "W4", "W5", "W6", "W7", "W8", "W9", "W10", "W11", "W12"};
	
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
		
		W1.add(9);
		W1.add(27);
		W1.add(45);
		W1.add(15);
		
		W2.add(10);
		W2.add(28);
		W2.add(46);
		W2.add(16);
		
		W3.add(5);
		W3.add(23);
		W3.add(41);
		W3.add(35);
		
		W4.add(6);
		W4.add(24);
		W4.add(42);
		W4.add(36);
		
		W5.add(3);
		W5.add(21);
		W5.add(39);
		W5.add(33);
		
		W6.add(4);
		W6.add(22);
		W6.add(40);
		W6.add(34);
		
		W7.add(1);
		W7.add(19);
		W7.add(37);
		W7.add(31);
		
		W8.add(2);
		W8.add(20);
		W8.add(38);
		W8.add(32);
		
		W9.add(11);
		W9.add(29);
		W9.add(47);
		W9.add(17);
		
		W10.add(12);
		W10.add(30);
		W10.add(48);
		W10.add(18);
		
		W11.add(7);
		W11.add(25);
		W11.add(43);
		W11.add(13);
		
		W12.add(8);
		W12.add(26);
		W12.add(44);
		W12.add(14);
		W12.add(49);
	}
}
