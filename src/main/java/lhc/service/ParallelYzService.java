package lhc.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import lhc.domain.SxYz;
import lhc.domain.SxZfYz2;
import lhc.repository.jpa.api.SxYzRepository;
import lhc.repository.jpa.api.SxZfYz2Repository;

@Service
public class ParallelYzService {
	@Autowired
	private SxYzRepository sxyzRepository;

	@Autowired
	private SxZfYz2Repository sxzfyz2Repository;

	@Async
	public Future<Exception> calAvg(SxYz yz, List<SxYz> list, int count) {
		Exception t = null;
		try {
			Integer totalAll = 0;
			Integer totalMax = 0;
			Integer totalMin0 = 0;
			Integer totalMin1 = 0;
			Integer totalMin2 = 0;
			Integer totalMin3 = 0;
			Integer totalMin4 = 0;
			Integer totalMin5 = 0;
			Integer totalMin6 = 0;
			for (int i = count; i < count + 12; i++) {
				if (i < list.size()) {
					SxYz data = list.get(i);
					if (data.getTotal() != null) {
						totalAll += data.getTotal();
					}
					if (data.getMax() != null) {
						totalMax += data.getMax();
					}
					if (data.getMin0() != null) {
						totalMin0 += data.getMin0();
					}
					if (data.getMin1() != null) {
						totalMin1 += data.getMin1();
					}
					if (data.getMin2() != null) {
						totalMin2 += data.getMin2();
					}
					if (data.getMin3() != null) {
						totalMin3 += data.getMin3();
					}
					if (data.getMin4() != null) {
						totalMin4 += data.getMin4();
					}
					if (data.getMin5() != null) {
						totalMin5 += data.getMin5();
					}
					if (data.getMin6() != null) {
						totalMin6 += data.getMin6();
					}
				} else {
					break;
				}
			}
			yz.setTotalAvg(new BigDecimal(totalAll).divide(new BigDecimal(12), 2, RoundingMode.HALF_UP));
			yz.setMaxAvg(new BigDecimal(totalMax).divide(new BigDecimal(12), 2, RoundingMode.HALF_UP));
			yz.setMin0Avg(new BigDecimal(totalMin0).divide(new BigDecimal(12), 2, RoundingMode.HALF_UP));
			yz.setMin1Avg(new BigDecimal(totalMin1).divide(new BigDecimal(12), 2, RoundingMode.HALF_UP));
			yz.setMin2Avg(new BigDecimal(totalMin2).divide(new BigDecimal(12), 2, RoundingMode.HALF_UP));
			yz.setMin3Avg(new BigDecimal(totalMin3).divide(new BigDecimal(12), 2, RoundingMode.HALF_UP));
			yz.setMin4Avg(new BigDecimal(totalMin4).divide(new BigDecimal(12), 2, RoundingMode.HALF_UP));
			yz.setMin5Avg(new BigDecimal(totalMin5).divide(new BigDecimal(12), 2, RoundingMode.HALF_UP));
			yz.setMin6Avg(new BigDecimal(totalMin6).divide(new BigDecimal(12), 2, RoundingMode.HALF_UP));
			sxyzRepository.save(yz);
		} catch (Exception e) {
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calAvg(SxZfYz2 yz, List<SxZfYz2> list, int count) {
		Exception t = null;
		try {
			Integer totalAll = 0;
			Integer totalMax = 0;
			Integer totalMin0 = 0;
			Integer totalMin1 = 0;
			Integer totalMin2 = 0;
			Integer totalMin3 = 0;
			Integer totalMin4 = 0;
			Integer totalMin5 = 0;
			Integer totalMin6 = 0;
			for (int i = count; i < count + 12; i++) {
				if (i < list.size()) {
					SxZfYz2 data = list.get(i);
					if (data.getTotal() != null) {
						totalAll += data.getTotal();
					}
					if (data.getMax() != null) {
						totalMax += data.getMax();
					}
					if (data.getMin0() != null) {
						totalMin0 += data.getMin0();
					}
					if (data.getMin1() != null) {
						totalMin1 += data.getMin1();
					}
					if (data.getMin2() != null) {
						totalMin2 += data.getMin2();
					}
					if (data.getMin3() != null) {
						totalMin3 += data.getMin3();
					}
					if (data.getMin4() != null) {
						totalMin4 += data.getMin4();
					}
					if (data.getMin5() != null) {
						totalMin5 += data.getMin5();
					}
					if (data.getMin6() != null) {
						totalMin6 += data.getMin6();
					}
				} else {
					break;
				}
			}
			yz.setTotalAvg(new BigDecimal(totalAll).divide(new BigDecimal(12), 2, RoundingMode.HALF_UP));
			yz.setMaxAvg(new BigDecimal(totalMax).divide(new BigDecimal(12), 2, RoundingMode.HALF_UP));
			yz.setMin0Avg(new BigDecimal(totalMin0).divide(new BigDecimal(12), 2, RoundingMode.HALF_UP));
			yz.setMin1Avg(new BigDecimal(totalMin1).divide(new BigDecimal(12), 2, RoundingMode.HALF_UP));
			yz.setMin2Avg(new BigDecimal(totalMin2).divide(new BigDecimal(12), 2, RoundingMode.HALF_UP));
			yz.setMin3Avg(new BigDecimal(totalMin3).divide(new BigDecimal(12), 2, RoundingMode.HALF_UP));
			yz.setMin4Avg(new BigDecimal(totalMin4).divide(new BigDecimal(12), 2, RoundingMode.HALF_UP));
			yz.setMin5Avg(new BigDecimal(totalMin5).divide(new BigDecimal(12), 2, RoundingMode.HALF_UP));
			yz.setMin6Avg(new BigDecimal(totalMin6).divide(new BigDecimal(12), 2, RoundingMode.HALF_UP));
			sxzfyz2Repository.save(yz);
		} catch (Exception e) {
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}
}
