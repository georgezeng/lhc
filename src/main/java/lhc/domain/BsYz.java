package lhc.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bs_yz")
public class BsYz extends Avg {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int year;
	private int phase;
	private String date;
	private Integer redOdd;
	private Integer redEven;
	private Integer greenOdd;
	private Integer greenEven;
	private Integer blueOdd;
	private Integer blueEven;
	private Integer lastYz;
	private Integer delta;

	public Integer getDelta() {
		return delta;
	}

	public void setDelta(Integer delta) {
		this.delta = delta;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getRedOdd() {
		return redOdd;
	}

	public void setRedOdd(Integer redOdd) {
		this.redOdd = redOdd;
	}

	public Integer getRedEven() {
		return redEven;
	}

	public void setRedEven(Integer redEven) {
		this.redEven = redEven;
	}

	public Integer getGreenOdd() {
		return greenOdd;
	}

	public void setGreenOdd(Integer greenOdd) {
		this.greenOdd = greenOdd;
	}

	public Integer getGreenEven() {
		return greenEven;
	}

	public void setGreenEven(Integer greenEven) {
		this.greenEven = greenEven;
	}

	public Integer getBlueOdd() {
		return blueOdd;
	}

	public void setBlueOdd(Integer blueOdd) {
		this.blueOdd = blueOdd;
	}

	public Integer getBlueEven() {
		return blueEven;
	}

	public void setBlueEven(Integer blueEven) {
		this.blueEven = blueEven;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getLastYz() {
		return lastYz;
	}

	public void setLastYz(Integer lastYz) {
		this.lastYz = lastYz;
	}

	public Long getId() {
		return id;
	}

}
