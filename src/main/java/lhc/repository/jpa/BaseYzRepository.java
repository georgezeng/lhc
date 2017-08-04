package lhc.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import lhc.domain.BaseYz;

@Transactional
public interface BaseYzRepository<T extends BaseYz> extends PagingAndSortingRepository<T, Long> {
	T findByDate(String date);
}