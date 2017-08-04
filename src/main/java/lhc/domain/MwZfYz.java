package lhc.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "mw_zf_yz")
public class MwZfYz extends Avg {
	private Integer zf0;
	private Integer zf1;
	private Integer zf2;
	private Integer zf3;
	private Integer zf4;
	private Integer zf5;
	private Integer zf6;
	private Integer zf7;
	private Integer zf8;
	private Integer zf9;
	private Integer currentPos;

	@Transient
	private Integer[] posList = new Integer[12];

	@Transient
	private int[] lastYzList = new int[25];

	@Transient
	private BigDecimal avg;

	@Transient
	private Integer lastCountYz;

	public Integer getZf5() {
		return zf5;
	}

	public void setZf5(Integer zf5) {
		this.zf5 = zf5;
	}

	public Integer getZf6() {
		return zf6;
	}

	public void setZf6(Integer zf6) {
		this.zf6 = zf6;
	}

	public Integer getZf7() {
		return zf7;
	}

	public void setZf7(Integer zf7) {
		this.zf7 = zf7;
	}

	public Integer getZf8() {
		return zf8;
	}

	public void setZf8(Integer zf8) {
		this.zf8 = zf8;
	}

	public Integer getZf9() {
		return zf9;
	}

	public void setZf9(Integer zf9) {
		this.zf9 = zf9;
	}

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
