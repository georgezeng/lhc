package lhc.dto;

public class SpecialNum {
	private int num;
	private Integer delta;
	private boolean matchedForSpecialNum;

	public boolean isMatchedForSpecialNum() {
		return matchedForSpecialNum;
	}

	public void setMatchedForSpecialNum(boolean matchedForSpecialNum) {
		this.matchedForSpecialNum = matchedForSpecialNum;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Integer getDelta() {
		return delta;
	}

	public void setDelta(Integer delta) {
		this.delta = delta;
	}

}
