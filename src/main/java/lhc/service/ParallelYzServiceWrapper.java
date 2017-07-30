package lhc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lhc.domain.Avg;

@Service
@Transactional
public class ParallelYzServiceWrapper {

	@Autowired
	private ParallelYzService parallelYzService;

	@Async
	public <T extends Avg> Future<Exception> calAvg(PagingAndSortingRepository<T, Long> repository) {
		Exception t = null;
		try {
			List<T> list = (List<T>) repository.findAll(new Sort(new Order(Direction.DESC, "date")));
			if (list != null) {
				int count = 0;
				List<Future<Exception>> tasks = new ArrayList<Future<Exception>>();
				for (T yz : list) {
					tasks.add(parallelYzService.calAvg(yz, list, count++, repository));
				}
				while (true) {
					count = 0;
					for (Future<Exception> f : tasks) {
						if (f.isDone()) {
							if (f.get() != null) {
								throw f.get();
							}
							count++;
						}
					}
					if (count == tasks.size()) {
						break;
					} else {
						Thread.sleep(10);
					}
				}
			}
		} catch (Exception e) {
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

}
