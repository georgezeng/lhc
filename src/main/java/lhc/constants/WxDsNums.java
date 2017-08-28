package lhc.constants;

import java.util.ArrayList;
import java.util.List;

public class WxDsNums {
	public static final String[] FDS = { "JinOdd", "JinEven", "MuOdd", "MuEven", "ShuiOdd", "ShuiEven", "HuoOdd",
			"HuoEven", "TuOdd", "TuEven" };

	public static final List<Integer> JINODD = new ArrayList<Integer>();
	public static final List<Integer> JINEVEN = new ArrayList<Integer>();
	public static final List<Integer> MUODD = new ArrayList<Integer>();
	public static final List<Integer> MUEVEN = new ArrayList<Integer>();
	public static final List<Integer> SHUIODD = new ArrayList<Integer>();
	public static final List<Integer> SHUIEVEN = new ArrayList<Integer>();
	public static final List<Integer> HUOODD = new ArrayList<Integer>();
	public static final List<Integer> HUOEVEN = new ArrayList<Integer>();
	public static final List<Integer> TUODD = new ArrayList<Integer>();
	public static final List<Integer> TUEVEN = new ArrayList<Integer>();
	static {
		JINODD.add(5);
		JINODD.add(13);
		JINODD.add(21);
		JINODD.add(35);
		JINODD.add(43);

		JINEVEN.add(6);
		JINEVEN.add(14);
		JINEVEN.add(22);
		JINEVEN.add(36);
		JINEVEN.add(44);

		MUODD.add(3);
		MUODD.add(17);
		MUODD.add(25);
		MUODD.add(33);
		MUODD.add(47);

		MUEVEN.add(4);
		MUEVEN.add(18);
		MUEVEN.add(26);
		MUEVEN.add(34);
		MUEVEN.add(48);

		SHUIEVEN.add(2);
		SHUIEVEN.add(10);
		SHUIEVEN.add(24);
		SHUIEVEN.add(32);
		SHUIEVEN.add(40);

		SHUIODD.add(9);
		SHUIODD.add(23);
		SHUIODD.add(31);
		SHUIODD.add(39);

		HUOODD.add(1);
		HUOODD.add(11);
		HUOODD.add(19);
		HUOODD.add(27);
		HUOODD.add(41);
		HUOODD.add(49);

		HUOEVEN.add(12);
		HUOEVEN.add(20);
		HUOEVEN.add(28);
		HUOEVEN.add(42);

		TUODD.add(7);
		TUODD.add(15);
		TUODD.add(29);
		TUODD.add(37);
		TUODD.add(45);

		TUEVEN.add(8);
		TUEVEN.add(16);
		TUEVEN.add(30);
		TUEVEN.add(38);
		TUEVEN.add(46);

	}
}
