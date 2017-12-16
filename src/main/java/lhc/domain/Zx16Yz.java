package lhc.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import lhc.constants.Zx16Nums;

@Entity
@Table(name = "zx16_yz")
public class Zx16Yz extends Avg {

	private Integer w1;
	private Integer w2;
	private Integer w3;
	private Integer w4;
	private Integer w5;
	private Integer w6;
	private Integer w7;
	private Integer w8;
	private Integer w9;
	private Integer w10;
	private Integer w11;
	private Integer w12;

	public List[] getNums() {
		return Zx16Nums.NUMS;
	}

	public Integer getW8() {
		return w8;
	}

	public void setW8(Integer w8) {
		this.w8 = w8;
	}

	public Integer getW9() {
		return w9;
	}

	public void setW9(Integer w9) {
		this.w9 = w9;
	}

	public Integer getW10() {
		return w10;
	}

	public void setW10(Integer w10) {
		this.w10 = w10;
	}

	public Integer getW11() {
		return w11;
	}

	public void setW11(Integer w11) {
		this.w11 = w11;
	}

	public Integer getW12() {
		return w12;
	}

	public void setW12(Integer w12) {
		this.w12 = w12;
	}

	public Integer getW1() {
		return w1;
	}

	public void setW1(Integer w1) {
		this.w1 = w1;
	}

	public Integer getW2() {
		return w2;
	}

	public void setW2(Integer w2) {
		this.w2 = w2;
	}

	public Integer getW3() {
		return w3;
	}

	public void setW3(Integer w3) {
		this.w3 = w3;
	}

	public Integer getW4() {
		return w4;
	}

	public void setW4(Integer w4) {
		this.w4 = w4;
	}

	public Integer getW5() {
		return w5;
	}

	public void setW5(Integer w5) {
		this.w5 = w5;
	}

	public Integer getW6() {
		return w6;
	}

	public void setW6(Integer w6) {
		this.w6 = w6;
	}

	public Integer getW7() {
		return w7;
	}

	public void setW7(Integer w7) {
		this.w7 = w7;
	}

}
