package lhc.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mw_yz")
public class MwYz {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int year;
	private int phase;
	private String date;
	private Integer w1;
	private Integer w2;
	private Integer w3;
	private Integer w4;
	private Integer w5;
	private Integer w6;
	private Integer w7;
	private Integer w8;
	private Integer w9;
	private Integer w0;
	private Integer total;
	private Integer lastYz;
	private Integer delta;

	public Integer getDelta() {
		return delta;
	}

	public void setDelta(Integer delta) {
		this.delta = delta;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getW0() {
		return w0;
	}

	public void setW0(Integer w0) {
		this.w0 = w0;
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

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getLastYz() {
		return lastYz;
	}

	public void setLastYz(Integer lastYz) {
		this.lastYz = lastYz;
	}

	public Long getId() {
		return id;
	}

}
