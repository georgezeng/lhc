package lhc.constants;

import java.util.ArrayList;
import java.util.List;

public class QiwNums {
	public static final String[] FDS = { "W1", "W2", "W3", "W4", "W5", "W6", "W7" };

	public static final List<Integer> W1 = new ArrayList<Integer>();
	public static final List<Integer> W2 = new ArrayList<Integer>();
	public static final List<Integer> W3 = new ArrayList<Integer>();
	public static final List<Integer> W4 = new ArrayList<Integer>();
	public static final List<Integer> W5 = new ArrayList<Integer>();
	public static final List<Integer> W6 = new ArrayList<Integer>();
	public static final List<Integer> W7 = new ArrayList<Integer>();
	@SuppressWarnings("unchecked")
	public static final List<Integer>[] NUMS = new List[7];
	static {
		NUMS[0] = W1;
		NUMS[1] = W2;
		NUMS[2] = W3;
		NUMS[3] = W4;
		NUMS[4] = W5;
		NUMS[5] = W6;
		NUMS[6] = W7;
		
		W1.add(1);
		W1.add(8);
		W1.add(15);
		W1.add(22);
		W1.add(29);
		W1.add(36);
		W1.add(43);

		W2.add(2);
		W2.add(9);
		W2.add(16);
		W2.add(23);
		W2.add(30);
		W2.add(37);
		W2.add(44);

		W3.add(3);
		W3.add(10);
		W3.add(17);
		W3.add(24);
		W3.add(31);
		W3.add(38);
		W3.add(45);

		W4.add(4);
		W4.add(11);
		W4.add(18);
		W4.add(25);
		W4.add(32);
		W4.add(39);
		W4.add(46);

		W5.add(5);
		W5.add(12);
		W5.add(19);
		W5.add(26);
		W5.add(33);
		W5.add(40);
		W5.add(47);

		W6.add(6);
		W6.add(13);
		W6.add(20);
		W6.add(27);
		W6.add(34);
		W6.add(41);
		W7.add(48);

		W7.add(7);
		W7.add(14);
		W7.add(21);
		W7.add(28);
		W7.add(35);
		W7.add(42);
		W7.add(49);

	}
}
