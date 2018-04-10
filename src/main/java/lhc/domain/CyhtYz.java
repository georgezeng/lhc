package lhc.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cyht_yz")
public class CyhtYz extends BaseYz {
	private Integer specialNum;
	private String myNums;
	@Column(name = "my100_nums")
	private String my100Nums;
	private String swNums;
	@Column(name = "v1_nums")
	private String v1Nums;
	@Column(name = "v2_nums")
	private String v2Nums;
	@Column(name = "v3_nums")
	private String v3Nums;
	private Integer myYz;
	@Column(name = "my100_yz")
	private Integer my100Yz;
	private Integer swYz;
	@Column(name = "v1_yz")
	private Integer v1Yz; // my, my100
	@Column(name = "v2_yz")
	private Integer v2Yz; // my, sw
	@Column(name = "v3_yz")
	private Integer v3Yz; // my100, sw

	public Integer getSpecialNum() {
		return specialNum;
	}

	public void setSpecialNum(Integer specialNum) {
		this.specialNum = specialNum;
	}

	public String getMyNums() {
		return myNums;
	}

	public void setMyNums(String myNums) {
		this.myNums = myNums;
	}

	public String getMy100Nums() {
		return my100Nums;
	}

	public void setMy100Nums(String my100Nums) {
		this.my100Nums = my100Nums;
	}

	public String getSwNums() {
		return swNums;
	}

	public void setSwNums(String swNums) {
		this.swNums = swNums;
	}

	public String getV1Nums() {
		return v1Nums;
	}

	public void setV1Nums(String v1Nums) {
		this.v1Nums = v1Nums;
	}

	public String getV2Nums() {
		return v2Nums;
	}

	public void setV2Nums(String v2Nums) {
		this.v2Nums = v2Nums;
	}

	public String getV3Nums() {
		return v3Nums;
	}

	public void setV3Nums(String v3Nums) {
		this.v3Nums = v3Nums;
	}

	public Integer getMyYz() {
		return myYz;
	}

	public void setMyYz(Integer myYz) {
		this.myYz = myYz;
	}

	public Integer getMy100Yz() {
		return my100Yz;
	}

	public void setMy100Yz(Integer my100Yz) {
		this.my100Yz = my100Yz;
	}

	public Integer getSwYz() {
		return swYz;
	}

	public void setSwYz(Integer swYz) {
		this.swYz = swYz;
	}

	public Integer getV1Yz() {
		return v1Yz;
	}

	public void setV1Yz(Integer v1Yz) {
		this.v1Yz = v1Yz;
	}

	public Integer getV2Yz() {
		return v2Yz;
	}

	public void setV2Yz(Integer v2Yz) {
		this.v2Yz = v2Yz;
	}

	public Integer getV3Yz() {
		return v3Yz;
	}

	public void setV3Yz(Integer v3Yz) {
		this.v3Yz = v3Yz;
	}

}
