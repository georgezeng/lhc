package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.Zx4ZfYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class Zx4ZfYzDao extends BaseYzDao<Zx4ZfYz> {

	@Override
	protected String getTableName() {
		return "zx4_zf_yz";
	}

}
