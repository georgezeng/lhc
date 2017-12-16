package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.Zx13ZfYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class Zx13ZfYzDao extends BaseYzDao<Zx13ZfYz> {

	@Override
	protected String getTableName() {
		return "zx13_zf_yz";
	}

}
