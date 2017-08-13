package lhc.repository.jpa.api;

import lhc.domain.PtYz;
import lhc.repository.jpa.BaseYzRepository;

public interface PtYzRepository extends BaseYzRepository<PtYz> {

	PtYz findByYearAndPhase(int year, int phase);
}