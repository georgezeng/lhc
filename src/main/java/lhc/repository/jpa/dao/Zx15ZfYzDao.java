package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.Zx15ZfYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class Zx15ZfYzDao extends BaseYzDao<Zx15ZfYz> {

	@Override
	protected String getTableName() {
		return "zx15_zf_yz";
	}

}
