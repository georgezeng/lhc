package lhc.dto;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.SqlResultSetMapping;

import lhc.domain.DmgJyYz;

@SqlResultSetMapping(name = "DmgJY", classes = @ConstructorResult(targetClass = DmgJYViewBean.class, columns = {
		@ColumnResult(name = "date", type = String.class), @ColumnResult(name = "year", type = Integer.class),
		@ColumnResult(name = "phase", type = Integer.class), @ColumnResult(name = "specialNum", type = Integer.class),
		@ColumnResult(name = "aen", type = String.class), @ColumnResult(name = "bfn", type = String.class),
		@ColumnResult(name = "cgn", type = String.class), @ColumnResult(name = "dhn", type = String.class) }))
@Entity
public class DmgJYViewBean extends DmgJyYz {
	public DmgJYViewBean() {
	}

	public DmgJYViewBean(String date, int year, int phase, Integer specialNum, String aen, String bfn, String cgn,
			String dhn) {
		setSpecialNum(specialNum);
		setYear(year);
		setPhase(phase);
		setDate(date);
		setAen(aen);
		setBfn(bfn);
		setCgn(cgn);
		setDhn(dhn);
	}
}
