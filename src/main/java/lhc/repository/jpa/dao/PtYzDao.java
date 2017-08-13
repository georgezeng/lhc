package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.PtYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class PtYzDao extends BaseYzDao<PtYz> {

	@Override
	protected String getTableName() {
		return "pt_yz";
	}
}
