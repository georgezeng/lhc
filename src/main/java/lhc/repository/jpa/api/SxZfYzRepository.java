package lhc.repository.jpa.api;

import org.springframework.data.repository.PagingAndSortingRepository;

import lhc.domain.SxZfYz;

public interface SxZfYzRepository extends PagingAndSortingRepository<SxZfYz, Long> {
	SxZfYz findByDate(String date);
}