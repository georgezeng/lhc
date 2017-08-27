package lhc.domain;

import java.math.BigDecimal;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
public abstract class BaseCsYz extends BaseYz {
	private BigDecimal avg;
	private Integer pairs;
	private Integer large;
	private Integer small;
	private Integer total;
	@Transient
	private String currentPos;

	public String getCurrentPos() {
		return currentPos;
	}

	public void setCurrentPos(String currentPos) {
		this.currentPos = currentPos;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getLarge() {
		return large;
	}

	public void setLarge(Integer large) {
		this.large = large;
	}

	public Integer getSmall() {
		return small;
	}

	public void setSmall(Integer small) {
		this.small = small;
	}

	public Integer getPairs() {
		return pairs;
	}

	public void setPairs(Integer pairs) {
		this.pairs = pairs;
	}

	public BigDecimal getAvg() {
		return avg;
	}

	public void setAvg(BigDecimal avg) {
		this.avg = avg;
	}

}
