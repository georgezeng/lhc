package lhc.repository.jpa.api;

import org.springframework.data.repository.PagingAndSortingRepository;

import lhc.domain.SxYz;

public interface SxYzRepository extends PagingAndSortingRepository<SxYz, Long> {
	SxYz findByYearAndPhase(int year, int phase);
}