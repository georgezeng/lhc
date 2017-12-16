package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.Zx3ZfYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class Zx3ZfYzDao extends BaseYzDao<Zx3ZfYz> {

	@Override
	protected String getTableName() {
		return "zx3_zf_yz";
	}

}
