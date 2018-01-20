package lhc.dto;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Transient;

@SqlResultSetMapping(name = "FxSwA", classes = @ConstructorResult(targetClass = FxSwADTO.class, columns = {
		@ColumnResult(name = "date", type = String.class), @ColumnResult(name = "year", type = Integer.class),
		@ColumnResult(name = "phase", type = Integer.class), @ColumnResult(name = "specialNum", type = Integer.class),
		@ColumnResult(name = "a1Nums", type = String.class), @ColumnResult(name = "a2Nums", type = String.class),
		@ColumnResult(name = "a3Nums", type = String.class), @ColumnResult(name = "a3pNums", type = String.class),
		@ColumnResult(name = "arNums", type = String.class), @ColumnResult(name = "arA2A3A3PNums", type = String.class) }))
@Entity
public class FxSwADTO {
	@Id
	private Long id;
	private String date;
	private Integer year;
	private Integer phase;
	private Integer specialNum;
	private String a1Nums;
	private String a2Nums;
	private String a3Nums;
	private String a3pNums;
	private String arNums;
	private String arA2A3A3PNums;
	@Transient
	private String type;

	public FxSwADTO() {

	}

	public FxSwADTO(String date, Integer year, Integer phase, Integer specialNum, String a1Nums, String a2Nums,
			String a3Nums, String a3pNums, String arNums, String arA2A3A3PNums) {
		this.date = date;
		this.year = year;
		this.phase = phase;
		this.specialNum = specialNum;
		this.a1Nums = a1Nums;
		this.a2Nums = a2Nums;
		this.a3Nums = a3Nums;
		this.a3pNums = a3pNums;
		this.arNums = arNums;
		this.arA2A3A3PNums = arA2A3A3PNums;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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

	public String getA1Nums() {
		return a1Nums;
	}

	public void setA1Nums(String a1Nums) {
		this.a1Nums = a1Nums;
	}

	public String getA2Nums() {
		return a2Nums;
	}

	public void setA2Nums(String a2Nums) {
		this.a2Nums = a2Nums;
	}

	public String getA3Nums() {
		return a3Nums;
	}

	public void setA3Nums(String a3Nums) {
		this.a3Nums = a3Nums;
	}

	public String getA3pNums() {
		return a3pNums;
	}

	public void setA3pNums(String a3pNums) {
		this.a3pNums = a3pNums;
	}

	public String getArNums() {
		return arNums;
	}

	public void setArNums(String arNums) {
		this.arNums = arNums;
	}

	public String getArA2A3A3PNums() {
		return arA2A3A3PNums;
	}

	public void setArA2A3A3PNums(String arA2A3A3PNums) {
		this.arA2A3A3PNums = arA2A3A3PNums;
	}
}
