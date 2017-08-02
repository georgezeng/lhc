package lhc.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;

import lhc.domain.BaseYz;

public interface BaseYzRepository<T extends BaseYz> extends PagingAndSortingRepository<T, Long> {
	T findByDate(String date);
}