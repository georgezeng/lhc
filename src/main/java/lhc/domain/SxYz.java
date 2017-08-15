package lhc.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;

import lhc.enums.SX;

@Entity
@Table(name = "sx_yz")
public class SxYz extends Lr {
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

}
