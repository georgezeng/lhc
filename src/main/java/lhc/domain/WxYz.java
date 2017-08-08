package lhc.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "wx_yz")
public class WxYz extends Avg {
	private Integer jin;
	private Integer mu;
	private Integer shui;
	private Integer huo;
	private Integer tu;

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
