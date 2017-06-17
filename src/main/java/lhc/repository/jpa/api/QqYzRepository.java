package lhc.repository.jpa.api;

import org.springframework.data.repository.PagingAndSortingRepository;

import lhc.domain.QqYz;

public interface QqYzRepository extends PagingAndSortingRepository<QqYz, Long> {
	QqYz findByDate(String date);
}