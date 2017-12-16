package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.Zx9ZfYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class Zx9ZfYzDao extends BaseYzDao<Zx9ZfYz> {

	@Override
	protected String getTableName() {
		return "zx9_zf_yz";
	}

}
