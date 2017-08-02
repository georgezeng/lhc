package lhc.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sw_yz")
public class SwYz extends Avg {
	private Integer w1;
	private Integer w2;
	private Integer w3;
	private Integer w4;
	private Integer w0;

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

}
