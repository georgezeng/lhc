package lhc.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lhc.enums.SX;

@Entity
@Table(name = "sx_yz")
public class SxYz {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int year;
	private int phase;
	private String date;
	private Integer Shu;
	private Integer Niu;
	private Integer Hu;
	private Integer Tu;
	@Column(name = "lonng")
	private Integer Long;
	private Integer She;
	private Integer Ma;
	private Integer Yang;
	private Integer Hou;
	private Integer Ji;
	private Integer Gou;
	private Integer Zhu;
	private Integer total;
	private BigDecimal totalAvg;
	private Integer delta;
	private Integer lastYz;
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
	@Enumerated(EnumType.STRING)
	private SX currentSx;

	@Transient
	private SX topSx;
	@Transient
	private SX lastSx;
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

	public SX getTopSx() {
		return topSx;
	}

	public void setTopSx(SX topSx) {
		this.topSx = topSx;
	}

	public SX getLastSx() {
		return lastSx;
	}

	public void setLastSx(SX lastSx) {
		this.lastSx = lastSx;
	}

	public SX getCurrentSx() {
		return currentSx;
	}

	public void setCurrentSx(SX currentSx) {
		this.currentSx = currentSx;
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

	public Integer getShu() {
		return Shu;
	}

	public void setShu(Integer Shu) {
		this.Shu = Shu;
	}

	public Integer getNiu() {
		return Niu;
	}

	public void setNiu(Integer Niu) {
		this.Niu = Niu;
	}

	public Integer getHu() {
		return Hu;
	}

	public void setHu(Integer Hu) {
		this.Hu = Hu;
	}

	public Integer getTu() {
		return Tu;
	}

	public void setTu(Integer Tu) {
		this.Tu = Tu;
	}

	public Integer getLong() {
		return Long;
	}

	public void setLong(Integer Long) {
		this.Long = Long;
	}

	public Integer getShe() {
		return She;
	}

	public void setShe(Integer She) {
		this.She = She;
	}

	public Integer getMa() {
		return Ma;
	}

	public void setMa(Integer Ma) {
		this.Ma = Ma;
	}

	public Integer getYang() {
		return Yang;
	}

	public void setYang(Integer Yang) {
		this.Yang = Yang;
	}

	public Integer getHou() {
		return Hou;
	}

	public void setHou(Integer Hou) {
		this.Hou = Hou;
	}

	public Integer getJi() {
		return Ji;
	}

	public void setJi(Integer Ji) {
		this.Ji = Ji;
	}

	public Integer getGou() {
		return Gou;
	}

	public void setGou(Integer Gou) {
		this.Gou = Gou;
	}

	public Integer getZhu() {
		return Zhu;
	}

	public void setZhu(Integer Zhu) {
		this.Zhu = Zhu;
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
