package lhc.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sw_yz")
public class SwYz {
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
	private Integer w0;
	private Integer total;
	private Integer delta;
	private Integer lastYz;

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
