package lhc.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import lhc.constants.Bs9qNums;

@Entity
@Table(name = "bs9q_yz")
public class Bs9qYz extends Lr {
	private Integer red1;
	private Integer red2;
	private Integer red3;
	private Integer blue1;
	private Integer blue2;
	private Integer blue3;
	private Integer green1;
	private Integer green2;
	private Integer green3;
	@Transient
	private Integer[][] list = new Integer[][] {
		Bs9qNums.RED1.toArray(new Integer[]{}),
		Bs9qNums.RED2.toArray(new Integer[]{}),
		Bs9qNums.RED3.toArray(new Integer[]{}),
		Bs9qNums.BLUE1.toArray(new Integer[]{}),
		Bs9qNums.BLUE2.toArray(new Integer[]{}),
		Bs9qNums.BLUE3.toArray(new Integer[]{}),
		Bs9qNums.GREEN1.toArray(new Integer[]{}),
		Bs9qNums.GREEN2.toArray(new Integer[]{}),
		Bs9qNums.GREEN3.toArray(new Integer[]{})
	};

	public Integer[][] getList() {
		return list;
	}

	public void setList(Integer[][] list) {
		this.list = list;
	}

	public Integer getRed1() {
		return red1;
	}

	public void setRed1(Integer red1) {
		this.red1 = red1;
	}

	public Integer getRed2() {
		return red2;
	}

	public void setRed2(Integer red2) {
		this.red2 = red2;
	}

	public Integer getRed3() {
		return red3;
	}

	public void setRed3(Integer red3) {
		this.red3 = red3;
	}

	public Integer getBlue1() {
		return blue1;
	}

	public void setBlue1(Integer blue1) {
		this.blue1 = blue1;
	}

	public Integer getBlue2() {
		return blue2;
	}

	public void setBlue2(Integer blue2) {
		this.blue2 = blue2;
	}

	public Integer getBlue3() {
		return blue3;
	}

	public void setBlue3(Integer blue3) {
		this.blue3 = blue3;
	}

	public Integer getGreen1() {
		return green1;
	}

	public void setGreen1(Integer green1) {
		this.green1 = green1;
	}

	public Integer getGreen2() {
		return green2;
	}

	public void setGreen2(Integer green2) {
		this.green2 = green2;
	}

	public Integer getGreen3() {
		return green3;
	}

	public void setGreen3(Integer green3) {
		this.green3 = green3;
	}

}
