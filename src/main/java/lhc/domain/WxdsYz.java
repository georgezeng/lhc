package lhc.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "wxds_yz")
public class WxdsYz extends Lr {
	private Integer jinOdd;
	private Integer jinEven;
	private Integer muOdd;
	private Integer muEven;
	private Integer shuiOdd;
	private Integer shuiEven;
	private Integer huoOdd;
	private Integer huoEven;
	private Integer tuOdd;
	private Integer tuEven;

	public Integer getJinOdd() {
		return jinOdd;
	}

	public void setJinOdd(Integer jinOdd) {
		this.jinOdd = jinOdd;
	}

	public Integer getJinEven() {
		return jinEven;
	}

	public void setJinEven(Integer jinEven) {
		this.jinEven = jinEven;
	}

	public Integer getMuOdd() {
		return muOdd;
	}

	public void setMuOdd(Integer muOdd) {
		this.muOdd = muOdd;
	}

	public Integer getMuEven() {
		return muEven;
	}

	public void setMuEven(Integer muEven) {
		this.muEven = muEven;
	}

	public Integer getShuiOdd() {
		return shuiOdd;
	}

	public void setShuiOdd(Integer shuiOdd) {
		this.shuiOdd = shuiOdd;
	}

	public Integer getShuiEven() {
		return shuiEven;
	}

	public void setShuiEven(Integer shuiEven) {
		this.shuiEven = shuiEven;
	}

	public Integer getHuoOdd() {
		return huoOdd;
	}

	public void setHuoOdd(Integer huoOdd) {
		this.huoOdd = huoOdd;
	}

	public Integer getHuoEven() {
		return huoEven;
	}

	public void setHuoEven(Integer huoEven) {
		this.huoEven = huoEven;
	}

	public Integer getTuOdd() {
		return tuOdd;
	}

	public void setTuOdd(Integer tuOdd) {
		this.tuOdd = tuOdd;
	}

	public Integer getTuEven() {
		return tuEven;
	}

	public void setTuEven(Integer tuEven) {
		this.tuEven = tuEven;
	}

}
