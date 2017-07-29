package lhc.dto;

public class TmYzInfo {
	private Integer num;
	private Integer yz;
	private boolean tm;

	public TmYzInfo(Integer num, Integer yz) {
		this.num = num;
		this.yz = yz;
		tm = yz != null && yz == 0;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getYz() {
		return yz;
	}

	public void setYz(Integer yz) {
		this.yz = yz;
	}

	public boolean isTm() {
		return tm;
	}

	public void setTm(boolean tm) {
		this.tm = tm;
	}

}