package lhc.repository.jpa.api;

import org.springframework.data.repository.PagingAndSortingRepository;

import lhc.domain.BsYz;

public interface BsYzRepository extends PagingAndSortingRepository<BsYz, Long> {
	BsYz findByDate(String date);
}