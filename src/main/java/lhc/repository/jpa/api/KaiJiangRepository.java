package lhc.repository.jpa.api;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import lhc.domain.KaiJiang;
import lhc.repository.jpa.BaseYzRepository;

public interface KaiJiangRepository extends BaseYzRepository<KaiJiang> {

	List<KaiJiang> findByYearOrderByPhaseDesc(int year);

	@Query(value = "select distinct year from kai_jiang group by year, id order by year desc", nativeQuery=true)
	List<Integer> findGroupByYear();

	void deleteByYear(int year);
}