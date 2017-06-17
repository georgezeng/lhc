package lhc.repository.jpa.api;

import org.springframework.data.repository.PagingAndSortingRepository;

import lhc.domain.LhYz;

public interface LhYzRepository extends PagingAndSortingRepository<LhYz, Long> {
	LhYz findByDate(String date);
}