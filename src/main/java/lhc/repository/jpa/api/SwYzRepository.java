package lhc.repository.jpa.api;

import org.springframework.data.repository.PagingAndSortingRepository;

import lhc.domain.SwYz;

public interface SwYzRepository extends PagingAndSortingRepository<SwYz, Long> {
	SwYz findByDate(String date);
}