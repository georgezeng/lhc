package lhc.dto;

import java.util.List;

public class XbwJYCondition {
	private Integer year;
	private Integer phase;
	private List<Integer> types;
	private Integer type;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public void setTypes(List<Integer> types) {
		this.types = types;
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

	public List<Integer> getTypes() {
		return types;
	}

}
