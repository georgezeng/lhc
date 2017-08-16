package lhc.domain;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class PosAvg extends Avg {
	private Integer pos;

	public Integer getPos() {
		return pos;
	}

	public void setPos(Integer pos) {
		this.pos = pos;
	}

}
