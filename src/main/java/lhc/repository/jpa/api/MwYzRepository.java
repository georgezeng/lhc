package lhc.repository.jpa.api;

import org.springframework.data.repository.PagingAndSortingRepository;

import lhc.domain.MwYz;

public interface MwYzRepository extends PagingAndSortingRepository<MwYz, Long> {
	MwYz findByDate(String date);
}