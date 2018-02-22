package lhc.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.google.common.base.Joiner;

@MappedSuperclass
public abstract class DmgJyYz extends BaseYz {
	private Integer specialNum;
	private String aeNums;
	private String bfNums;
	private String cgNums;
	private String dhNums;
	private String aeNumsReverse;
	private String bfNumsReverse;
	private String cgNumsReverse;
	private String dhNumsReverse;

	@Transient
	private String aen;
	@Transient
	private String bfn;
	@Transient
	private String cgn;
	@Transient
	private String dhn;

	@Transient
	private List<Integer> ae = new ArrayList<Integer>();
	@Transient
	private List<Integer> bf = new ArrayList<Integer>();
	@Transient
	private List<Integer> cg = new ArrayList<Integer>();
	@Transient
	private List<Integer> dh = new ArrayList<Integer>();

	public void reset() {
		specialNum = null;
		aeNums = null;
		bfNums = null;
		cgNums = null;
		dhNums = null;
		aeNumsReverse = null;
		bfNumsReverse = null;
		cgNumsReverse = null;
		dhNumsReverse = null;
	}

	public void clearAll() {
		ae.clear();
		bf.clear();
		cg.clear();
		dh.clear();
	}

	private String getJoinString(List<Integer> c) {
		String str = Joiner.on(",").join(c);
		return !str.isEmpty() ? str : null;
	}

	public void assemble(boolean reverse) {
		if (reverse) {
			aeNumsReverse = getJoinString(ae);
			bfNumsReverse = getJoinString(bf);
			cgNumsReverse = getJoinString(cg);
			dhNumsReverse = getJoinString(dh);
		} else {
			aeNums = getJoinString(ae);
			bfNums = getJoinString(bf);
			cgNums = getJoinString(cg);
			dhNums = getJoinString(dh);
		}
	}

	public Integer getSpecialNum() {
		return specialNum;
	}

	public void setSpecialNum(Integer specialNum) {
		this.specialNum = specialNum;
	}

	public String getAeNums() {
		return aeNums;
	}

	public void setAeNums(String aeNums) {
		this.aeNums = aeNums;
	}

	public String getBfNums() {
		return bfNums;
	}

	public void setBfNums(String bfNums) {
		this.bfNums = bfNums;
	}

	public String getCgNums() {
		return cgNums;
	}

	public void setCgNums(String cgNums) {
		this.cgNums = cgNums;
	}

	public String getDhNums() {
		return dhNums;
	}

	public void setDhNums(String dhNums) {
		this.dhNums = dhNums;
	}

	public String getAeNumsReverse() {
		return aeNumsReverse;
	}

	public void setAeNumsReverse(String aeNumsReverse) {
		this.aeNumsReverse = aeNumsReverse;
	}

	public String getBfNumsReverse() {
		return bfNumsReverse;
	}

	public void setBfNumsReverse(String bfNumsReverse) {
		this.bfNumsReverse = bfNumsReverse;
	}

	public String getCgNumsReverse() {
		return cgNumsReverse;
	}

	public void setCgNumsReverse(String cgNumsReverse) {
		this.cgNumsReverse = cgNumsReverse;
	}

	public String getDhNumsReverse() {
		return dhNumsReverse;
	}

	public void setDhNumsReverse(String dhNumsReverse) {
		this.dhNumsReverse = dhNumsReverse;
	}

	public String getAen() {
		return aen;
	}

	public void setAen(String aen) {
		this.aen = aen;
	}

	public String getBfn() {
		return bfn;
	}

	public void setBfn(String bfn) {
		this.bfn = bfn;
	}

	public String getCgn() {
		return cgn;
	}

	public void setCgn(String cgn) {
		this.cgn = cgn;
	}

	public String getDhn() {
		return dhn;
	}

	public void setDhn(String dhn) {
		this.dhn = dhn;
	}

	public List<Integer> getAe() {
		return ae;
	}

	public void setAe(List<Integer> ae) {
		this.ae = ae;
	}

	public List<Integer> getBf() {
		return bf;
	}

	public void setBf(List<Integer> bf) {
		this.bf = bf;
	}

	public List<Integer> getCg() {
		return cg;
	}

	public void setCg(List<Integer> cg) {
		this.cg = cg;
	}

	public List<Integer> getDh() {
		return dh;
	}

	public void setDh(List<Integer> dh) {
		this.dh = dh;
	}

}
