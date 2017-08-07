package lhc.repository.jpa.api;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import lhc.domain.KaiJiang;
import lhc.repository.jpa.BaseYzRepository;

@Transactional
public interface KaiJiangRepository extends BaseYzRepository<KaiJiang> {

	List<KaiJiang> findByYearOrderByPhaseDesc(int year);

	@Query(value = "from KaiJiang group by year order by year desc")
	List<KaiJiang> findGroupByYear();

	void deleteByYear(int year);
}