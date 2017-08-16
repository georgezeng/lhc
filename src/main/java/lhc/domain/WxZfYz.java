package lhc.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "wx_zf_yz")
public class WxZfYz extends ZfAvg {
	private Integer zf0;
	private Integer zf1;
	private Integer zf2;
	private Integer zf3;
	private Integer zf4;

	public Integer getZf0() {
		return zf0;
	}

	public void setZf0(Integer zf0) {
		this.zf0 = zf0;
	}

	public Integer getZf1() {
		return zf1;
	}

	public void setZf1(Integer zf1) {
		this.zf1 = zf1;
	}

	public Integer getZf2() {
		return zf2;
	}

	public void setZf2(Integer zf2) {
		this.zf2 = zf2;
	}

	public Integer getZf3() {
		return zf3;
	}

	public void setZf3(Integer zf3) {
		this.zf3 = zf3;
	}

	public Integer getZf4() {
		return zf4;
	}

	public void setZf4(Integer zf4) {
		this.zf4 = zf4;
	}

}
