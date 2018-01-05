package lhc.dto;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.SqlResultSetMapping;

import lhc.domain.DsxJyYz;

@SqlResultSetMapping(name = "DsxJY", classes = @ConstructorResult(targetClass = DsxJYViewBean.class, columns = {
		@ColumnResult(name = "date", type = String.class), @ColumnResult(name = "year", type = Integer.class),
		@ColumnResult(name = "phase", type = Integer.class), @ColumnResult(name = "specialNum", type = Integer.class),
		@ColumnResult(name = "c1n", type = String.class), @ColumnResult(name = "c2n", type = String.class),
		@ColumnResult(name = "c3n", type = String.class), @ColumnResult(name = "c4n", type = String.class),
		@ColumnResult(name = "c5n", type = String.class), @ColumnResult(name = "c6n", type = String.class),
		@ColumnResult(name = "c7n", type = String.class), @ColumnResult(name = "c8n", type = String.class),
		@ColumnResult(name = "c9n", type = String.class), @ColumnResult(name = "c10n", type = String.class),
		@ColumnResult(name = "c11n", type = String.class) }))
@Entity
public class DsxJYViewBean extends DsxJyYz {
	public DsxJYViewBean() {
	}

	public DsxJYViewBean(String date, int year, int phase, Integer specialNum, String c1n, String c2n, String c3n,
			String c4n, String c5n, String c6n, String c7n, String c8n, String c9n, String c10n, String c11n) {
		setSpecialNum(specialNum);
		setYear(year);
		setPhase(phase);
		setDate(date);
		setC1n(c1n);
		setC2n(c2n);
		setC3n(c3n);
		setC4n(c4n);
		setC5n(c5n);
		setC6n(c6n);
		setC7n(c7n);
		setC8n(c8n);
		setC9n(c9n);
		setC10n(c10n);
		setC11n(c11n);
	}
}
