package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.Zx18ZfYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class Zx18ZfYzDao extends BaseYzDao<Zx18ZfYz> {

	@Override
	protected String getTableName() {
		return "zx18_zf_yz";
	}

}
