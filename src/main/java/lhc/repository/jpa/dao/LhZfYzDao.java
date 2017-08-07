package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.LhZfYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class LhZfYzDao extends BaseYzDao<LhZfYz> {

	@Override
	protected String getTableName() {
		return "lh_zf_yz";
	}

}
