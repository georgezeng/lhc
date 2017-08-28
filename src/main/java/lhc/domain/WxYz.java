package lhc.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import lhc.constants.WxNums;

@Entity
@Table(name = "wx_yz")
public class WxYz extends Avg {
	private Integer jin;
	private Integer mu;
	private Integer shui;
	private Integer huo;
	private Integer tu;
	@Transient
	private Integer[][] list = new Integer[][] {
		WxNums.JIN.toArray(new Integer[]{}),
		WxNums.MU.toArray(new Integer[]{}),
		WxNums.SHUI.toArray(new Integer[]{}),
		WxNums.HUO.toArray(new Integer[]{}),
		WxNums.TU.toArray(new Integer[]{})
	};

	public Integer[][] getList() {
		return list;
	}

	public void setList(Integer[][] list) {
		this.list = list;
	}

	public Integer getJin() {
		return jin;
	}

	public void setJin(Integer jin) {
		this.jin = jin;
	}

	public Integer getMu() {
		return mu;
	}

	public void setMu(Integer mu) {
		this.mu = mu;
	}

	public Integer getShui() {
		return shui;
	}

	public void setShui(Integer shui) {
		this.shui = shui;
	}

	public Integer getHuo() {
		return huo;
	}

	public void setHuo(Integer huo) {
		this.huo = huo;
	}

	public Integer getTu() {
		return tu;
	}

	public void setTu(Integer tu) {
		this.tu = tu;
	}

}
