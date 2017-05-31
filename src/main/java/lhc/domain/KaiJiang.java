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

	private Integer specialNum;

	@Enumerated(EnumType.STRING)
	private SX num1Sx;
	private Integer num1;

	@Enumerated(EnumType.STRING)
	private SX num2Sx;
	private Integer num2;

	@Enumerated(EnumType.STRING)
	private SX num3Sx;
	private Integer num3;

	@Enumerated(EnumType.STRING)
	private SX num4Sx;
	private Integer num4;

	@Enumerated(EnumType.STRING)
	private SX num5Sx;
	private Integer num5;

	@Enumerated(EnumType.STRING)
	private SX num6Sx;
	private Integer num6;

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

	public Integer getSpecialNum() {
		return specialNum;
	}

	public void setSpecialNum(Integer specialNum) {
		this.specialNum = specialNum;
	}

	public SX getNum1Sx() {
		return num1Sx;
	}

	public void setNum1Sx(SX num1Sx) {
		this.num1Sx = num1Sx;
	}

	public Integer getNum1() {
		return num1;
	}

	public void setNum1(Integer num1) {
		this.num1 = num1;
	}

	public SX getNum2Sx() {
		return num2Sx;
	}

	public void setNum2Sx(SX num2Sx) {
		this.num2Sx = num2Sx;
	}

	public Integer getNum2() {
		return num2;
	}

	public void setNum2(Integer num2) {
		this.num2 = num2;
	}

	public SX getNum3Sx() {
		return num3Sx;
	}

	public void setNum3Sx(SX num3Sx) {
		this.num3Sx = num3Sx;
	}

	public Integer getNum3() {
		return num3;
	}

	public void setNum3(Integer num3) {
		this.num3 = num3;
	}

	public SX getNum4Sx() {
		return num4Sx;
	}

	public void setNum4Sx(SX num4Sx) {
		this.num4Sx = num4Sx;
	}

	public Integer getNum4() {
		return num4;
	}

	public void setNum4(Integer num4) {
		this.num4 = num4;
	}

	public SX getNum5Sx() {
		return num5Sx;
	}

	public void setNum5Sx(SX num5Sx) {
		this.num5Sx = num5Sx;
	}

	public Integer getNum5() {
		return num5;
	}

	public void setNum5(Integer num5) {
		this.num5 = num5;
	}

	public SX getNum6Sx() {
		return num6Sx;
	}

	public void setNum6Sx(SX num6Sx) {
		this.num6Sx = num6Sx;
	}

	public Integer getNum6() {
		return num6;
	}

	public void setNum6(Integer num6) {
		this.num6 = num6;
	}

	public int getPhase() {
		return phase;
	}

	public void setPhase(int phase) {
		this.phase = phase;
	}

}
