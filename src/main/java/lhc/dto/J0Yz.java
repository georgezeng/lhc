package lhc.dto;

import java.util.List;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Transient;

@SqlResultSetMapping(name = "J0Yz", classes = @ConstructorResult(targetClass = J0Yz.class, columns = {
		@ColumnResult(name = "date", type = String.class), @ColumnResult(name = "year", type = Integer.class),
		@ColumnResult(name = "phase", type = Integer.class), @ColumnResult(name = "sxj0", type = Integer.class),
		@ColumnResult(name = "sxzfj0", type = Integer.class), @ColumnResult(name = "dsj0", type = Integer.class),
		@ColumnResult(name = "dszfj0", type = Integer.class), @ColumnResult(name = "swj0", type = Integer.class),
		@ColumnResult(name = "swzfj0", type = Integer.class), @ColumnResult(name = "mwj0", type = Integer.class),
		@ColumnResult(name = "mwzfj0", type = Integer.class), @ColumnResult(name = "lhj0", type = Integer.class),
		@ColumnResult(name = "lhzfj0", type = Integer.class), @ColumnResult(name = "bsj0", type = Integer.class),
		@ColumnResult(name = "bszfj0", type = Integer.class), @ColumnResult(name = "zsj0", type = Integer.class),
		@ColumnResult(name = "zszfj0", type = Integer.class), @ColumnResult(name = "wxzfj0", type = Integer.class),
		@ColumnResult(name = "wxj0", type = Integer.class), @ColumnResult(name = "wxdsj0", type = Integer.class),
		@ColumnResult(name = "wxdszfj0", type = Integer.class), @ColumnResult(name = "pdj0", type = Integer.class),
		@ColumnResult(name = "pdzfj0", type = Integer.class), @ColumnResult(name = "fdj0", type = Integer.class),
		@ColumnResult(name = "fdzfj0", type = Integer.class), @ColumnResult(name = "qqj0", type = Integer.class),
		@ColumnResult(name = "qqzfj0", type = Integer.class), @ColumnResult(name = "twelvej0", type = Integer.class),
		@ColumnResult(name = "twelvezfj0", type = Integer.class), @ColumnResult(name = "slqj0", type = Integer.class),
		@ColumnResult(name = "slqzfj0", type = Integer.class) }))
@Entity
public class J0Yz {
	public J0Yz() {
	}

