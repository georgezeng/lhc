package lhc.repository.jpa.api;

import org.springframework.data.repository.PagingAndSortingRepository;

import lhc.domain.QwYz;

public interface QwYzRepository extends PagingAndSortingRepository<QwYz, Long> {
	QwYz findByDate(String date);
}