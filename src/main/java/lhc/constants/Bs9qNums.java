package lhc.constants;

import java.util.ArrayList;
import java.util.List;

public class Bs9qNums {
	public static final String[] FDS = {"Red1", "Red2", "Red3", "Blue1", "Blue2", "Blue3", "Green1", "Green2", "Green3"};
	
	public static final List<Integer> RED1 = new ArrayList<Integer>();
	public static final List<Integer> RED2 = new ArrayList<Integer>();
	public static final List<Integer> RED3 = new ArrayList<Integer>();
	public static final List<Integer> BLUE1 = new ArrayList<Integer>();
	public static final List<Integer> BLUE2 = new ArrayList<Integer>();
	public static final List<Integer> BLUE3 = new ArrayList<Integer>();
	public static final List<Integer> GREEN1 = new ArrayList<Integer>();
	public static final List<Integer> GREEN2 = new ArrayList<Integer>();
	public static final List<Integer> GREEN3 = new ArrayList<Integer>();
	@SuppressWarnings("unchecked")
	public static final List<Integer>[] NUMS = new List[9];
	static {
		NUMS[0] = RED1;
		NUMS[1] = RED2;
		NUMS[2] = RED3;
		NUMS[3] = BLUE1;
		NUMS[4] = BLUE2;
		NUMS[5] = BLUE3;
		NUMS[6] = GREEN1;
		NUMS[7] = GREEN2;
		NUMS[8] = GREEN3;
		
		RED1.add(1);
		RED1.add(2);
		RED1.add(7);
		RED1.add(8);
		RED1.add(12);
		RED1.add(13);
		
		RED2.add(18);
		RED2.add(19);
		RED2.add(23);
		RED2.add(24);
		RED2.add(29);
		RED2.add(30);
		
		RED3.add(34);
		RED3.add(35);
		RED3.add(40);
		RED3.add(45);
		RED3.add(46);
		
		BLUE1.add(5);
		BLUE1.add(6);
		BLUE1.add(11);
		BLUE1.add(16);
		BLUE1.add(17);
		
		BLUE2.add(21);
		BLUE2.add(22);
		BLUE2.add(27);
		BLUE2.add(28);
		BLUE2.add(32);
		
		BLUE3.add(33);
		BLUE3.add(38);
		BLUE3.add(39);
		BLUE3.add(43);
		BLUE3.add(44);
		BLUE3.add(49);
		
		GREEN1.add(3);
		GREEN1.add(4);
		GREEN1.add(9);
		GREEN1.add(10);
		GREEN1.add(14);
		
		GREEN2.add(15);
		GREEN2.add(20);
		GREEN2.add(25);
		GREEN2.add(26);
		GREEN2.add(31);
		
		GREEN3.add(36);
		GREEN3.add(37);
		GREEN3.add(41);
		GREEN3.add(42);
		GREEN3.add(47);
		GREEN3.add(48);
	}
}
