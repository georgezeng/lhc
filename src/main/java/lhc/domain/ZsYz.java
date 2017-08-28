package lhc.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import lhc.constants.ZsNums;

@Entity
@Table(name = "zs_yz")
public class ZsYz extends Lr {
	private Integer fd1;
	private Integer fd2;
	private Integer fd3;
	private Integer fd4;
	private Integer fd5;
	private Integer fd6;
	private Integer fd7;
	private Integer fd8;
	private Integer fd9;
	@Transient
	private Integer[][] list = new Integer[][] {
		ZsNums.FD1.toArray(new Integer[]{}),
		ZsNums.FD2.toArray(new Integer[]{}),
		ZsNums.FD3.toArray(new Integer[]{}),
		ZsNums.FD4.toArray(new Integer[]{}),
		ZsNums.FD5.toArray(new Integer[]{}),
		ZsNums.FD6.toArray(new Integer[]{}),
		ZsNums.FD7.toArray(new Integer[]{}),
		ZsNums.FD8.toArray(new Integer[]{}),
		ZsNums.FD9.toArray(new Integer[]{})	
	};

	public Integer[][] getList() {
		return list;
	}

	public void setList(Integer[][] list) {
		this.list = list;
	}

	public Integer getFd1() {
		return fd1;
	}

	public void setFd1(Integer fd1) {
		this.fd1 = fd1;
	}

	public Integer getFd2() {
		return fd2;
	}

	public void setFd2(Integer fd2) {
		this.fd2 = fd2;
	}

	public Integer getFd3() {
		return fd3;
	}

	public void setFd3(Integer fd3) {
		this.fd3 = fd3;
	}

	public Integer getFd4() {
		return fd4;
	}

	public void setFd4(Integer fd4) {
		this.fd4 = fd4;
	}

	public Integer getFd5() {
		return fd5;
	}

	public void setFd5(Integer fd5) {
		this.fd5 = fd5;
	}

	public Integer getFd6() {
		return fd6;
	}

	public void setFd6(Integer fd6) {
		this.fd6 = fd6;
	}

	public Integer getFd7() {
		return fd7;
	}

	public void setFd7(Integer fd7) {
		this.fd7 = fd7;
	}

	public Integer getFd8() {
		return fd8;
	}

	public void setFd8(Integer fd8) {
		this.fd8 = fd8;
	}

	public Integer getFd9() {
		return fd9;
	}

	public void setFd9(Integer fd9) {
		this.fd9 = fd9;
	}

}
