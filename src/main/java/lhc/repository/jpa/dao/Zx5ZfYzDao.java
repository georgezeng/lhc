package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.Zx5ZfYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class Zx5ZfYzDao extends BaseYzDao<Zx5ZfYz> {

	@Override
	protected String getTableName() {
		return "zx5_zf_yz";
	}

}
