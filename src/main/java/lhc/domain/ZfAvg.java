package lhc.domain;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class ZfAvg extends Avg {
	private Integer currentPos;

	public Integer getCurrentPos() {
		return currentPos;
	}

	public void setCurrentPos(Integer currentPos) {
		this.currentPos = currentPos;
	}
}
