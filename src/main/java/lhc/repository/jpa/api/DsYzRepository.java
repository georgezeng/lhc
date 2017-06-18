package lhc.repository.jpa.api;

import org.springframework.data.repository.PagingAndSortingRepository;

import lhc.domain.DsYz;

public interface DsYzRepository extends PagingAndSortingRepository<DsYz, Long> {
	DsYz findByDate(String date);
}