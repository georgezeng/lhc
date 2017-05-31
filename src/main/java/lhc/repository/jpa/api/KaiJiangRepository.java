package lhc.repository.jpa.api;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import lhc.domain.KaiJiang;

@Transactional
public interface KaiJiangRepository extends PagingAndSortingRepository<KaiJiang, Long> {
	KaiJiang findByDate(String date);

	List<KaiJiang> findByYearOrderByPhaseDesc(int year);

	@Query(value = "from KaiJiang group by year order by year desc")
	List<KaiJiang> findGroupByYear();
	
	void deleteByYear(int year);
}