package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.LhYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class LhYzDao extends BaseYzDao<LhYz> {

	@Override
	protected String getTableName() {
		return "lh_yz";
	}
}
