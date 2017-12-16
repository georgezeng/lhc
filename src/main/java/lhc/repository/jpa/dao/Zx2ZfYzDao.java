package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.Zx2ZfYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class Zx2ZfYzDao extends BaseYzDao<Zx2ZfYz> {

	@Override
	protected String getTableName() {
		return "zx2_zf_yz";
	}

}
