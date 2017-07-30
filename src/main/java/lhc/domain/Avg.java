package lhc.domain;

import java.math.BigDecimal;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Avg {
	private Integer total;
	private BigDecimal totalAvg;
	private Integer max;
	private BigDecimal maxAvg;
	private Integer min0;
	private BigDecimal min0Avg;
	private Integer min1;
	private BigDecimal min1Avg;
	private Integer min2;
	private BigDecimal min2Avg;
	private Integer min3;
	private BigDecimal min3Avg;
	private Integer min4;
	private BigDecimal min4Avg;
	private Integer min5;
	private BigDecimal min5Avg;
	private Integer min6;
	private BigDecimal min6Avg;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public BigDecimal getTotalAvg() {
		return totalAvg;
	}

	public void setTotalAvg(BigDecimal totalAvg) {
		this.totalAvg = totalAvg;
	}

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}

	public BigDecimal getMaxAvg() {
		return maxAvg;
	}

	public void setMaxAvg(BigDecimal maxAvg) {
		this.maxAvg = maxAvg;
	}

	public Integer getMin0() {
		return min0;
	}

	public void setMin0(Integer min0) {
		this.min0 = min0;
	}

	public BigDecimal getMin0Avg() {
		return min0Avg;
	}

	public void setMin0Avg(BigDecimal min0Avg) {
		this.min0Avg = min0Avg;
	}

	public Integer getMin1() {
		return min1;
	}

	public void setMin1(Integer min1) {
		this.min1 = min1;
	}

	public BigDecimal getMin1Avg() {
		return min1Avg;
	}

	public void setMin1Avg(BigDecimal min1Avg) {
		this.min1Avg = min1Avg;
	}

	public Integer getMin2() {
		return min2;
	}

	public void setMin2(Integer min2) {
		this.min2 = min2;
	}

	public BigDecimal getMin2Avg() {
		return min2Avg;
	}

	public void setMin2Avg(BigDecimal min2Avg) {
		this.min2Avg = min2Avg;
	}

	public Integer getMin3() {
		return min3;
	}

	public void setMin3(Integer min3) {
		this.min3 = min3;
	}

	public BigDecimal getMin3Avg() {
		return min3Avg;
	}

	public void setMin3Avg(BigDecimal min3Avg) {
		this.min3Avg = min3Avg;
	}

	public Integer getMin4() {
		return min4;
	}

	public void setMin4(Integer min4) {
		this.min4 = min4;
	}

	public BigDecimal getMin4Avg() {
		return min4Avg;
	}

	public void setMin4Avg(BigDecimal min4Avg) {
		this.min4Avg = min4Avg;
	}

	public Integer getMin5() {
		return min5;
	}

	public void setMin5(Integer min5) {
		this.min5 = min5;
	}

	public BigDecimal getMin5Avg() {
		return min5Avg;
	}

	public void setMin5Avg(BigDecimal min5Avg) {
		this.min5Avg = min5Avg;
	}

	public Integer getMin6() {
		return min6;
	}

	public void setMin6(Integer min6) {
		this.min6 = min6;
	}

	public BigDecimal getMin6Avg() {
		return min6Avg;
	}

	public void setMin6Avg(BigDecimal min6Avg) {
		this.min6Avg = min6Avg;
	}
}
