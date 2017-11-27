package lhc.domain;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
public abstract class BaseDsYz extends BaseYz {
	private Integer small;
	private Integer large;
	private Integer odd;
	private Integer even;
	private Integer smallOdd;
	private Integer smallEven;
	private Integer largeOdd;
	private Integer largeEven;
	private Integer lastDsYz;
	private Integer lastDxYz;
	private Integer lastDxDsYz;
	@Transient
	private Integer total;

	public Integer getSmall() {
		return small;
	}

	public void setSmall(Integer small) {
		this.small = small;
	}

	public Integer getLarge() {
		return large;
	}

	public void setLarge(Integer large) {
		this.large = large;
	}

	public Integer getOdd() {
		return odd;
	}

	public void setOdd(Integer odd) {
		this.odd = odd;
	}

	public Integer getEven() {
		return even;
	}

	public void setEven(Integer even) {
		this.even = even;
	}

	public Integer getSmallOdd() {
		return smallOdd;
	}

	public void setSmallOdd(Integer smallOdd) {
		this.smallOdd = smallOdd;
	}

	public Integer getSmallEven() {
		return smallEven;
	}

	public void setSmallEven(Integer smallEven) {
		this.smallEven = smallEven;
	}

	public Integer getLargeOdd() {
		return largeOdd;
	}

	public void setLargeOdd(Integer largeOdd) {
		this.largeOdd = largeOdd;
	}

	public Integer getLargeEven() {
		return largeEven;
	}

	public void setLargeEven(Integer largeEven) {
		this.largeEven = largeEven;
	}

	public Integer getLastDsYz() {
		return lastDsYz;
	}

	public void setLastDsYz(Integer lastDsYz) {
		this.lastDsYz = lastDsYz;
	}

	public Integer getLastDxYz() {
		return lastDxYz;
	}

	public void setLastDxYz(Integer lastDxYz) {
		this.lastDxYz = lastDxYz;
	}

	public Integer getLastDxDsYz() {
		return lastDxDsYz;
	}

	public void setLastDxDsYz(Integer lastDxDsYz) {
		this.lastDxDsYz = lastDxDsYz;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

}
