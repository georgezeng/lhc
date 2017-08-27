package lhc.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lhc.enums.SX;

@Entity
@Table(name = "sx_cs_yz")
public class SxCsYz extends BaseCsYz {
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
	private SX currentSx;

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
