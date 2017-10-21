package lhc.constants;

import java.util.ArrayList;
import java.util.List;

public class DsNums {
	public static final String[] FDS = { "Ds0Odd", "Ds0Even", "Ds1Odd", "Ds1Even", "Ds2Odd", "Ds2Even", "Ds3Odd",
			"Ds3Even", "Ds4Odd", "Ds4Even" };

	public static final List<Integer> DS0ODD = new ArrayList<Integer>();
	public static final List<Integer> DS0EVEN = new ArrayList<Integer>();
	public static final List<Integer> DS1ODD = new ArrayList<Integer>();
	public static final List<Integer> DS1EVEN = new ArrayList<Integer>();
	public static final List<Integer> DS2ODD = new ArrayList<Integer>();
	public static final List<Integer> DS2EVEN = new ArrayList<Integer>();
	public static final List<Integer> DS3ODD = new ArrayList<Integer>();
	public static final List<Integer> DS3EVEN = new ArrayList<Integer>();
	public static final List<Integer> DS4ODD = new ArrayList<Integer>();
	public static final List<Integer> DS4EVEN = new ArrayList<Integer>();
	
	@SuppressWarnings("unchecked")
	public static final List<Integer>[] NUMS = new List[10];
	static {
		NUMS[0] = DS0ODD;
		NUMS[1] = DS0EVEN;
		NUMS[2] = DS1ODD;
		NUMS[3] = DS1EVEN;
		NUMS[4] = DS2ODD;
		NUMS[5] = DS2EVEN;
		NUMS[6] = DS3ODD;
		NUMS[7] = DS3EVEN;
		NUMS[8] = DS4ODD;
		NUMS[9] = DS4EVEN;
		
		DS0ODD.add(1);
		DS0ODD.add(3);
		DS0ODD.add(5);
		DS0ODD.add(7);
		DS0ODD.add(9);

		DS0EVEN.add(2);
		DS0EVEN.add(4);
		DS0EVEN.add(6);
		DS0EVEN.add(8);

		DS1ODD.add(11);
		DS1ODD.add(13);
		DS1ODD.add(15);
		DS1ODD.add(17);
		DS1ODD.add(19);

		DS1EVEN.add(10);
		DS1EVEN.add(12);
		DS1EVEN.add(14);
		DS1EVEN.add(16);
		DS1EVEN.add(18);

		DS2ODD.add(21);
		DS2ODD.add(23);
		DS2ODD.add(25);
		DS2ODD.add(27);
		DS2ODD.add(29);

		DS2EVEN.add(20);
		DS2EVEN.add(22);
		DS2EVEN.add(24);
		DS2EVEN.add(26);
		DS2EVEN.add(28);

		DS3ODD.add(31);
		DS3ODD.add(33);
		DS3ODD.add(35);
		DS3ODD.add(37);
		DS3ODD.add(39);

		DS3EVEN.add(30);
		DS3EVEN.add(32);
		DS3EVEN.add(34);
		DS3EVEN.add(36);
		DS3EVEN.add(38);

		DS4ODD.add(41);
		DS4ODD.add(43);
		DS4ODD.add(45);
		DS4ODD.add(47);
		DS4ODD.add(49);

		DS4EVEN.add(40);
		DS4EVEN.add(42);
		DS4EVEN.add(44);
		DS4EVEN.add(46);
		DS4EVEN.add(48);
	}
}
