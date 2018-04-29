package lhc.service;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.concurrent.Future;

import org.springframework.data.repository.CrudRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import lhc.domain.Avg;

@Service
public class ParallelYzService {

	@Async
	public <T extends Avg> Future<Exception> calAvg(T yz, List<T> list, int count, CrudRepository<T, Long> repository) {
		Exception t = null;
		try {
			int arg1 = 12;
			int arg2 = 150;
			int topLen = 5;
			int minLen = 20;
			Integer totalAll = 0;
			Integer[] topTotals = new Integer[topLen];
			Integer[] minTotals = new Integer[minLen];
			for (int i = count; i < count + arg2; i++) {
				if (i < list.size()) {
					Avg data = list.get(i);
					if (i < count + arg1) {
						if (data.getTotal() != null) {
							totalAll += data.getTotal();
						}
					}
					for (int j = 0; j < topLen; j++) {
						if (topTotals[j] == null) {
							topTotals[j] = 0;
						}
						Method m = ReflectionUtils.findMethod(Avg.class, "getTop" + j);
						Integer value = (Integer) m.invoke(data);
						if (value != null) {
							topTotals[j] += value;
						}
					}

					for (int j = 0; j < minLen; j++) {
						if (minTotals[j] == null) {
							minTotals[j] = 0;
						}
						Method m = ReflectionUtils.findMethod(Avg.class, "getMin" + j);
						Integer value = (Integer) m.invoke(data);
						if (value != null) {
							minTotals[j] += value;
						}
					}
				} else {
					break;
				}
			}
			yz.setTotalAvg(new BigDecimal(totalAll).divide(new BigDecimal(arg1), 2, RoundingMode.HALF_UP));
			for (int i = 0; i < topLen; i++) {
				if (topTotals[i] != null) {
					Method m = ReflectionUtils.findMethod(Avg.class, "setTop" + i + "Avg", BigDecimal.class);
					m.invoke(yz, new BigDecimal(topTotals[i]).divide(new BigDecimal(arg2), 2, RoundingMode.HALF_UP));
				}
			}
			for (int i = 0; i < minLen; i++) {
				if (minTotals[i] != null) {
					Method m = ReflectionUtils.findMethod(Avg.class, "setMin" + i + "Avg", BigDecimal.class);
					m.invoke(yz, new BigDecimal(minTotals[i]).divide(new BigDecimal(arg2), 2, RoundingMode.HALF_UP));
				}
			}
			repository.save(yz);
		} catch (Exception e) {
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

}
