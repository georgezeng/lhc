package lhc.constants;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class SxNums {
	public static final List<Integer> SX1 = new ArrayList<Integer>();
	public static final List<Integer> SX2 = new ArrayList<Integer>();
	public static final List<Integer> SX3 = new ArrayList<Integer>();
	public static final List<Integer> SX4 = new ArrayList<Integer>();
	public static final List<Integer> SX5 = new ArrayList<Integer>();
	public static final List<Integer> SX6 = new ArrayList<Integer>();
	public static final List<Integer> SX7 = new ArrayList<Integer>();
	public static final List<Integer> SX8 = new ArrayList<Integer>();
	public static final List<Integer> SX9 = new ArrayList<Integer>();
	public static final List<Integer> SX10 = new ArrayList<Integer>();
	public static final List<Integer> SX11 = new ArrayList<Integer>();
	public static final List<Integer> SX12 = new ArrayList<Integer>();

	public static final List<Integer>[] NUMS = new List[12];

	static {
		try {
			Class<SxNums> clazz = SxNums.class;
			for (int i = 0; i < 12; i++) {
				NUMS[i] = (List<Integer>) clazz.getDeclaredField("SX" + (i + 1)).get(null);
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}

		SX1.add(1);
		SX1.add(13);
		SX1.add(25);
		SX1.add(37);
		SX1.add(49);

		SX2.add(2);
		SX2.add(14);
		SX2.add(26);
		SX2.add(38);

		SX3.add(3);
		SX3.add(15);
		SX3.add(27);
		SX3.add(39);

		SX4.add(4);
		SX4.add(16);
		SX4.add(28);
		SX4.add(40);

		SX5.add(5);
		SX5.add(17);
		SX5.add(29);
		SX5.add(41);

		SX6.add(6);
		SX6.add(18);
		SX6.add(30);
		SX6.add(42);

		SX7.add(7);
		SX7.add(19);
		SX7.add(31);
		SX7.add(43);

		SX8.add(8);
		SX8.add(20);
		SX8.add(32);
		SX8.add(44);

		SX9.add(9);
		SX9.add(21);
		SX9.add(33);
		SX9.add(45);

		SX10.add(10);
		SX10.add(22);
		SX10.add(34);
		SX10.add(46);

		SX11.add(11);
		SX11.add(23);
		SX11.add(35);
		SX11.add(47);

		SX12.add(12);
		SX12.add(24);
		SX12.add(36);
		SX12.add(48);
	}
}
