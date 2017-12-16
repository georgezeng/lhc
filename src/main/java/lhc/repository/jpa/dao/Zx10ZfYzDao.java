package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.Zx10ZfYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class Zx10ZfYzDao extends BaseYzDao<Zx10ZfYz> {

	@Override
	protected String getTableName() {
		return "zx10_zf_yz";
	}

}
