package lhc.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sx_lr_yz")
public class SxLrYz extends Avg {
	private Integer redRed;
	private Integer redYellow;
	private Integer redGreen;
	private Integer yellowRed;
	private Integer yellowYellow;
	private Integer yellowGreen;
	private Integer greenRed;
	private Integer greenYellow;
	private Integer greenGreen;
	private Integer pos;

	public Integer getPos() {
		return pos;
	}

	public void setPos(Integer pos) {
		this.pos = pos;
	}

	public Integer getRedRed() {
		return redRed;
	}

	public void setRedRed(Integer redRed) {
		this.redRed = redRed;
	}

	public Integer getRedYellow() {
		return redYellow;
	}

	public void setRedYellow(Integer redYellow) {
		this.redYellow = redYellow;
	}

	public Integer getRedGreen() {
		return redGreen;
	}

	public void setRedGreen(Integer redGreen) {
		this.redGreen = redGreen;
	}

	public Integer getYellowRed() {
		return yellowRed;
	}

	public void setYellowRed(Integer yellowRed) {
		this.yellowRed = yellowRed;
	}

	public Integer getYellowYellow() {
		return yellowYellow;
	}

	public void setYellowYellow(Integer yellowYellow) {
		this.yellowYellow = yellowYellow;
	}

	public Integer getYellowGreen() {
		return yellowGreen;
	}

	public void setYellowGreen(Integer yellowGreen) {
		this.yellowGreen = yellowGreen;
	}

	public Integer getGreenRed() {
		return greenRed;
	}

	public void setGreenRed(Integer greenRed) {
		this.greenRed = greenRed;
	}

	public Integer getGreenYellow() {
		return greenYellow;
	}

	public void setGreenYellow(Integer greenYellow) {
		this.greenYellow = greenYellow;
	}

	public Integer getGreenGreen() {
		return greenGreen;
	}

	public void setGreenGreen(Integer greenGreen) {
		this.greenGreen = greenGreen;
	}

}
