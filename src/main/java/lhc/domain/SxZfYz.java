package lhc.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "sx_zf_yz")
public class SxZfYz {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int year;
	private int phase;
	private String date;
	private Integer zf0;
	private Integer zf1;
	private Integer zf2;
	private Integer zf3;
	private Integer zf4;
	private Integer zf5;
	private Integer zf6;
	private Integer zf7;
	private Integer zf8;
	private Integer zf9;
	private Integer zf10;
	private Integer zf11;
	private Integer total;
	private Integer delta;
	private Integer lastYz;
	private Integer currentPos;

	@Transient
	private Integer[] posList = new Integer[12];

	@Transient
	private int[] lastYzList = new int[25];

	public int[] getLastYzList() {
		return lastYzList;
	}

	public void setLastYzList(int[] lastYzList) {
		this.lastYzList = lastYzList;
	}

	public Integer[] getPosList() {
		return posList;
	}

	public void setPosList(Integer[] posList) {
		this.posList = posList;
	}

	public Integer getCurrentPos() {
		return currentPos;
	}

	public void setCurrentPos(Integer currentPos) {
		this.currentPos = currentPos;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getPhase() {
		return phase;
	}

	public void setPhase(int phase) {
		this.phase = phase;
	}

	public Integer getZf0() {
		return zf0;
	}

	public void setZf0(Integer zf0) {
		this.zf0 = zf0;
	}

	public Integer getZf1() {
		return zf1;
	}

	public void setZf1(Integer zf1) {
		this.zf1 = zf1;
	}

	public Integer getZf2() {
		return zf2;
	}

	public void setZf2(Integer zf2) {
		this.zf2 = zf2;
	}

	public Integer getZf3() {
		return zf3;
	}

	public void setZf3(Integer zf3) {
		this.zf3 = zf3;
	}

	public Integer getZf4() {
		return zf4;
	}

	public void setZf4(Integer zf4) {
		this.zf4 = zf4;
	}

	public Integer getZf5() {
		return zf5;
	}

	public void setZf5(Integer zf5) {
		this.zf5 = zf5;
	}

	public Integer getZf6() {
		return zf6;
	}

	public void setZf6(Integer zf6) {
		this.zf6 = zf6;
	}

	public Integer getZf7() {
		return zf7;
	}

	public void setZf7(Integer zf7) {
		this.zf7 = zf7;
	}

	public Integer getZf8() {
		return zf8;
	}

	public void setZf8(Integer zf8) {
		this.zf8 = zf8;
	}

	public Integer getZf9() {
		return zf9;
	}

	public void setZf9(Integer zf9) {
		this.zf9 = zf9;
	}

	public Integer getZf10() {
		return zf10;
	}

	public void setZf10(Integer zf10) {
		this.zf10 = zf10;
	}

	public Integer getZf11() {
		return zf11;
	}

	public void setZf11(Integer zf11) {
		this.zf11 = zf11;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getDelta() {
		return delta;
	}

	public void setDelta(Integer delta) {
		this.delta = delta;
	}

	public Integer getLastYz() {
		return lastYz;
	}

	public void setLastYz(Integer lastYZ) {
		this.lastYz = lastYZ;
	}

	public Long getId() {
		return id;
	}

}
