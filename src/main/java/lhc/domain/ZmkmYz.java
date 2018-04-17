package lhc.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.common.base.Joiner;

@Entity
@Table(name = "zmkm_yz")
public class ZmkmYz extends BaseYz {
	private Integer specialNum;
	private Integer c1yz;
	private String c1Nums;
	private Integer c2yz;
	private String c2Nums;
	private Integer c3yz;
	private String c3Nums;
	private Integer c4yz;
	private String c4Nums;
	private Integer c5yz;
	private String c5Nums;
	private Integer c6yz;
	private String c6Nums;
	private Integer c7yz;
	private String c7Nums;
	private Integer c8yz;
	private String c8Nums;
	private Integer c9yz;
	private String c9Nums;
	private Integer c10yz;
	private String c10Nums;
	private Integer c11yz;
	private String c11Nums;
	private Integer c12yz;
	private String c12Nums;
	private Integer c13yz;
	private String c13Nums;
	private Integer c14yz;
	private String c14Nums;
	private Integer c15yz;
	private String c15Nums;

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
	@Transient
	private List<Integer> c12 = new ArrayList<Integer>();
	@Transient
	private List<Integer> c13 = new ArrayList<Integer>();
	@Transient
	private List<Integer> c14 = new ArrayList<Integer>();
	@Transient
	private List<Integer> c15 = new ArrayList<Integer>();

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
		c11Nums = null;
		c12Nums = null;
		c13Nums = null;
		c14Nums = null;
		c15Nums = null;
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
		c12.clear();
		c13.clear();
		c14.clear();
		c15.clear();
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
		c11Nums = getJoinString(c11);
		c12Nums = getJoinString(c12);
		c13Nums = getJoinString(c13);
		c14Nums = getJoinString(c14);
		c15Nums = getJoinString(c15);
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

	public List<Integer> getC12() {
		return c12;
	}

	public void setC12(List<Integer> c12) {
		this.c12 = c12;
	}

	public List<Integer> getC13() {
		return c13;
	}

	public void setC13(List<Integer> c13) {
		this.c13 = c13;
	}

	public List<Integer> getC14() {
		return c14;
	}

	public void setC14(List<Integer> c14) {
		this.c14 = c14;
	}

	public List<Integer> getC15() {
		return c15;
	}

	public void setC15(List<Integer> c15) {
		this.c15 = c15;
	}

	public Integer getSpecialNum() {
		return specialNum;
	}

	public void setSpecialNum(Integer specialNum) {
		this.specialNum = specialNum;
	}

	public Integer getC1yz() {
		return c1yz;
	}

	public void setC1yz(Integer c1yz) {
		this.c1yz = c1yz;
	}

	public String getC1Nums() {
		return c1Nums;
	}

	public void setC1Nums(String c1Nums) {
		this.c1Nums = c1Nums;
	}

	public Integer getC2yz() {
		return c2yz;
	}

	public void setC2yz(Integer c2yz) {
		this.c2yz = c2yz;
	}

	public String getC2Nums() {
		return c2Nums;
	}

	public void setC2Nums(String c2Nums) {
		this.c2Nums = c2Nums;
	}

	public Integer getC3yz() {
		return c3yz;
	}

	public void setC3yz(Integer c3yz) {
		this.c3yz = c3yz;
	}

	public String getC3Nums() {
		return c3Nums;
	}

	public void setC3Nums(String c3Nums) {
		this.c3Nums = c3Nums;
	}

	public Integer getC4yz() {
		return c4yz;
	}

	public void setC4yz(Integer c4yz) {
		this.c4yz = c4yz;
	}

	public String getC4Nums() {
		return c4Nums;
	}

	public void setC4Nums(String c4Nums) {
		this.c4Nums = c4Nums;
	}

	public Integer getC5yz() {
		return c5yz;
	}

	public void setC5yz(Integer c5yz) {
		this.c5yz = c5yz;
	}

	public String getC5Nums() {
		return c5Nums;
	}

	public void setC5Nums(String c5Nums) {
		this.c5Nums = c5Nums;
	}

	public Integer getC6yz() {
		return c6yz;
	}

	public void setC6yz(Integer c6yz) {
		this.c6yz = c6yz;
	}

	public String getC6Nums() {
		return c6Nums;
	}

	public void setC6Nums(String c6Nums) {
		this.c6Nums = c6Nums;
	}

	public Integer getC7yz() {
		return c7yz;
	}

	public void setC7yz(Integer c7yz) {
		this.c7yz = c7yz;
	}

	public String getC7Nums() {
		return c7Nums;
	}

	public void setC7Nums(String c7Nums) {
		this.c7Nums = c7Nums;
	}

	public Integer getC8yz() {
		return c8yz;
	}

	public void setC8yz(Integer c8yz) {
		this.c8yz = c8yz;
	}

	public String getC8Nums() {
		return c8Nums;
	}

	public void setC8Nums(String c8Nums) {
		this.c8Nums = c8Nums;
	}

	public Integer getC9yz() {
		return c9yz;
	}

	public void setC9yz(Integer c9yz) {
		this.c9yz = c9yz;
	}

	public String getC9Nums() {
		return c9Nums;
	}

	public void setC9Nums(String c9Nums) {
		this.c9Nums = c9Nums;
	}

	public Integer getC10yz() {
		return c10yz;
	}

	public void setC10yz(Integer c10yz) {
		this.c10yz = c10yz;
	}

	public String getC10Nums() {
		return c10Nums;
	}

	public void setC10Nums(String c10Nums) {
		this.c10Nums = c10Nums;
	}

	public Integer getC11yz() {
		return c11yz;
	}

	public void setC11yz(Integer c11yz) {
		this.c11yz = c11yz;
	}

	public String getC11Nums() {
		return c11Nums;
	}

	public void setC11Nums(String c11Nums) {
		this.c11Nums = c11Nums;
	}

	public Integer getC12yz() {
		return c12yz;
	}

	public void setC12yz(Integer c12yz) {
		this.c12yz = c12yz;
	}

	public String getC12Nums() {
		return c12Nums;
	}

	public void setC12Nums(String c12Nums) {
		this.c12Nums = c12Nums;
	}

	public Integer getC13yz() {
		return c13yz;
	}

	public void setC13yz(Integer c13yz) {
		this.c13yz = c13yz;
	}

	public String getC13Nums() {
		return c13Nums;
	}

	public void setC13Nums(String c13Nums) {
		this.c13Nums = c13Nums;
	}

	public Integer getC14yz() {
		return c14yz;
	}

	public void setC14yz(Integer c14yz) {
		this.c14yz = c14yz;
	}

	public String getC14Nums() {
		return c14Nums;
	}

	public void setC14Nums(String c14Nums) {
		this.c14Nums = c14Nums;
	}

	public Integer getC15yz() {
		return c15yz;
	}

	public void setC15yz(Integer c15yz) {
		this.c15yz = c15yz;
	}

	public String getC15Nums() {
		return c15Nums;
	}

	public void setC15Nums(String c15Nums) {
		this.c15Nums = c15Nums;
	}
}
