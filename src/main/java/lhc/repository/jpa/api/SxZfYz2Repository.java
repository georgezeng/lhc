package lhc.repository.jpa.api;

import org.springframework.data.repository.PagingAndSortingRepository;

import lhc.domain.SxZfYz2;

public interface SxZfYz2Repository extends PagingAndSortingRepository<SxZfYz2, Long> {
	SxZfYz2 findByDate(String date);
}