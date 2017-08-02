package lhc.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "sw_zf_yz")
public class SwZfYz extends Avg {
	private Integer zf0;
	private Integer zf1;
	private Integer zf2;
	private Integer zf3;
	private Integer zf4;
	private Integer currentPos;

	@Transient
	private Integer[] posList = new Integer[12];

	@Transient
	private int[] lastYzList = new int[25];

	@Transient
	private BigDecimal avg;

	@Transient
	private Integer lastCountYz;

	public Integer getLastCountYz() {
		return lastCountYz;
	}

	public void setLastCountYz(Integer lastCountYz) {
		this.lastCountYz = lastCountYz;
	}

	public BigDecimal getAvg() {
		return avg;
	}

	public void setAvg(BigDecimal avg) {
		this.avg = avg;
	}

	public int[] getLastYzList() {
		return lastYzList;
	}

	public void setLastYzList(int[] lastYzList) {
		this.lastYzList = lastYzList;
	}

	public Integer[] getPosList() {
		return posList;
	}

	public void setPosList(Integer[] posList) {
		this.posList = posList;
	}

	public Integer getCurrentPos() {
		return currentPos;
	}

	public void setCurrentPos(Integer currentPos) {
		this.currentPos = currentPos;
	}

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
