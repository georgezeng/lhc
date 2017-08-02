package lhc.repository.jpa.api;

import lhc.domain.TmYz;
import lhc.repository.jpa.BaseYzRepository;

public interface TmYzRepository extends BaseYzRepository<TmYz> {

	TmYz findByYearAndPhase(int year, int phase);
}