package lhc.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "bs_yz")
public class BsYz extends Avg {

	private Integer redOdd;
	private Integer redEven;
	private Integer greenOdd;
	private Integer greenEven;
	private Integer blueOdd;
	private Integer blueEven;

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

}
