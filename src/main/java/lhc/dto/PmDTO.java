package lhc.dto;

import java.math.BigDecimal;

public class PmDTO {
	private int year;
	private int phase;
	private PmNum num1;
	private PmNum num2;
	private PmNum num3;
	private PmNum num4;
	private PmNum num5;
	private PmNum num6;
	private BigDecimal tp1 = BigDecimal.ZERO;
	private BigDecimal tp2 = BigDecimal.ZERO;
	private BigDecimal tp3 = BigDecimal.ZERO;
	private BigDecimal tp4 = BigDecimal.ZERO;
	private BigDecimal tp5 = BigDecimal.ZERO;
	private BigDecimal tp6 = BigDecimal.ZERO;
	private SpecialNum specialNum;

	public BigDecimal getTp1() {
		return tp1;
	}

	public void setTp1(BigDecimal tp1) {
		this.tp1 = tp1;
	}

	public BigDecimal getTp2() {
		return tp2;
	}

	public void setTp2(BigDecimal tp2) {
		this.tp2 = tp2;
	}

	public BigDecimal getTp3() {
		return tp3;
	}

	public void setTp3(BigDecimal tp3) {
		this.tp3 = tp3;
	}

	public BigDecimal getTp4() {
		return tp4;
	}

	public void setTp4(BigDecimal tp4) {
		this.tp4 = tp4;
	}

	public BigDecimal getTp5() {
		return tp5;
	}

	public void setTp5(BigDecimal tp5) {
		this.tp5 = tp5;
	}

	public BigDecimal getTp6() {
		return tp6;
	}

	public void setTp6(BigDecimal tp6) {
		this.tp6 = tp6;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getPhase() {
		return phase;
	}

	public void setPhase(int phase) {
		this.phase = phase;
	}

	public SpecialNum getSpecialNum() {
		return specialNum;
	}

	public void setSpecialNum(SpecialNum specialNum) {
		this.specialNum = specialNum;
	}

	public PmNum getNum1() {
		return num1;
	}

	public void setNum1(PmNum num1) {
		this.num1 = num1;
	}

	public PmNum getNum2() {
		return num2;
	}

	public void setNum2(PmNum num2) {
		this.num2 = num2;
	}

	public PmNum getNum3() {
		return num3;
	}

	public void setNum3(PmNum num3) {
		this.num3 = num3;
	}

	public PmNum getNum4() {
		return num4;
	}

	public void setNum4(PmNum num4) {
		this.num4 = num4;
	}

	public PmNum getNum5() {
		return num5;
	}

	public void setNum5(PmNum num5) {
		this.num5 = num5;
	}

	public PmNum getNum6() {
		return num6;
	}

	public void setNum6(PmNum num6) {
		this.num6 = num6;
	}

}
