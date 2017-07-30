package lhc.repository.jpa.api;

import org.springframework.data.repository.PagingAndSortingRepository;

import lhc.domain.TmYz;

public interface TmYzRepository extends PagingAndSortingRepository<TmYz, Long> {
	TmYz findByDate(String date);

	TmYz findByYearAndPhase(int year, int phase);
}