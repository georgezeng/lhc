package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.Zx8ZfYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class Zx8ZfYzDao extends BaseYzDao<Zx8ZfYz> {

	@Override
	protected String getTableName() {
		return "zx8_zf_yz";
	}

}
