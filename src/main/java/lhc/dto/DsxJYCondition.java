package lhc.dto;

public class DsxJYCondition {
	private Integer year;
	private Integer phase;
	private Integer type; // 最少红点|最多红点
	private String version; // 加强版|经典版...
	private boolean reverse; // 是否反转
	private boolean qc; // 是否去重

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public boolean isReverse() {
		return reverse;
	}

	public void setReverse(boolean reverse) {
		this.reverse = reverse;
	}

	public boolean isQc() {
		return qc;
	}

	public void setQc(boolean qc) {
		this.qc = qc;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getPhase() {
		return phase;
	}

	public void setPhase(Integer phase) {
		this.phase = phase;
	}

}
