package lhc.constants;

import java.util.ArrayList;
import java.util.List;

public class Zx9Nums {
	public static final String[] FDS = {"W1", "W2", "W3", "W4", "W5"};
	
	public static final List<Integer> W1 = new ArrayList<Integer>();
	public static final List<Integer> W2 = new ArrayList<Integer>();
	public static final List<Integer> W3 = new ArrayList<Integer>();
	public static final List<Integer> W4 = new ArrayList<Integer>();
	public static final List<Integer> W5 = new ArrayList<Integer>();
	@SuppressWarnings("unchecked")
	public static final List<Integer>[] NUMS = new List[5];
	static {
		NUMS[0] = W1;
		NUMS[1] = W2;
		NUMS[2] = W3;
		NUMS[3] = W4;
		NUMS[4] = W5;
		W1.add(1);
		W1.add(6);
		W1.add(15);
		W1.add(19);
		W1.add(21);
		W1.add(26);
		W1.add(35);
		W1.add(39);
		W1.add(41);
		W1.add(46);
		
		W2.add(2);
		W2.add(7);
		W2.add(14);
		W2.add(18);
		W2.add(22);
		W2.add(27);
		W2.add(34);
		W2.add(38);
		W2.add(42);
		W2.add(47);
		
		W3.add(3);
		W3.add(8);
		W3.add(13);
		W3.add(17);
		W3.add(23);
		W3.add(28);
		W3.add(33);
		W3.add(37);
		W3.add(43);
		W3.add(48);
		
		W4.add(4);
		W4.add(9);
		W4.add(12);
		W4.add(16);
		W4.add(24);
		W4.add(29);
		W4.add(32);
		W4.add(36);
		W4.add(44);
		W4.add(49);
		
		W5.add(5);
		W5.add(10);
		W5.add(11);
		W5.add(20);
		W5.add(25);
		W5.add(30);
		W5.add(31);
		W5.add(40);
		W5.add(45);
	}
}
