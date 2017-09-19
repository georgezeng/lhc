package lhc.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "hm_ds_yz")
public class HmDsYz extends BaseYz {
	private Integer small;
	private Integer large;
	private Integer odd;
	private Integer even;
	private Integer smallOdd;
	private Integer smallEven;
	private Integer largeOdd;
	private Integer largeEven;
	@Column(name = "last_sl_yz")
	private Integer lastSLYz;
	@Column(name = "last_oe_yz")
	private Integer lastOEYz;
	@Column(name = "last_sloe_yz")
	private Integer lastSLOEYz;
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

	public Integer getLastSLYz() {
		return lastSLYz;
	}

	public void setLastSLYz(Integer lastSLYz) {
		this.lastSLYz = lastSLYz;
	}

	public Integer getLastOEYz() {
		return lastOEYz;
	}

	public void setLastOEYz(Integer lastOEYz) {
		this.lastOEYz = lastOEYz;
	}

	public Integer getLastSLOEYz() {
		return lastSLOEYz;
	}

	public void setLastSLOEYz(Integer lastSLOEYz) {
		this.lastSLOEYz = lastSLOEYz;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

}
