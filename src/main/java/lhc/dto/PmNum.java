package lhc.dto;

public class PmNum {
	private int num;
	private boolean matchedForSpecialNum;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public boolean isMatchedForSpecialNum() {
		return matchedForSpecialNum;
	}

	public void setMatchedForSpecialNum(boolean matchedForSpecialNum) {
		this.matchedForSpecialNum = matchedForSpecialNum;
	}

	public PmNum(int num, boolean matchedForSpecialNum) {
		this.num = num;
		this.matchedForSpecialNum = matchedForSpecialNum;
	}
}
