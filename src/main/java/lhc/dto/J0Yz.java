package lhc.dto;

import java.math.BigInteger;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;

@SqlResultSetMapping(name = "J0Yz", classes = @ConstructorResult(targetClass = J0Yz.class, columns = {
		@ColumnResult(name = "date", type = String.class), @ColumnResult(name = "year", type = BigInteger.class),
		@ColumnResult(name = "phase", type = BigInteger.class), @ColumnResult(name = "sxj0", type = BigInteger.class),
		@ColumnResult(name = "sxzfj0", type = BigInteger.class), @ColumnResult(name = "dsj0", type = BigInteger.class),
		@ColumnResult(name = "dszfj0", type = BigInteger.class), @ColumnResult(name = "swj0", type = BigInteger.class),
		@ColumnResult(name = "swzfj0", type = BigInteger.class), @ColumnResult(name = "mwj0", type = BigInteger.class),
		@ColumnResult(name = "mwzfj0", type = BigInteger.class), @ColumnResult(name = "lhj0", type = BigInteger.class),
		@ColumnResult(name = "lhzfj0", type = BigInteger.class), @ColumnResult(name = "bsj0", type = BigInteger.class),
		@ColumnResult(name = "bszfj0", type = BigInteger.class), @ColumnResult(name = "zsj0", type = BigInteger.class),
		@ColumnResult(name = "zszfj0", type = BigInteger.class), @ColumnResult(name = "wxzfj0", type = BigInteger.class),
		@ColumnResult(name = "wxj0", type = BigInteger.class), @ColumnResult(name = "wxdsj0", type = BigInteger.class),
		@ColumnResult(name = "wxdszfj0", type = BigInteger.class), @ColumnResult(name = "pdj0", type = BigInteger.class),
		@ColumnResult(name = "pdzfj0", type = BigInteger.class), @ColumnResult(name = "fdj0", type = BigInteger.class),
		@ColumnResult(name = "fdzfj0", type = BigInteger.class), @ColumnResult(name = "qqj0", type = BigInteger.class),
		@ColumnResult(name = "qqzfj0", type = BigInteger.class), @ColumnResult(name = "twelvej0", type = BigInteger.class),
		@ColumnResult(name = "twelvezfj0", type = BigInteger.class), @ColumnResult(name = "slqj0", type = BigInteger.class),
		@ColumnResult(name = "slqzfj0", type = BigInteger.class) }))
@Entity
public class J0Yz {
	@Id
	private Long id;
	private String date;
	private BigInteger year;
	private BigInteger phase;
	private BigInteger sxj0;
	private BigInteger sxzfj0;
	private BigInteger dsj0;
	private BigInteger dszfj0;
	private BigInteger swj0;
	private BigInteger swzfj0;
	private BigInteger mwj0;
	private BigInteger mwzfj0;
	private BigInteger lhj0;
	private BigInteger lhzfj0;
	private BigInteger bsj0;
	private BigInteger bszfj0;
	private BigInteger zsj0;
	private BigInteger zszfj0;
	private BigInteger wxj0;
	private BigInteger wxzfj0;
	private BigInteger wxdsj0;
	private BigInteger wxdszfj0;
	private BigInteger pdj0;
	private BigInteger pdzfj0;
	private BigInteger fdj0;
	private BigInteger fdzfj0;
	private BigInteger qqj0;
	private BigInteger qqzfj0;
	private BigInteger twelvej0;
	private BigInteger twelvezfj0;
	private BigInteger slqj0;
	private BigInteger slqzfj0;

	public J0Yz() {
	}

	public J0Yz(String date, BigInteger year, BigInteger phase, BigInteger sxj0, BigInteger sxzfj0, BigInteger dsj0,
			BigInteger dszfj0, BigInteger swj0, BigInteger swzfj0, BigInteger mwj0, BigInteger mwzfj0, BigInteger lhj0,
			BigInteger lhzfj0, BigInteger bsj0, BigInteger bszfj0, BigInteger zsj0, BigInteger zszfj0, BigInteger wxj0,
			BigInteger wxzfj0, BigInteger wxdsj0, BigInteger wxdszfj0, BigInteger pdj0, BigInteger pdzfj0, BigInteger fdj0,
			BigInteger fdzfj0, BigInteger qqj0, BigInteger qqzfj0, BigInteger twelvej0, BigInteger twelvezfj0,
			BigInteger slqj0, BigInteger slqzfj0) {
		this.date = date;
		this.year = year;
		this.phase = phase;
		this.sxj0 = sxj0;
		this.sxzfj0 = sxzfj0;
		this.dsj0 = dsj0;
		this.dszfj0 = dszfj0;
		this.swj0 = swj0;
		this.swzfj0 = swzfj0;
		this.mwj0 = mwj0;
		this.mwzfj0 = mwzfj0;
		this.lhj0 = lhj0;
		this.lhzfj0 = lhzfj0;
		this.bsj0 = bsj0;
		this.bszfj0 = bszfj0;
		this.zsj0 = zsj0;
		this.zszfj0 = zszfj0;
		this.wxj0 = wxj0;
		this.wxzfj0 = wxzfj0;
		this.wxdsj0 = wxdsj0;
		this.wxdszfj0 = wxdszfj0;
		this.pdj0 = pdj0;
		this.pdzfj0 = pdzfj0;
		this.fdj0 = fdj0;
		this.fdzfj0 = fdzfj0;
		this.qqj0 = qqj0;
		this.qqzfj0 = qqzfj0;
		this.twelvej0 = twelvej0;
		this.twelvezfj0 = twelvezfj0;
		this.slqj0 = slqj0;
		this.slqzfj0 = slqzfj0;
	}

