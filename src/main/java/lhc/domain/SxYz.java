package lhc.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	@Column(name="lonng")
	private Integer Long;
	private Integer She;
	private Integer Ma;
	private Integer Yang;
	private Integer Hou;
	private Integer Ji;
	private Integer Gou;
	private Integer Zhu;
	private Integer total;
	private Integer delta;
	private Integer lastYz;
	@Enumerated(EnumType.STRING)
	private SX currentSx;
	
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
