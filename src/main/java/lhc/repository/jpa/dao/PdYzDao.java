package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.PdYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class PdYzDao extends BaseYzDao<PdYz> {

	@Override
	protected String getTableName() {
		return "pd_yz";
	}
}
