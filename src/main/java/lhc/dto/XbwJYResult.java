package lhc.dto;

import java.util.ArrayList;
import java.util.List;

public class XbwJYResult {
	private Integer year;
	private Integer phase;
	private Integer specialNum;
	private List<XbwJY> datas = new ArrayList<XbwJY>();

	public List<XbwJY> getDatas() {
		return datas;
	}

	public void setDatas(List<XbwJY> datas) {
		this.datas = datas;
	}

	public void addData(XbwJY data) {
		datas.add(data);
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

	public Integer getSpecialNum() {
		return specialNum;
	}

	public void setSpecialNum(Integer specialNum) {
		this.specialNum = specialNum;
	}
}
