package lhc.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ds_yz")
public class DsYz extends Avg {
	private Integer ds0Odd;
	private Integer ds0Even;
	private Integer ds1Odd;
	private Integer ds1Even;
	private Integer ds2Odd;
	private Integer ds2Even;
	private Integer ds3Odd;
	private Integer ds3Even;
	private Integer ds4Odd;
	private Integer ds4Even;

	public Integer getDs0Odd() {
		return ds0Odd;
	}

	public void setDs0Odd(Integer ds0Odd) {
		this.ds0Odd = ds0Odd;
	}

	public Integer getDs0Even() {
		return ds0Even;
	}

	public void setDs0Even(Integer ds0Even) {
		this.ds0Even = ds0Even;
	}

	public Integer getDs1Odd() {
		return ds1Odd;
	}

	public void setDs1Odd(Integer ds1Odd) {
		this.ds1Odd = ds1Odd;
	}

	public Integer getDs1Even() {
		return ds1Even;
	}

	public void setDs1Even(Integer ds1Even) {
		this.ds1Even = ds1Even;
	}

	public Integer getDs2Odd() {
		return ds2Odd;
	}

	public void setDs2Odd(Integer ds2Odd) {
		this.ds2Odd = ds2Odd;
	}

	public Integer getDs2Even() {
		return ds2Even;
	}

	public void setDs2Even(Integer ds2Even) {
		this.ds2Even = ds2Even;
	}

	public Integer getDs3Odd() {
		return ds3Odd;
	}

	public void setDs3Odd(Integer ds3Odd) {
		this.ds3Odd = ds3Odd;
	}

	public Integer getDs3Even() {
		return ds3Even;
	}

	public void setDs3Even(Integer ds3Even) {
		this.ds3Even = ds3Even;
	}

	public Integer getDs4Odd() {
		return ds4Odd;
	}

	public void setDs4Odd(Integer ds4Odd) {
		this.ds4Odd = ds4Odd;
	}

	public Integer getDs4Even() {
		return ds4Even;
	}

	public void setDs4Even(Integer ds4Even) {
		this.ds4Even = ds4Even;
	}

}
