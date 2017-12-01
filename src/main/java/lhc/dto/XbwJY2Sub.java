package lhc.dto;

import java.util.List;

public class XbwJY2Sub {
	private String text;
	private String range;
	private List<Integer> nums;

	public XbwJY2Sub(String text, String range, List<Integer> nums) {
		this.text = text;
		this.range = range;
		this.nums = nums;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	public List<Integer> getNums() {
		return nums;
	}

	public void setNums(List<Integer> nums) {
		this.nums = nums;
	}
}