	public J0Yz(String date, Integer year, Integer phase, Integer sxj0, Integer sxzfj0, Integer dsj0, Integer dszfj0,
			Integer swj0, Integer swzfj0, Integer mwj0, Integer mwzfj0, Integer lhj0, Integer lhzfj0, Integer bsj0,
			Integer bszfj0, Integer zsj0, Integer zszfj0, Integer wxj0, Integer wxzfj0, Integer wxdsj0, Integer wxdszfj0,
			Integer pdj0, Integer pdzfj0, Integer fdj0, Integer fdzfj0, Integer qqj0, Integer qqzfj0, Integer twelvej0,
			Integer twelvezfj0, Integer slqj0, Integer slqzfj0) {
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

	@Id
	private Long id;
	private String date;
	private Integer year;
	private Integer phase;
	private Integer sxj0;
	private Integer sxzfj0;
	private Integer dsj0;
	private Integer dszfj0;
	private Integer swj0;
	private Integer swzfj0;
	private Integer mwj0;
	private Integer mwzfj0;
	private Integer lhj0;
	private Integer lhzfj0;
	private Integer bsj0;
	private Integer bszfj0;
	private Integer zsj0;
	private Integer zszfj0;
	private Integer wxj0;
	private Integer wxzfj0;
	private Integer wxdsj0;
	private Integer wxdszfj0;
	private Integer pdj0;
	private Integer pdzfj0;
	private Integer fdj0;
	private Integer fdzfj0;
	private Integer qqj0;
	private Integer qqzfj0;
	private Integer twelvej0;
	private Integer twelvezfj0;
	private Integer slqj0;
	private Integer slqzfj0;
	@Transient
	private List<Integer> sxNums;
	@Transient
	private List<Integer> sxzfNums;
	@Transient
	private List<Integer> dsNums;
	@Transient
	private List<Integer> dszfNums;
	@Transient
	private List<Integer> swNums;
	@Transient
	private List<Integer> swzfNums;
	@Transient
	private List<Integer> mwNums;
	@Transient
	private List<Integer> mwzfNums;
	@Transient
	private List<Integer> lhNums;
	@Transient
	private List<Integer> lhzfNums;
	@Transient
	private List<Integer> bsNums;
	@Transient
	private List<Integer> bszfNums;
	@Transient
	private List<Integer> zsNums;
	@Transient
	private List<Integer> zszfNums;
	@Transient
	private List<Integer> wxNums;
	@Transient
	private List<Integer> wxzfNums;
	@Transient
	private List<Integer> wxdsNums;
	@Transient
	private List<Integer> wxdszfNums;
	@Transient
	private List<Integer> pdNums;
	@Transient
	private List<Integer> pdzfNums;
	@Transient
	private List<Integer> fdNums;
	@Transient
	private List<Integer> fdzfNums;
	@Transient
	private List<Integer> qqNums;
	@Transient
	private List<Integer> qqzfNums;
	@Transient
	private List<Integer> twelveNums;
	@Transient
	private List<Integer> twelvezfNums;
	@Transient
	private List<Integer> slqNums;
	@Transient
	private List<Integer> slqzfNums;

	public List<Integer> getQqNums() {
		return qqNums;
	}

	public void setQqNums(List<Integer> qqNums) {
		this.qqNums = qqNums;
	}

	public List<Integer> getQqzfNums() {
		return qqzfNums;
	}

	public void setQqzfNums(List<Integer> qqzfNums) {
		this.qqzfNums = qqzfNums;
	}

	public List<Integer> getTwelveNums() {
		return twelveNums;
	}

	public void setTwelveNums(List<Integer> twelveNums) {
		this.twelveNums = twelveNums;
	}

	public List<Integer> getTwelvezfNums() {
		return twelvezfNums;
	}

	public void setTwelvezfNums(List<Integer> twelvezfNums) {
		this.twelvezfNums = twelvezfNums;
	}

	public List<Integer> getSlqNums() {
		return slqNums;
	}

	public void setSlqNums(List<Integer> slqNums) {
		this.slqNums = slqNums;
	}

	public List<Integer> getSlqzfNums() {
		return slqzfNums;
	}

	public void setSlqzfNums(List<Integer> slqzfNums) {
		this.slqzfNums = slqzfNums;
	}

	public List<Integer> getFdNums() {
		return fdNums;
	}

	public void setFdNums(List<Integer> fdNums) {
		this.fdNums = fdNums;
	}

	public List<Integer> getFdzfNums() {
		return fdzfNums;
	}

	public void setFdzfNums(List<Integer> fdzfNums) {
		this.fdzfNums = fdzfNums;
	}

	public List<Integer> getPdNums() {
		return pdNums;
	}

	public void setPdNums(List<Integer> pdNums) {
		this.pdNums = pdNums;
	}

	public List<Integer> getPdzfNums() {
		return pdzfNums;
	}

	public void setPdzfNums(List<Integer> pdzfNums) {
		this.pdzfNums = pdzfNums;
	}

	public List<Integer> getWxdsNums() {
		return wxdsNums;
	}

	public void setWxdsNums(List<Integer> wxdsNums) {
		this.wxdsNums = wxdsNums;
	}

	public List<Integer> getWxdszfNums() {
		return wxdszfNums;
	}

	public void setWxdszfNums(List<Integer> wxdszfNums) {
		this.wxdszfNums = wxdszfNums;
	}

	public List<Integer> getWxNums() {
		return wxNums;
	}

	public void setWxNums(List<Integer> wxNums) {
		this.wxNums = wxNums;
	}

	public List<Integer> getWxzfNums() {
		return wxzfNums;
	}

	public void setWxzfNums(List<Integer> wxzfNums) {
		this.wxzfNums = wxzfNums;
	}

	public List<Integer> getZsNums() {
		return zsNums;
	}

	public void setZsNums(List<Integer> zsNums) {
		this.zsNums = zsNums;
	}

	public List<Integer> getZszfNums() {
		return zszfNums;
	}

	public void setZszfNums(List<Integer> zszfNums) {
		this.zszfNums = zszfNums;
	}

	public List<Integer> getBsNums() {
		return bsNums;
	}

	public void setBsNums(List<Integer> bsNums) {
		this.bsNums = bsNums;
	}

	public List<Integer> getBszfNums() {
		return bszfNums;
	}

	public void setBszfNums(List<Integer> bszfNums) {
		this.bszfNums = bszfNums;
	}

	public List<Integer> getLhNums() {
		return lhNums;
	}

	public void setLhNums(List<Integer> lhNums) {
		this.lhNums = lhNums;
	}

	public List<Integer> getLhzfNums() {
		return lhzfNums;
	}

	public void setLhzfNums(List<Integer> lhzfNums) {
		this.lhzfNums = lhzfNums;
	}

	public List<Integer> getMwNums() {
		return mwNums;
	}

	public void setMwNums(List<Integer> mwNums) {
		this.mwNums = mwNums;
	}

	public List<Integer> getMwzfNums() {
		return mwzfNums;
	}

	public void setMwzfNums(List<Integer> mwzfNums) {
		this.mwzfNums = mwzfNums;
	}

	public List<Integer> getSwNums() {
		return swNums;
	}

	public void setSwNums(List<Integer> swNums) {
		this.swNums = swNums;
	}

	public List<Integer> getSwzfNums() {
		return swzfNums;
	}

	public void setSwzfNums(List<Integer> swzfNums) {
		this.swzfNums = swzfNums;
	}

	public List<Integer> getSxNums() {
		return sxNums;
	}

	public void setSxNums(List<Integer> sxNums) {
		this.sxNums = sxNums;
	}

	public List<Integer> getSxzfNums() {
		return sxzfNums;
	}

	public void setSxzfNums(List<Integer> sxzfNums) {
		this.sxzfNums = sxzfNums;
	}

	public List<Integer> getDsNums() {
		return dsNums;
	}

	public void setDsNums(List<Integer> dsNums) {
		this.dsNums = dsNums;
	}

	public List<Integer> getDszfNums() {
		return dszfNums;
	}

	public void setDszfNums(List<Integer> dszfNums) {
		this.dszfNums = dszfNums;
	}

	public Integer getSxzfj0() {
		return sxzfj0;
	}

	public void setSxzfj0(Integer sxzfj0) {
		this.sxzfj0 = sxzfj0;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getMwj0() {
		return mwj0;
	}

	public void setMwj0(Integer mwj0) {
		this.mwj0 = mwj0;
	}

	public Integer getMwzfj0() {
		return mwzfj0;
	}

	public void setMwzfj0(Integer mwzfj0) {
		this.mwzfj0 = mwzfj0;
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

	public Integer getSxj0() {
		return sxj0;
	}

	public void setSxj0(Integer sxj0) {
		this.sxj0 = sxj0;
	}

	public Integer getDsj0() {
		return dsj0;
	}

	public void setDsj0(Integer dsj0) {
		this.dsj0 = dsj0;
	}

	public Integer getDszfj0() {
		return dszfj0;
	}

	public void setDszfj0(Integer dszfj0) {
		this.dszfj0 = dszfj0;
	}

	public Integer getSwj0() {
		return swj0;
	}

	public void setSwj0(Integer swj0) {
		this.swj0 = swj0;
	}

	public Integer getSwzfj0() {
		return swzfj0;
	}

	public void setSwzfj0(Integer swzfj0) {
		this.swzfj0 = swzfj0;
	}

	public Integer getLhj0() {
		return lhj0;
	}

	public void setLhj0(Integer lhj0) {
		this.lhj0 = lhj0;
	}

	public Integer getLhzfj0() {
		return lhzfj0;
	}

	public void setLhzfj0(Integer lhzfj0) {
		this.lhzfj0 = lhzfj0;
	}

	public Integer getBsj0() {
		return bsj0;
	}

	public void setBsj0(Integer bsj0) {
		this.bsj0 = bsj0;
	}

	public Integer getBszfj0() {
		return bszfj0;
	}

	public void setBszfj0(Integer bszfj0) {
		this.bszfj0 = bszfj0;
	}

	public Integer getZsj0() {
		return zsj0;
	}

	public void setZsj0(Integer zsj0) {
		this.zsj0 = zsj0;
	}

	public Integer getZszfj0() {
		return zszfj0;
	}

	public void setZszfj0(Integer zszfj0) {
		this.zszfj0 = zszfj0;
	}

	public Integer getWxj0() {
		return wxj0;
	}

	public void setWxj0(Integer wxj0) {
		this.wxj0 = wxj0;
	}

	public Integer getWxzfj0() {
		return wxzfj0;
	}

	public void setWxzfj0(Integer wxzfj0) {
		this.wxzfj0 = wxzfj0;
	}

	public Integer getWxdsj0() {
		return wxdsj0;
	}

	public void setWxdsj0(Integer wxdsj0) {
		this.wxdsj0 = wxdsj0;
	}

	public Integer getWxdszfj0() {
		return wxdszfj0;
	}

	public void setWxdszfj0(Integer wxdszfj0) {
		this.wxdszfj0 = wxdszfj0;
	}

	public Integer getPdj0() {
		return pdj0;
	}

	public void setPdj0(Integer pdj0) {
		this.pdj0 = pdj0;
	}

	public Integer getPdzfj0() {
		return pdzfj0;
	}

	public void setPdzfj0(Integer pdzfj0) {
		this.pdzfj0 = pdzfj0;
	}

	public Integer getFdj0() {
		return fdj0;
	}

	public void setFdj0(Integer fdj0) {
		this.fdj0 = fdj0;
	}

	public Integer getFdzfj0() {
		return fdzfj0;
	}

	public void setFdzfj0(Integer fdzfj0) {
		this.fdzfj0 = fdzfj0;
	}

	public Integer getQqj0() {
		return qqj0;
	}

	public void setQqj0(Integer qqj0) {
		this.qqj0 = qqj0;
	}

	public Integer getQqzfj0() {
		return qqzfj0;
	}

	public void setQqzfj0(Integer qqzfj0) {
		this.qqzfj0 = qqzfj0;
	}

	public Integer getTwelvej0() {
		return twelvej0;
	}

	public void setTwelvej0(Integer twelvej0) {
		this.twelvej0 = twelvej0;
	}

	public Integer getTwelvezfj0() {
		return twelvezfj0;
	}

	public void setTwelvezfj0(Integer twelvezfj0) {
		this.twelvezfj0 = twelvezfj0;
	}

	public Integer getSlqj0() {
		return slqj0;
	}

	public void setSlqj0(Integer slqj0) {
		this.slqj0 = slqj0;
	}

	public Integer getSlqzfj0() {
		return slqzfj0;
	}

	public void setSlqzfj0(Integer slqzfj0) {
		this.slqzfj0 = slqzfj0;
	}

}
