package lhc.repository.jpa.api;

import org.springframework.data.repository.PagingAndSortingRepository;

import lhc.domain.TmFdYz;

public interface TmFdYzRepository extends PagingAndSortingRepository<TmFdYz, Long> {
	TmFdYz findByDate(String date);
}