package lhc.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.google.common.base.Joiner;

@MappedSuperclass
public abstract class DsxMnYz extends BaseYz {
	private Integer specialNum;
	private String c1Nums;
	private String c2Nums;
	private String c3Nums;
	private String c4Nums;
	private String c5Nums;
	private String c6Nums;
	private String c7Nums;
	private String c8Nums;
	private String c9Nums;
	private String c10Nums;

	@Transient
	private List<Integer> c1 = new ArrayList<Integer>();
	@Transient
	private List<Integer> c2 = new ArrayList<Integer>();
	@Transient
	private List<Integer> c3 = new ArrayList<Integer>();
	@Transient
	private List<Integer> c4 = new ArrayList<Integer>();
	@Transient
	private List<Integer> c5 = new ArrayList<Integer>();
	@Transient
	private List<Integer> c6 = new ArrayList<Integer>();
	@Transient
	private List<Integer> c7 = new ArrayList<Integer>();
	@Transient
	private List<Integer> c8 = new ArrayList<Integer>();
	@Transient
	private List<Integer> c9 = new ArrayList<Integer>();
	@Transient
	private List<Integer> c10 = new ArrayList<Integer>();
	@Transient
	private List<Integer> c11 = new ArrayList<Integer>();

	public void reset() {
		specialNum = null;
		c1Nums = null;
		c2Nums = null;
		c3Nums = null;
		c4Nums = null;
		c5Nums = null;
		c6Nums = null;
		c7Nums = null;
		c8Nums = null;
		c9Nums = null;
		c10Nums = null;
	}

	public void clearAll() {
		c1.clear();
		c2.clear();
		c3.clear();
		c4.clear();
		c5.clear();
		c6.clear();
		c7.clear();
		c8.clear();
		c9.clear();
		c10.clear();
		c11.clear();
	}

	private String getJoinString(List<Integer> c) {
		String str = Joiner.on(",").join(c);
		return !str.isEmpty() ? str : null;
	}

	public void assemble() {
		c1Nums = getJoinString(c1);
		c2Nums = getJoinString(c2);
		c3Nums = getJoinString(c3);
		c4Nums = getJoinString(c4);
		c5Nums = getJoinString(c5);
		c6Nums = getJoinString(c6);
		c7Nums = getJoinString(c7);
		c8Nums = getJoinString(c8);
		c9Nums = getJoinString(c9);
		c10Nums = getJoinString(c10);
	}

	public List<Integer> getC1() {
		return c1;
	}

	public void setC1(List<Integer> c1) {
		this.c1 = c1;
	}

	public List<Integer> getC2() {
		return c2;
	}

	public void setC2(List<Integer> c2) {
		this.c2 = c2;
	}

	public List<Integer> getC3() {
		return c3;
	}

	public void setC3(List<Integer> c3) {
		this.c3 = c3;
	}

	public List<Integer> getC4() {
		return c4;
	}

	public void setC4(List<Integer> c4) {
		this.c4 = c4;
	}

	public List<Integer> getC5() {
		return c5;
	}

	public void setC5(List<Integer> c5) {
		this.c5 = c5;
	}

	public List<Integer> getC6() {
		return c6;
	}

	public void setC6(List<Integer> c6) {
		this.c6 = c6;
	}

	public List<Integer> getC7() {
		return c7;
	}

	public void setC7(List<Integer> c7) {
		this.c7 = c7;
	}

	public List<Integer> getC8() {
		return c8;
	}

	public void setC8(List<Integer> c8) {
		this.c8 = c8;
	}

	public List<Integer> getC9() {
		return c9;
	}

	public void setC9(List<Integer> c9) {
		this.c9 = c9;
	}

	public List<Integer> getC10() {
		return c10;
	}

	public void setC10(List<Integer> c10) {
		this.c10 = c10;
	}

	public List<Integer> getC11() {
		return c11;
	}

	public void setC11(List<Integer> c11) {
		this.c11 = c11;
	}

	public Integer getSpecialNum() {
		return specialNum;
	}

	public void setSpecialNum(Integer specialNum) {
		this.specialNum = specialNum;
	}

	public String getC1Nums() {
		return c1Nums;
	}

	public void setC1Nums(String c1Nums) {
		this.c1Nums = c1Nums;
	}

	public String getC2Nums() {
		return c2Nums;
	}

	public void setC2Nums(String c2Nums) {
		this.c2Nums = c2Nums;
	}

	public String getC3Nums() {
		return c3Nums;
	}

	public void setC3Nums(String c3Nums) {
		this.c3Nums = c3Nums;
	}

	public String getC4Nums() {
		return c4Nums;
	}

	public void setC4Nums(String c4Nums) {
		this.c4Nums = c4Nums;
	}

	public String getC5Nums() {
		return c5Nums;
	}

	public void setC5Nums(String c5Nums) {
		this.c5Nums = c5Nums;
	}

	public String getC6Nums() {
		return c6Nums;
	}

	public void setC6Nums(String c6Nums) {
		this.c6Nums = c6Nums;
	}

	public String getC7Nums() {
		return c7Nums;
	}

	public void setC7Nums(String c7Nums) {
		this.c7Nums = c7Nums;
	}

	public String getC8Nums() {
		return c8Nums;
	}

	public void setC8Nums(String c8Nums) {
		this.c8Nums = c8Nums;
	}

	public String getC9Nums() {
		return c9Nums;
	}

	public void setC9Nums(String c9Nums) {
		this.c9Nums = c9Nums;
	}

	public String getC10Nums() {
		return c10Nums;
	}

	public void setC10Nums(String c10Nums) {
		this.c10Nums = c10Nums;
	}
}
