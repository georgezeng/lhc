package lhc.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lhc.dto.TmYzInfo;

@Entity
@Table(name = "tm_fd_yz")
public class TmFdYz {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int year;
	private int phase;
	private String date;
	private Integer fd1;
	private Integer fd2;
	private Integer fd3;
	private Integer fd4;
	private Integer fd5;
	private Integer fd6;
	private Integer fd7;
	private Integer total;
	private Integer lastYz;
	private Integer delta;
	private Integer maxYz;
	@Transient
	private List<TmYzInfo> list;

	public List<TmYzInfo> getList() {
		return list;
	}

	public void setList(List<TmYzInfo> list) {
		this.list = list;
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

	public Integer getMaxYz() {
		return maxYz;
	}

	public void setMaxYz(Integer maxYz) {
		this.maxYz = maxYz;
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

	public void setId(Long id) {
		this.id = id;
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
