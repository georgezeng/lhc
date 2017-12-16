package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.Zx1ZfYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class Zx1ZfYzDao extends BaseYzDao<Zx1ZfYz> {

	@Override
	protected String getTableName() {
		return "zx1_zf_yz";
	}

}
