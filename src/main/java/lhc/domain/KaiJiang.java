package lhc.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lhc.enums.SX;

@Entity
@Table(name = "kai_jiang")
public class KaiJiang {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String date;

	private int year;

	@Enumerated(EnumType.STRING)
	private SX specialSx;

	private int specialNum;

	@Enumerated(EnumType.STRING)
	private SX num1Sx;
	private int num1;

	@Enumerated(EnumType.STRING)
	private SX num2Sx;
	private int num2;

	@Enumerated(EnumType.STRING)
	private SX num3Sx;
	private int num3;

	@Enumerated(EnumType.STRING)
	private SX num4Sx;
	private int num4;

	@Enumerated(EnumType.STRING)
	private SX num5Sx;
	private int num5;

	@Enumerated(EnumType.STRING)
	private SX num6Sx;
	private int num6;

	private int phase;

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Long getId() {
		return id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public SX getSpecialSx() {
		return specialSx;
	}

	public void setSpecialSx(SX specialSx) {
		this.specialSx = specialSx;
	}

	public int getSpecialNum() {
		return specialNum;
	}

	public void setSpecialNum(int specialNum) {
		this.specialNum = specialNum;
	}

	public SX getNum1Sx() {
		return num1Sx;
	}

	public void setNum1Sx(SX num1Sx) {
		this.num1Sx = num1Sx;
	}

	public int getNum1() {
		return num1;
	}

	public void setNum1(int num1) {
		this.num1 = num1;
	}

	public SX getNum2Sx() {
		return num2Sx;
	}

	public void setNum2Sx(SX num2Sx) {
		this.num2Sx = num2Sx;
	}

	public int getNum2() {
		return num2;
	}

	public void setNum2(int num2) {
		this.num2 = num2;
	}

	public SX getNum3Sx() {
		return num3Sx;
	}

	public void setNum3Sx(SX num3Sx) {
		this.num3Sx = num3Sx;
	}

	public int getNum3() {
		return num3;
	}

	public void setNum3(int num3) {
		this.num3 = num3;
	}

	public SX getNum4Sx() {
		return num4Sx;
	}

	public void setNum4Sx(SX num4Sx) {
		this.num4Sx = num4Sx;
	}

	public int getNum4() {
		return num4;
	}

	public void setNum4(int num4) {
		this.num4 = num4;
	}

	public SX getNum5Sx() {
		return num5Sx;
	}

	public void setNum5Sx(SX num5Sx) {
		this.num5Sx = num5Sx;
	}

	public int getNum5() {
		return num5;
	}

	public void setNum5(int num5) {
		this.num5 = num5;
	}

	public SX getNum6Sx() {
		return num6Sx;
	}

	public void setNum6Sx(SX num6Sx) {
		this.num6Sx = num6Sx;
	}

	public int getNum6() {
		return num6;
	}

	public void setNum6(int num6) {
		this.num6 = num6;
	}

	public int getPhase() {
		return phase;
	}

	public void setPhase(int phase) {
		this.phase = phase;
	}

}
