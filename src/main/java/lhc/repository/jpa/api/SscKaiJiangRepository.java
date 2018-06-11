package lhc.repository.jpa.api;

import org.springframework.data.repository.PagingAndSortingRepository;

import lhc.domain.SscKaiJiang;

public interface SscKaiJiangRepository extends PagingAndSortingRepository<SscKaiJiang, Long> {
	SscKaiJiang findByPhase(String phase);
}