	public BigInteger getSxzfj0() {
		return sxzfj0;
	}

	public void setSxzfj0(BigInteger sxzfj0) {
		this.sxzfj0 = sxzfj0;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigInteger getMwj0() {
		return mwj0;
	}

	public void setMwj0(BigInteger mwj0) {
		this.mwj0 = mwj0;
	}

	public BigInteger getMwzfj0() {
		return mwzfj0;
	}

	public void setMwzfj0(BigInteger mwzfj0) {
		this.mwzfj0 = mwzfj0;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public BigInteger getYear() {
		return year;
	}

	public void setYear(BigInteger year) {
		this.year = year;
	}

	public BigInteger getPhase() {
		return phase;
	}

	public void setPhase(BigInteger phase) {
		this.phase = phase;
	}

	public BigInteger getSxj0() {
		return sxj0;
	}

	public void setSxj0(BigInteger sxj0) {
		this.sxj0 = sxj0;
	}

	public BigInteger getDsj0() {
		return dsj0;
	}

	public void setDsj0(BigInteger dsj0) {
		this.dsj0 = dsj0;
	}

	public BigInteger getDszfj0() {
		return dszfj0;
	}

	public void setDszfj0(BigInteger dszfj0) {
		this.dszfj0 = dszfj0;
	}

	public BigInteger getSwj0() {
		return swj0;
	}

	public void setSwj0(BigInteger swj0) {
		this.swj0 = swj0;
	}

	public BigInteger getSwzfj0() {
		return swzfj0;
	}

	public void setSwzfj0(BigInteger swzfj0) {
		this.swzfj0 = swzfj0;
	}

	public BigInteger getLhj0() {
		return lhj0;
	}

	public void setLhj0(BigInteger lhj0) {
		this.lhj0 = lhj0;
	}

	public BigInteger getLhzfj0() {
		return lhzfj0;
	}

	public void setLhzfj0(BigInteger lhzfj0) {
		this.lhzfj0 = lhzfj0;
	}

	public BigInteger getBsj0() {
		return bsj0;
	}

	public void setBsj0(BigInteger bsj0) {
		this.bsj0 = bsj0;
	}

	public BigInteger getBszfj0() {
		return bszfj0;
	}

	public void setBszfj0(BigInteger bszfj0) {
		this.bszfj0 = bszfj0;
	}

	public BigInteger getZsj0() {
		return zsj0;
	}

	public void setZsj0(BigInteger zsj0) {
		this.zsj0 = zsj0;
	}

	public BigInteger getZszfj0() {
		return zszfj0;
	}

	public void setZszfj0(BigInteger zszfj0) {
		this.zszfj0 = zszfj0;
	}

	public BigInteger getWxj0() {
		return wxj0;
	}

	public void setWxj0(BigInteger wxj0) {
		this.wxj0 = wxj0;
	}

	public BigInteger getWxzfj0() {
		return wxzfj0;
	}

	public void setWxzfj0(BigInteger wxzfj0) {
		this.wxzfj0 = wxzfj0;
	}

	public BigInteger getWxdsj0() {
		return wxdsj0;
	}

	public void setWxdsj0(BigInteger wxdsj0) {
		this.wxdsj0 = wxdsj0;
	}

	public BigInteger getWxdszfj0() {
		return wxdszfj0;
	}

	public void setWxdszfj0(BigInteger wxdszfj0) {
		this.wxdszfj0 = wxdszfj0;
	}

	public BigInteger getPdj0() {
		return pdj0;
	}

	public void setPdj0(BigInteger pdj0) {
		this.pdj0 = pdj0;
	}

	public BigInteger getPdzfj0() {
		return pdzfj0;
	}

	public void setPdzfj0(BigInteger pdzfj0) {
		this.pdzfj0 = pdzfj0;
	}

	public BigInteger getFdj0() {
		return fdj0;
	}

	public void setFdj0(BigInteger fdj0) {
		this.fdj0 = fdj0;
	}

	public BigInteger getFdzfj0() {
		return fdzfj0;
	}

	public void setFdzfj0(BigInteger fdzfj0) {
		this.fdzfj0 = fdzfj0;
	}

	public BigInteger getQqj0() {
		return qqj0;
	}

	public void setQqj0(BigInteger qqj0) {
		this.qqj0 = qqj0;
	}

	public BigInteger getQqzfj0() {
		return qqzfj0;
	}

	public void setQqzfj0(BigInteger qqzfj0) {
		this.qqzfj0 = qqzfj0;
	}

	public BigInteger getTwelvej0() {
		return twelvej0;
	}

	public void setTwelvej0(BigInteger twelvej0) {
		this.twelvej0 = twelvej0;
	}

	public BigInteger getTwelvezfj0() {
		return twelvezfj0;
	}

	public void setTwelvezfj0(BigInteger twelvezfj0) {
		this.twelvezfj0 = twelvezfj0;
	}

	public BigInteger getSlqj0() {
		return slqj0;
	}

	public void setSlqj0(BigInteger slqj0) {
		this.slqj0 = slqj0;
	}

	public BigInteger getSlqzfj0() {
		return slqzfj0;
	}

	public void setSlqzfj0(BigInteger slqzfj0) {
		this.slqzfj0 = slqzfj0;
	}

}
