package lhc.constants;

import java.util.ArrayList;
import java.util.List;

import lhc.enums.SX;

public class Sx3qNums {
	public static final String[] FDS = {"W1", "W2", "W3"};
	
	public static final List<SX> W1 = new ArrayList<SX>();
	public static final List<SX> W2 = new ArrayList<SX>();
	public static final List<SX> W3 = new ArrayList<SX>();
	static {
		W1.add(SX.Shu);
		W1.add(SX.Tu);
		W1.add(SX.Ma);
		W1.add(SX.Ji);
		
		W2.add(SX.Niu);
		W2.add(SX.Long);
		W2.add(SX.Yang);
		W2.add(SX.Gou);
		
		W3.add(SX.Hu);
		W3.add(SX.She);
		W3.add(SX.Hou);
		W3.add(SX.Zhu);
	}
}
