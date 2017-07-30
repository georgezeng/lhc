package lhc.repository.jpa.api;

import org.springframework.data.repository.PagingAndSortingRepository;

import lhc.domain.ZsYz;

public interface ZsYzRepository extends PagingAndSortingRepository<ZsYz, Long> {
	ZsYz findByDate(String date);
}