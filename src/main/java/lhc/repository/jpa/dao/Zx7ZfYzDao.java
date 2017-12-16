package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.Zx7ZfYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class Zx7ZfYzDao extends BaseYzDao<Zx7ZfYz> {

	@Override
	protected String getTableName() {
		return "zx7_zf_yz";
	}

}
