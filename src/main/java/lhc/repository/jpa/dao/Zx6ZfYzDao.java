package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.Zx6ZfYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class Zx6ZfYzDao extends BaseYzDao<Zx6ZfYz> {

	@Override
	protected String getTableName() {
		return "zx6_zf_yz";
	}

}
