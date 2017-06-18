package lhc.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "ds_yz")
public class DsYz {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int year;
	private int phase;
	private String date;
	private Integer sxSmall;
	private Integer sxLarge;
	private Integer sxSingle;
	private Integer sxEven;
	private Integer hmSmall;
	private Integer hmLarge;
	private Integer hmSingle;
	private Integer hmEven;
	private Integer lastHmDsYz;
	private Integer lastHmDxYz;
	private Integer lastSxDsYz;
	private Integer lastSxDxYz;
	@Transient
	private Integer total;

	public Integer getSxSmall() {
		return sxSmall;
	}

	public void setSxSmall(Integer sxSmall) {
		this.sxSmall = sxSmall;
	}

	public Integer getSxLarge() {
		return sxLarge;
	}

	public void setSxLarge(Integer sxLarge) {
		this.sxLarge = sxLarge;
	}

	public Integer getSxSingle() {
		return sxSingle;
	}

	public void setSxSingle(Integer sxSingle) {
		this.sxSingle = sxSingle;
	}

	public Integer getSxEven() {
		return sxEven;
	}

	public void setSxEven(Integer sxEven) {
		this.sxEven = sxEven;
	}

	public Integer getHmSmall() {
		return hmSmall;
	}

	public void setHmSmall(Integer hmSmall) {
		this.hmSmall = hmSmall;
	}

	public Integer getHmLarge() {
		return hmLarge;
	}

	public void setHmLarge(Integer hmLarge) {
		this.hmLarge = hmLarge;
	}

	public Integer getHmSingle() {
		return hmSingle;
	}

	public void setHmSingle(Integer hmSingle) {
		this.hmSingle = hmSingle;
	}

	public Integer getHmEven() {
		return hmEven;
	}

	public void setHmEven(Integer hmEven) {
		this.hmEven = hmEven;
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

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getLastHmDsYz() {
		return lastHmDsYz;
	}

	public void setLastHmDsYz(Integer lastHmDsYz) {
		this.lastHmDsYz = lastHmDsYz;
	}

	public Integer getLastHmDxYz() {
		return lastHmDxYz;
	}

	public void setLastHmDxYz(Integer lastHmDxYz) {
		this.lastHmDxYz = lastHmDxYz;
	}

	public Integer getLastSxDsYz() {
		return lastSxDsYz;
	}

	public void setLastSxDsYz(Integer lastSxDsYz) {
		this.lastSxDsYz = lastSxDsYz;
	}

	public Integer getLastSxDxYz() {
		return lastSxDxYz;
	}

	public void setLastSxDxYz(Integer lastSxDxYz) {
		this.lastSxDxYz = lastSxDxYz;
	}

	public Long getId() {
		return id;
	}

}
