package lhc.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import lhc.domain.BaseYz;

public interface BaseYzRepository<T extends BaseYz> extends PagingAndSortingRepository<T, Long> {
	T findByDate(String date);

	T findByYearAndPhase(int year, int phase);

}