package lhc.constants;

import java.util.ArrayList;
import java.util.List;

public class BsNums {
	public static final String[] FDS = {"RedOdd", "RedEven", "BlueOdd", "BlueEven", "GreenOdd", "GreenEven"};
	
	public static final List<Integer> REDODD = new ArrayList<Integer>();
	public static final List<Integer> REDEVEN = new ArrayList<Integer>();
	public static final List<Integer> GREENODD = new ArrayList<Integer>();
	public static final List<Integer> GREENEVEN = new ArrayList<Integer>();
	public static final List<Integer> BLUEODD = new ArrayList<Integer>();
	public static final List<Integer> BLUEEVEN = new ArrayList<Integer>();
	static {
		REDODD.add(1);
		REDODD.add(7);
		REDODD.add(13);
		REDODD.add(19);
		REDODD.add(23);
		REDODD.add(29);
		REDODD.add(35);
		REDODD.add(45);

		REDEVEN.add(2);
		REDEVEN.add(8);
		REDEVEN.add(12);
		REDEVEN.add(18);
		REDEVEN.add(24);
		REDEVEN.add(30);
		REDEVEN.add(34);
		REDEVEN.add(40);
		REDEVEN.add(46);

		BLUEODD.add(5);
		BLUEODD.add(11);
		BLUEODD.add(17);
		BLUEODD.add(21);
		BLUEODD.add(27);
		BLUEODD.add(33);
		BLUEODD.add(39);
		BLUEODD.add(43);
		BLUEODD.add(49);

		BLUEEVEN.add(6);
		BLUEEVEN.add(16);
		BLUEEVEN.add(22);
		BLUEEVEN.add(28);
		BLUEEVEN.add(32);
		BLUEEVEN.add(38);
		BLUEEVEN.add(44);

		GREENODD.add(3);
		GREENODD.add(9);
		GREENODD.add(15);
		GREENODD.add(25);
		GREENODD.add(31);
		GREENODD.add(37);
		GREENODD.add(41);
		GREENODD.add(47);

		GREENEVEN.add(4);
		GREENEVEN.add(10);
		GREENEVEN.add(14);
		GREENEVEN.add(20);
		GREENEVEN.add(26);
		GREENEVEN.add(36);
		GREENEVEN.add(42);
		GREENEVEN.add(48);

	}
}
