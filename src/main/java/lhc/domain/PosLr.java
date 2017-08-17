package lhc.domain;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

import lhc.enums.Color;

@MappedSuperclass
public class PosLr extends PosAvg {
	@Enumerated(EnumType.STRING)
	private Color lastYzColor;
	private Integer red;
	private Integer yellow;
	private Integer green;
	private int colorCount = 1;
	private Integer colorMax;
	private Integer colorTotal;

	public Color getLastYzColor() {
		return lastYzColor;
	}

	public void setLastYzColor(Color lastYzColor) {
		this.lastYzColor = lastYzColor;
	}

	public Integer getRed() {
		return red;
	}

	public void setRed(Integer red) {
		this.red = red;
	}

	public Integer getYellow() {
		return yellow;
	}

	public void setYellow(Integer yellow) {
		this.yellow = yellow;
	}

	public Integer getGreen() {
		return green;
	}

	public void setGreen(Integer green) {
		this.green = green;
	}

	public int getColorCount() {
		return colorCount;
	}

	public void setColorCount(int colorCount) {
		this.colorCount = colorCount;
	}

	public Integer getColorMax() {
		return colorMax;
	}

	public void setColorMax(Integer colorMax) {
		this.colorMax = colorMax;
	}

	public Integer getColorTotal() {
		return colorTotal;
	}

	public void setColorTotal(Integer colorTotal) {
		this.colorTotal = colorTotal;
	}
}
