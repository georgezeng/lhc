package lhc.repository.jpa.api;

import org.springframework.data.repository.PagingAndSortingRepository;

import lhc.domain.SqYz;

public interface SqYzRepository extends PagingAndSortingRepository<SqYz, Long> {
	SqYz findByDate(String date);
}