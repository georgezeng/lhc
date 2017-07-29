package lhc.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "sx_zf_yz2")
public class SxZfYz2 {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int year;
	private int phase;
	private String date;
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
	private Integer zf10;
	private Integer zf11;
	private Integer total;
	private BigDecimal totalAvg;
	private Integer delta;
	private Integer lastYz;
	private Integer currentPos;
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

	@Transient
	private Integer[] posList = new Integer[12];

	@Transient
	private int[] lastYzList = new int[25];

	@Transient
	private BigDecimal avg;

	@Transient
	private Integer lastCountYz;

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

	public Integer getLastCountYz() {
		return lastCountYz;
	}

	public void setLastCountYz(Integer lastCountYz) {
		this.lastCountYz = lastCountYz;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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

	public Integer getZf10() {
		return zf10;
	}

	public void setZf10(Integer zf10) {
		this.zf10 = zf10;
	}

	public Integer getZf11() {
		return zf11;
	}

	public void setZf11(Integer zf11) {
		this.zf11 = zf11;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getDelta() {
		return delta;
	}

	public void setDelta(Integer delta) {
		this.delta = delta;
	}

	public Integer getLastYz() {
		return lastYz;
	}

	public void setLastYz(Integer lastYZ) {
		this.lastYz = lastYZ;
	}

	public Long getId() {
		return id;
	}

}
